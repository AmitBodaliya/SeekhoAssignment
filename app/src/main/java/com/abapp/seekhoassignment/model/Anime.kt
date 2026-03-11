package com.abapp.seekhoassignment.model


data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: AnimeImages
)

data class AnimeImages(
    val jpg: AnimeImageUrl
)

data class AnimeImageUrl(
    val image_url: String
)