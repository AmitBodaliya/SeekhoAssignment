package com.abapp.seekhoassignment.data.repository

import android.content.Context
import com.abapp.seekhoassignment.api.RetrofitClient
import com.abapp.seekhoassignment.data.database.AnimeDatabase
import com.abapp.seekhoassignment.data.database.entity.AnimeDetailEntity
import com.abapp.seekhoassignment.data.database.entity.AnimeEntity
import com.abapp.seekhoassignment.model.Anime
import com.abapp.seekhoassignment.model.AnimeDetail
import com.abapp.seekhoassignment.model.AnimeImageUrl
import com.abapp.seekhoassignment.model.AnimeImages
import com.abapp.seekhoassignment.model.Genre
import com.abapp.seekhoassignment.model.Trailer
import kotlin.collections.emptyList


class AnimeRepositoryImpl(
    context: Context
) : AnimeRepository {


    //api
    private val api = RetrofitClient.apiService

    //database - room
    private val db = AnimeDatabase.getDatabase(context)
    private val animeDao = db.animeDao()
    private val detailDao = db.animeDetailDao()






    // get anime from api
    override suspend fun getTopAnime(): List<Anime> {
        return getTopAnimePage(1)
    }




    // get specific page
    override suspend fun getTopAnimePage(page: Int): List<Anime> {
        return try {
            //call
            val response = api.getTopAnime(page = page).data

            //add db
            val entities = response.mapIndexed { index, anime ->
                AnimeEntity(
                    mal_id = anime.mal_id,
                    title = anime.title,
                    episodes = anime.episodes,
                    score = anime.score,
                    imageUrl = anime.images.jpg.image_url,
                    page = page,
                    position = index
                )
            }
            animeDao.insertAnime(entities)

            //return
            response
        } catch (_: Exception) {

            animeDao.getAnimeByPage(page).map {

                Anime(
                    mal_id = it.mal_id,
                    title = it.title,
                    episodes = it.episodes,
                    score = it.score,
                    images = AnimeImages(
                        jpg = AnimeImageUrl(it.imageUrl)
                    )
                )
            }
        }
    }




    //get anime details from api
    override suspend fun getAnimeDetails(id: Int): AnimeDetail {
        return try {
            //call
            val detail = api.getAnimeDetails(id).data

            // save to DB
            detailDao.insertAnimeDetail(
                AnimeDetailEntity(
                    mal_id = detail.mal_id,
                    title = detail.title,
                    synopsis = detail.synopsis,
                    episodes = detail.episodes,
                    score = detail.score,
                    imageUrl = detail.images.jpg.image_url,
                    genres = detail.genres.joinToString { it.name },
                    trailerId = detail.trailer?.youtube_id
                )
            )

            //return
            detail
        } catch (_: Exception) {

            // offline fallback
            val cached = detailDao.getAnimeDetail(id)

            AnimeDetail(
                mal_id = cached?.mal_id ?: 0,
                title = cached?.title ?: "Unknown",
                synopsis = cached?.synopsis,
                episodes = cached?.episodes,
                score = cached?.score,
                images = AnimeImages(
                    jpg = AnimeImageUrl(
                        cached?.imageUrl ?: ""
                    )
                ),
                genres = cached?.genres?.split(",")?.map { Genre(it) } ?: emptyList(),
                trailer = Trailer(cached?.trailerId)
            )
        }
    }
}