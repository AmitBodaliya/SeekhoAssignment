package com.abapp.seekhoassignment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abapp.seekhoassignment.data.database.dao.AnimeDao
import com.abapp.seekhoassignment.data.database.dao.AnimeDetailDao
import com.abapp.seekhoassignment.data.database.entity.AnimeDetailEntity
import com.abapp.seekhoassignment.data.database.entity.AnimeEntity



@Database(
    entities = [AnimeEntity::class, AnimeDetailEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {


    abstract fun animeDao(): AnimeDao
    abstract fun animeDetailDao(): AnimeDetailDao


    companion object {
        @Volatile
        private var INSTANCE: AnimeDatabase? = null

        fun getDatabase(context: Context): AnimeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeDatabase::class.java,
                    "anime_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}