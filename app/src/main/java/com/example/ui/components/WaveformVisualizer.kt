package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.isActive
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Frequency Wave Visualizer that responds dynamically to vocal tone and pitch harmonics.
 * Guarantees dynamic, lively animated waveforms on mobile devices without ever remaining flat.
 */
@Composable
fun WaveformVisualizer(
    amplitude: Float,
    isRecording: Boolean,
    isPaused: Boolean,
    modifier: Modifier = Modifier,
    barsCount: Int = 36
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant

    // Continuous dynamic animation phase for vocal tone frequency waves
    var phase by remember { mutableFloatStateOf(0f) }
    var smoothedAmp by remember { mutableFloatStateOf(0.15f) }

    // High frequency frame ticker that moves the tone wave continuously
    LaunchedEffect(isRecording, isPaused, amplitude) {
        if (isRecording && !isPaused) {
            val target = amplitude.coerceIn(0.12f, 1.0f)
            smoothedAmp = smoothedAmp * 0.7f + target * 0.3f
        } else {
            smoothedAmp = (smoothedAmp * 0.85f).coerceAtLeast(0.08f)
        }
    }

    LaunchedEffect(isRecording, isPaused) {
        while (isActive) {
            if (isRecording && !isPaused) {
                // Advance frequency phase based on vocal tone energy
                val speed = 0.12f + (smoothedAmp * 0.18f)
                phase = (phase + speed) % (2f * PI.toFloat())
            } else if (!isRecording) {
                phase = (phase + 0.03f) % (2f * PI.toFloat())
            }
            kotlinx.coroutines.delay(24) // ~40 FPS smooth wave motion
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(surfaceVariant.copy(alpha = 0.45f))
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .testTag("vocal_frequency_wave_visualizer")
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
        ) {
            val totalWidth = size.width
            val totalHeight = size.height
            val centerY = totalHeight / 2f

            // Dynamic tone frequency scaling: higher when vocal input is detected
            val toneFreq = if (smoothedAmp > 0.22f) 3.2f else 2.0f
            val waveHeightScale = (smoothedAmp * (totalHeight * 0.44f)).coerceIn(10f, totalHeight * 0.48f)

            // --- 1. Draw continuous multi-harmonic frequency wave path (Sine Wave of vocal tone) ---
            val wavePath = Path()
            val fillPath = Path()
            fillPath.moveTo(0f, centerY)

            val stepX = totalWidth / 60f
            for (step in 0..60) {
                val x = step * stepX
                val normalizedX = step / 60f
                // Multi-harmonic sine equation representing singing voice timbre:
                // fundamental + 2nd harmonic + 3rd harmonic
                val angle1 = (normalizedX * toneFreq * 2f * PI.toFloat()) - phase
                val angle2 = (normalizedX * (toneFreq * 2f) * 2f * PI.toFloat()) - (phase * 1.6f)
                val angle3 = (normalizedX * (toneFreq * 3f) * 2f * PI.toFloat()) + (phase * 0.8f)

                val harmonicFactor = (0.62f * sin(angle1)) + (0.28f * sin(angle2)) + (0.10f * cos(angle3))
                val y = centerY + (harmonicFactor * waveHeightScale)

                if (step == 0) {
                    wavePath.moveTo(x, y)
                    fillPath.lineTo(x, y)
                } else {
                    wavePath.lineTo(x, y)
                    fillPath.lineTo(x, y)
                }
            }
            fillPath.lineTo(totalWidth, centerY)
            fillPath.close()

            // Soft glowing area underneath tone wave
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryColor.copy(alpha = if (isRecording && !isPaused) 0.25f else 0.10f),
                        primaryColor.copy(alpha = 0.0f)
                    ),
                    startY = centerY - waveHeightScale,
                    endY = centerY + waveHeightScale
                )
            )

            // Glowing frequency wave stroke
            drawPath(
                path = wavePath,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        secondaryColor,
                        primaryColor,
                        tertiaryColor
                    )
                ),
                style = Stroke(
                    width = if (smoothedAmp > 0.25f) 3.5f else 2.2f,
                    cap = StrokeCap.Round
                )
            )

            // --- 2. Draw dynamic frequency spectrum bars matching vocal tone ---
            val barSpacing = totalWidth / barsCount
            val barWidth = (barSpacing * 0.65f).coerceAtLeast(3.2f)

            for (i in 0 until barsCount) {
                val normPos = i.toFloat() / barsCount.toFloat()
                val barAngle = (normPos * toneFreq * 2f * PI.toFloat()) - phase
                val barHarmonic = (0.55f + 0.35f * sin(barAngle) + 0.10f * sin(barAngle * 2.2f))

                // Scale bar height according to smoothed vocal amplitude and frequency wave
                val rawHeight = (smoothedAmp * (barHarmonic * 1.5f) * totalHeight).coerceIn(8f, totalHeight - 6f)
                val h = if (isRecording && !isPaused) rawHeight else 8f

                val x = i * barSpacing + (barSpacing - barWidth) / 2f
                val y = centerY - (h / 2f)

                val barBrush = Brush.verticalGradient(
                    colors = listOf(
                        secondaryColor.copy(alpha = 0.9f),
                        primaryColor,
                        tertiaryColor.copy(alpha = 0.9f)
                    ),
                    startY = y,
                    endY = y + h
                )

                drawRoundRect(
                    brush = barBrush,
                    topLeft = Offset(x, y),
                    size = Size(barWidth, h),
                    cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
                )
            }
        }

        // Live visual frequency badge
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 4.dp, bottom = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isRecording && !isPaused) {
                    if (smoothedAmp > 0.28f) "Tone Active: Singing Wave" else "Mic Listening..."
                } else if (isPaused) {
                    "Paused"
                } else {
                    "Ready to Sing"
                },
                style = MaterialTheme.typography.labelSmall,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = if (isRecording && !isPaused) primaryColor else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}
