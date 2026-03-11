package com.abapp.seekhoassignment.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "anime_detail")
data class AnimeDetailEntity(
    @PrimaryKey val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val imageUrl: String,
    val genres: String,
    val trailerId: String?
)