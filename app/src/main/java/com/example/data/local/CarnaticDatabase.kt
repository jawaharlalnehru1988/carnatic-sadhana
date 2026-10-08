package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.CategoryEntity
import com.example.data.model.LessonEntity
import com.example.data.model.PracticeLogEntity
import com.example.data.model.RecordingEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        CategoryEntity::class,
        LessonEntity::class,
        RecordingEntity::class,
        PracticeLogEntity::class,
        com.example.data.model.CustomTanpuraEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CarnaticDatabase : RoomDatabase() {

    abstract fun carnaticDao(): CarnaticDao

    companion object {
        @Volatile
        private var INSTANCE: CarnaticDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CarnaticDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarnaticDatabase::class.java,
                    "carnatic_sadhana.db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Populate default curriculum on initial database creation
                            INSTANCE?.let { database ->
                                scope.launch(Dispatchers.IO) {
                                    val dao = database.carnaticDao()
                                    dao.insertCategories(PreloadedCurriculum.getDefaultCategories())
                                    dao.insertLessons(PreloadedCurriculum.getDefaultLessons())
                                }
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
