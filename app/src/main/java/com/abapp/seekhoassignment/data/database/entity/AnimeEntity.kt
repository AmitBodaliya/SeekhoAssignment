package com.abapp.seekhoassignment.data.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val imageUrl: String,
    val page: Int,
    val position: Int
)