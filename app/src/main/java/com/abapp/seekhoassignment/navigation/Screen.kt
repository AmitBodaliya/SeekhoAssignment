package com.abapp.seekhoassignment.navigation


sealed class Screen(val route: String) {
    object AnimeList : Screen("anime_list")
    object AnimeDetail : Screen("anime_detail/{animeId}") {
        fun createRoute(animeId: Int) = "anime_detail/$animeId"
    }
}