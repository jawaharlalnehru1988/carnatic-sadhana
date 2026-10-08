package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.audio.AudioConverterUtil
import com.example.audio.TalaMetronome
import com.example.audio.TanpuraSynthesizer
import com.example.data.local.PreloadedCurriculum
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read app name string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Carnatic Sadhana", appName)
    }

    @Test
    fun `verify preloaded curriculum categories and lessons`() {
        val categories = PreloadedCurriculum.getDefaultCategories()
        val lessons = PreloadedCurriculum.getDefaultLessons()

        assertTrue(categories.isNotEmpty())
        assertTrue(lessons.isNotEmpty())

        val saraliCat = categories.find { it.id == "sarali" }
        assertNotNull(saraliCat)
        assertEquals("Sarali Varisais", saraliCat?.name)

        val jantaiCat = categories.find { it.id == "jantai" }
        assertNotNull(jantaiCat)

        val teacherCat = categories.find { it.id == "teacher" }
        assertNotNull(teacherCat)
    }

    @Test
    fun `verify youtube video id extraction`() {
        val url1 = "https://www.youtube.com/watch?v=kYJv8ZqjS_k"
        val id1 = AudioConverterUtil.extractYouTubeVideoId(url1)
        assertEquals("kYJv8ZqjS_k", id1)

        val url2 = "https://youtu.be/8V-d1m2N3x4"
        val id2 = AudioConverterUtil.extractYouTubeVideoId(url2)
        assertEquals("8V-d1m2N3x4", id2)
    }

    @Test
    fun `verify tanpura kattai frequencies and talas`() {
        assertTrue(TanpuraSynthesizer.KATTAI_MAP.containsKey("1 Kattai (C)"))
        assertEquals(8, TalaMetronome.ADI_TALA.beatsCount)
        assertEquals(6, TalaMetronome.RUPAKA_TALA.beatsCount)
    }
}
