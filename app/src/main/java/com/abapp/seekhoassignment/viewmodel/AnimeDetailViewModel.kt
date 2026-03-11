package com.abapp.seekhoassignment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abapp.seekhoassignment.data.repository.AnimeRepositoryImpl
import com.abapp.seekhoassignment.ui.state.AnimeDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class AnimeDetailViewModel(
    context: Context
) : ViewModel() {

    private val repository = AnimeRepositoryImpl(context)


    //state
    private val _uiState = MutableStateFlow(AnimeDetailUiState())
    val uiState: StateFlow<AnimeDetailUiState> = _uiState




    //fetch
    fun fetchAnimeDetail(id: Int) {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val detail = repository.getAnimeDetails(id)

                _uiState.value = _uiState.value.copy(
                    animeDetail = detail,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}