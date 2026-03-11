package com.abapp.seekhoassignment.data.database.dao

import com.abapp.seekhoassignment.data.database.entity.AnimeEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<AnimeEntity>)


    @Query("""SELECT * FROM anime WHERE page = :page ORDER BY position ASC """)
    suspend fun getAnimeByPage(page: Int): List<AnimeEntity>


    @Query("SELECT * FROM anime")
    suspend fun getAllAnime(): List<AnimeEntity>

}