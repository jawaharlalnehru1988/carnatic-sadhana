package com.example.data.repository

import com.example.data.local.CarnaticDao
import com.example.data.local.PreloadedCurriculum
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.data.model.PracticeLogEntity
import com.example.data.model.RecordingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CarnaticRepository(private val dao: CarnaticDao) {

    val categories: Flow<List<CategoryEntity>> = dao.getAllCategories()
    val lessons: Flow<List<LessonEntity>> = dao.getAllLessons()
    val recordings: Flow<List<RecordingEntity>> = dao.getAllRecordings()
    val practiceLogs: Flow<List<PracticeLogEntity>> = dao.getAllPracticeLogs()
    val customTanpuras: Flow<List<com.example.data.model.CustomTanpuraEntity>> = dao.getAllCustomTanpuras()

    suspend fun ensureDefaultData() {
        dao.insertCategories(PreloadedCurriculum.getDefaultCategories())
        // Sync & update standard curriculum lessons with latest notes from askharekrishna
        dao.insertLessons(PreloadedCurriculum.getDefaultLessons())
    }

    fun getLessonsByCategory(categoryId: String): Flow<List<LessonEntity>> {
        return dao.getLessonsByCategory(categoryId)
    }

    fun getRecordingsByCategory(categoryId: String): Flow<List<RecordingEntity>> {
        return dao.getRecordingsByCategory(categoryId)
    }

    fun getRecordingsByLesson(lessonId: String): Flow<List<RecordingEntity>> {
        return dao.getRecordingsByLesson(lessonId)
    }

    suspend fun saveRecording(recording: RecordingEntity): Long {
        return dao.insertRecording(recording)
    }

    suspend fun updateRecording(recording: RecordingEntity) {
        dao.updateRecording(recording)
    }

    suspend fun moveRecordingCategory(recordingId: Long, newCategoryId: String, newLessonId: String? = null) {
        dao.moveRecordingCategory(recordingId, newCategoryId, newLessonId)
    }

    suspend fun deleteRecording(recordingId: Long) {
        dao.deleteRecording(recordingId)
    }

    suspend fun toggleFavorite(recordingId: Long, isFav: Boolean) {
        dao.setFavorite(recordingId, isFav)
    }

    suspend fun insertCategory(category: CategoryEntity) {
        dao.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryEntity) {
        dao.updateCategory(category)
    }

    suspend fun deleteCategory(categoryId: String) {
        dao.deleteLessonsByCategoryId(categoryId)
        dao.reassignRecordingsOnCategoryDelete(categoryId)
        dao.deleteCategory(categoryId)
    }

    suspend fun insertLesson(lesson: LessonEntity) {
        dao.insertLesson(lesson)
    }

    suspend fun updateLesson(lesson: LessonEntity) {
        dao.updateLesson(lesson)
    }

    suspend fun deleteLesson(lessonId: String) {
        dao.deleteLesson(lessonId)
    }

    suspend fun updateLessonYoutube(lessonId: String, youtubeUrl: String) {
        dao.updateLessonYoutube(lessonId, youtubeUrl)
    }

    suspend fun logPracticeSession(log: PracticeLogEntity) {
        dao.insertPracticeLog(log)
    }

    suspend fun saveCustomTanpura(tanpura: com.example.data.model.CustomTanpuraEntity): Long {
        return dao.insertCustomTanpura(tanpura)
    }

    suspend fun deleteCustomTanpura(id: Long) {
        dao.deleteCustomTanpura(id)
    }
}
