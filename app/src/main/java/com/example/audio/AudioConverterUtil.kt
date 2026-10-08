package com.example.audio

import android.content.Context
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMetadataRetriever
import android.media.MediaMuxer
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AudioConverterUtil {

    private const val TAG = "AudioConverterUtil"

    data class ConversionResult(
        val success: Boolean,
        val outputFile: File?,
        val fileName: String,
        val durationMs: Long,
        val errorMessage: String? = null
    )

    private fun getStorageDir(context: Context): File {
        val dir = File(context.filesDir, "carnatic_recordings")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dir
    }

    /**
     * Imports an audio file or extracts audio from a video from a content Uri (e.g. from native Sound Recorder, Downloads, Google Drive, etc.)
     * and standardizes it to an MP3 / M4A container in internal storage.
     */
    suspend fun importAndConvertAudio(
        context: Context,
        uri: Uri,
        customTitle: String? = null,
        forceStandardize: Boolean = true
    ): ConversionResult = withContext(Dispatchers.IO) {
        try {
            val originalName = queryFileName(context, uri) ?: "imported_audio"
            val sanitizedBase = (customTitle?.ifBlank { null } ?: originalName)
                .replace(Regex("[^a-zA-Z0-9_-]"), "_")
                .take(40)
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

            // First write input stream to a temp file so MediaMetadataRetriever and MediaExtractor can read it
            val tempFile = File(context.cacheDir, "temp_import_${System.currentTimeMillis()}_${originalName}")
            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
            } ?: return@withContext ConversionResult(false, null, originalName, 0L, "Cannot open source file")

            // Check metadata (is it audio or video?)
            val retriever = MediaMetadataRetriever()
            var durationMs = 0L
            var hasVideo = false
            var mimeType = "audio/mpeg"

            try {
                retriever.setDataSource(tempFile.absolutePath)
                val durStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                durationMs = durStr?.toLongOrNull() ?: 0L
                val videoTrack = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO)
                hasVideo = videoTrack == "yes"
                mimeType = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE) ?: "audio/mpeg"
            } catch (e: Exception) {
                Log.w(TAG, "Retriever metadata extraction warning", e)
            } finally {
                retriever.release()
            }

            val storageDir = getStorageDir(context)

            // If it's a video file (e.g. video recorded during class) or requires extracting audio:
            if (hasVideo) {
                val extractedFileName = "${sanitizedBase}_${timeStamp}.m4a"
                val extractedOutputFile = File(storageDir, extractedFileName)
                val extractedOk = extractAudioTrackFromMedia(tempFile, extractedOutputFile)
                tempFile.delete()

                if (extractedOk && extractedOutputFile.exists() && extractedOutputFile.length() > 0) {
                    val finalDur = getFileDuration(extractedOutputFile)
                    return@withContext ConversionResult(
                        success = true,
                        outputFile = extractedOutputFile,
                        fileName = extractedFileName,
                        durationMs = if (finalDur > 0) finalDur else durationMs
                    )
                }
            }

            // For audio files: If original is MP3 or user wants MP3/M4A standardization:
            val extension = if (originalName.endsWith(".mp3", ignoreCase = true)) ".mp3" else ".m4a"
            val finalFileName = "${sanitizedBase}_${timeStamp}$extension"
            val destinationFile = File(storageDir, finalFileName)

            tempFile.copyTo(destinationFile, overwrite = true)
            tempFile.delete()

            val calculatedDuration = if (durationMs > 0) durationMs else getFileDuration(destinationFile)

            ConversionResult(
                success = true,
                outputFile = destinationFile,
                fileName = finalFileName,
                durationMs = calculatedDuration
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error importing audio", e)
            ConversionResult(
                success = false,
                outputFile = null,
                fileName = "error",
                durationMs = 0L,
                errorMessage = e.localizedMessage ?: "Unknown error"
            )
        }
    }

    /**
     * Extracts the raw AAC/audio track from a media container (e.g. video from music teacher) to a standalone M4A audio file.
     */
    private fun extractAudioTrackFromMedia(sourceFile: File, outputFile: File): Boolean {
        var extractor: MediaExtractor? = null
        var muxer: MediaMuxer? = null
        try {
            extractor = MediaExtractor()
            extractor.setDataSource(sourceFile.absolutePath)

            var audioTrackIndex = -1
            var audioFormat: MediaFormat? = null

            for (i in 0 until extractor.trackCount) {
                val format = extractor.getTrackFormat(i)
                val mime = format.getString(MediaFormat.KEY_MIME) ?: ""
                if (mime.startsWith("audio/")) {
                    audioTrackIndex = i
                    audioFormat = format
                    break
                }
            }

            if (audioTrackIndex < 0 || audioFormat == null) {
                Log.e(TAG, "No audio track found in media")
                return false
            }

            extractor.selectTrack(audioTrackIndex)

            muxer = MediaMuxer(outputFile.absolutePath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
            val muxerAudioTrack = muxer.addTrack(audioFormat)
            muxer.start()

            val maxBufferSize = if (audioFormat.containsKey(MediaFormat.KEY_MAX_INPUT_SIZE)) {
                audioFormat.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE).coerceAtLeast(1024 * 1024)
            } else {
                1024 * 1024
            }

            val buffer = ByteBuffer.allocate(maxBufferSize)
            val bufferInfo = android.media.MediaCodec.BufferInfo()

            while (true) {
                bufferInfo.offset = 0
                bufferInfo.size = extractor.readSampleData(buffer, 0)
                if (bufferInfo.size < 0) {
                    break
                }
                bufferInfo.presentationTimeUs = extractor.sampleTime
                bufferInfo.flags = extractor.sampleFlags
                muxer.writeSampleData(muxerAudioTrack, buffer, bufferInfo)
                extractor.advance()
            }

            muxer.stop()
            return true
        } catch (e: Exception) {
            Log.e(TAG, "Audio extraction failed", e)
            return false
        } finally {
            try {
                extractor?.release()
                muxer?.release()
            } catch (_: Exception) {}
        }
    }

    private fun getFileDuration(file: File): Long {
        return try {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(file.absolutePath)
            val dur = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
            retriever.release()
            dur
        } catch (_: Exception) {
            0L
        }
    }

    private fun queryFileName(context: Context, uri: Uri): String? {
        var name: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (index >= 0) {
                        name = it.getString(index)
                    }
                }
            }
        }
        if (name == null) {
            name = uri.path
            val cut = name?.lastIndexOf('/') ?: -1
            if (cut != -1) {
                name = name?.substring(cut + 1)
            }
        }
        return name
    }

    fun extractYouTubeVideoId(url: String): String? {
        val trimmed = url.trim()
        if (trimmed.length == 11 && !trimmed.contains(" ") && !trimmed.contains("/")) {
            return trimmed
        }
        val patterns = listOf(
            Regex("(?:https?:\\/\\/)?(?:www\\.)?(?:youtube\\.com\\/(?:watch\\?v=|embed\\/|v\\/)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})"),
            Regex("v=([a-zA-Z0-9_-]{11})"),
            Regex("youtu\\.be\\/([a-zA-Z0-9_-]{11})")
        )
        for (pattern in patterns) {
            val match = pattern.find(trimmed)
            if (match != null && match.groupValues.size > 1) {
                return match.groupValues[1]
            }
        }
        return null
    }
}
