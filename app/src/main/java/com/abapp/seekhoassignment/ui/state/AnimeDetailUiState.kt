package com.abapp.seekhoassignment.ui.state

import com.abapp.seekhoassignment.model.AnimeDetail

data class AnimeDetailUiState(
    val isLoading: Boolean = false,
    val animeDetail: AnimeDetail? = null,
    val error: String? = null
)