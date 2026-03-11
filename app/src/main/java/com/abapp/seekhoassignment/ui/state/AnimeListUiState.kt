package com.abapp.seekhoassignment.ui.state

import com.abapp.seekhoassignment.model.Anime

data class AnimeListUiState(
    val isLoading: Boolean = false,
    val animeList: List<Anime> = emptyList(),
    val error: String? = null,
    val isLastPage: Boolean = false
)