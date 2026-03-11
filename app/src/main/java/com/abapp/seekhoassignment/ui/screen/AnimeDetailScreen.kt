package com.abapp.seekhoassignment.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abapp.seekhoassignment.ui.component.AnimeImage
import com.abapp.seekhoassignment.ui.component.NetworkStatusIndicator
import com.abapp.seekhoassignment.ui.component.YoutubePlayer
import com.abapp.seekhoassignment.ui.placeholder.AnimeDetailsPlaceholder
import com.abapp.seekhoassignment.ui.placeholder.AnimeListPlaceholder
import com.abapp.seekhoassignment.viewmodel.AnimeDetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    animeId: Int,
    viewModel: AnimeDetailViewModel,
    onBackProcessed: () -> Unit
) {


    //context
    val context = LocalContext.current


    //state
    val uiState by viewModel.uiState.collectAsState()




    //call to fetch api to details of anime
    LaunchedEffect(Unit) {
        viewModel.fetchAnimeDetail(animeId)
    }





    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Detail") },

                navigationIcon = {
                    IconButton(
                        onClick = { onBackProcessed() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                actions = {
                    NetworkStatusIndicator(context)
                }
            )
        }
    ) { paddingValues ->


        when {

            //loading
            uiState.isLoading && uiState.animeDetail == null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    AnimeDetailsPlaceholder()
                }
            }

            uiState.animeDetail == null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No anime details available",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "We couldn't find any anime details to display right now.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            //error
            uiState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = uiState.error!!,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    )
                }
            }


            //data show
            else -> {
                val anime = uiState.animeDetail ?: return@Scaffold

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    //image , youtube video id
                    if (anime.trailer?.youtube_id != null) {
                        Text(
                            text = "Trailer: https://www.youtube.com/watch?v=${anime.trailer.youtube_id}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            //load image
                            AnimeImage(
                                imageUrl = anime.images.jpg.image_url,
                                modifier = Modifier
                                    .height(260.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = anime.title,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Rating: ${anime.score ?: "-"}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Episodes: ${anime.episodes ?: "N/A"}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))



                    Text(
                        text = "Genres: ${anime.genres.joinToString { it.name }}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = "Synopsis: ${anime.synopsis ?: "No synopsis available"}",
                        style = MaterialTheme.typography.bodyMedium
                    )



                    //show YouTube video if available
                    if (anime.trailer?.youtube_id != null) {
                        YoutubePlayer(
                            youtubeId = anime.trailer.youtube_id,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }

                }
            }
        }

    }
}