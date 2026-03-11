package com.abapp.seekhoassignment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abapp.seekhoassignment.data.database.entity.AnimeDetailEntity


@Dao
interface AnimeDetailDao {

    @Query("SELECT * FROM anime_detail WHERE mal_id = :id")
    suspend fun getAnimeDetail(id: Int): AnimeDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeDetail(animeDetail: AnimeDetailEntity)
}