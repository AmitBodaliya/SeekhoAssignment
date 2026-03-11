package com.abapp.seekhoassignment.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abapp.seekhoassignment.model.Anime
import com.abapp.seekhoassignment.data.repository.AnimeRepositoryImpl
import com.abapp.seekhoassignment.ui.state.AnimeListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class AnimeListViewModel(
    context: Context
) : ViewModel() {


    private val repository = AnimeRepositoryImpl(context)


    //state, data
    private var currentPage = 1
    private var isLoadingPage = false

    private val _uiState = MutableStateFlow(AnimeListUiState())
    val uiState: StateFlow<AnimeListUiState> = _uiState




    //load list
    init {
        loadNextPage()
    }



    //load list
    fun loadNextPage() {
        if (isLoadingPage || _uiState.value.isLastPage) return

        viewModelScope.launch {
            isLoadingPage = true

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val newPage = repository.getTopAnimePage(currentPage)

                if (newPage.isEmpty()) {
                    _uiState.value = _uiState.value.copy(
                        isLastPage = true,
                        isLoading = false
                    )
                } else {
                    val updatedList = _uiState.value.animeList + newPage

                    _uiState.value = _uiState.value.copy(
                        animeList = updatedList,
                        isLoading = false
                    )

                    currentPage++
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            } finally {
                isLoadingPage = false
            }
        }
    }
}