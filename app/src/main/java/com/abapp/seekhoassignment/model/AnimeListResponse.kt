package com.abapp.seekhoassignment.model


data class AnimeListResponse(
    val data: List<Anime>,
    val pagination: Pagination
)

data class Pagination(
    val last_visible_page: Int,
    val has_next_page: Boolean,
    val current_page: Int
)