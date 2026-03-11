package com.abapp.seekhoassignment.data.repository

import com.abapp.seekhoassignment.model.Anime
import com.abapp.seekhoassignment.model.AnimeDetail

interface AnimeRepository {

    suspend fun getTopAnime(): List<Anime>

    suspend fun getTopAnimePage(page: Int): List<Anime>

    suspend fun getAnimeDetails(id: Int): AnimeDetail
}