package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.data.model.PracticeLogEntity
import com.example.data.model.RecordingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarnaticDao {

    // --- Categories ---
    @Query("SELECT * FROM categories ORDER BY orderIndex ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteCategory(id: String)

    @Query("DELETE FROM lessons WHERE categoryId = :categoryId")
    suspend fun deleteLessonsByCategoryId(categoryId: String)

    @Query("UPDATE recordings SET categoryId = 'sarali', lessonId = NULL WHERE categoryId = :categoryId")
    suspend fun reassignRecordingsOnCategoryDelete(categoryId: String)

    // --- Lessons ---
    @Query("SELECT * FROM lessons ORDER BY orderIndex ASC")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE categoryId = :categoryId ORDER BY orderIndex ASC")
    fun getLessonsByCategory(categoryId: String): Flow<List<LessonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Query("UPDATE lessons SET youtubeUrl = :youtubeUrl WHERE id = :lessonId")
    suspend fun updateLessonYoutube(lessonId: String, youtubeUrl: String)

    @Query("DELETE FROM lessons WHERE id = :lessonId")
    suspend fun deleteLesson(lessonId: String)

    // --- Recordings ---
    @Query("SELECT * FROM recordings ORDER BY recordedAt DESC")
    fun getAllRecordings(): Flow<List<RecordingEntity>>

    @Query("SELECT * FROM recordings WHERE categoryId = :categoryId ORDER BY recordedAt DESC")
    fun getRecordingsByCategory(categoryId: String): Flow<List<RecordingEntity>>

    @Query("SELECT * FROM recordings WHERE lessonId = :lessonId ORDER BY recordedAt DESC")
    fun getRecordingsByLesson(lessonId: String): Flow<List<RecordingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecording(recording: RecordingEntity): Long

    @Update
    suspend fun updateRecording(recording: RecordingEntity)

    @Query("UPDATE recordings SET categoryId = :newCategoryId, lessonId = :newLessonId WHERE id = :recordingId")
    suspend fun moveRecordingCategory(recordingId: Long, newCategoryId: String, newLessonId: String?)

    @Query("UPDATE recordings SET isFavorite = :isFav WHERE id = :recordingId")
    suspend fun setFavorite(recordingId: Long, isFav: Boolean)

    @Query("DELETE FROM recordings WHERE id = :id")
    suspend fun deleteRecording(id: Long)

    // --- Practice Logs ---
    @Query("SELECT * FROM practice_logs ORDER BY timestamp DESC")
    fun getAllPracticeLogs(): Flow<List<PracticeLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPracticeLog(log: PracticeLogEntity)

    // --- Custom Tanpura Tracks ---
    @Query("SELECT * FROM custom_tanpuras ORDER BY addedAt DESC")
    fun getAllCustomTanpuras(): Flow<List<com.example.data.model.CustomTanpuraEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomTanpura(tanpura: com.example.data.model.CustomTanpuraEntity): Long

    @Query("DELETE FROM custom_tanpuras WHERE id = :id")
    suspend fun deleteCustomTanpura(id: Long)
}
