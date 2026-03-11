package com.abapp.seekhoassignment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider




class AnimeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(AnimeListViewModel::class.java) -> {
                AnimeListViewModel(context) as T
            }

            modelClass.isAssignableFrom(AnimeDetailViewModel::class.java) -> {
                AnimeDetailViewModel(context) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}