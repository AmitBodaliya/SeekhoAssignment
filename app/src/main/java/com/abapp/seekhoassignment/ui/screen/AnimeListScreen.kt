package com.abapp.seekhoassignment.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abapp.seekhoassignment.ui.component.AnimeItemView
import com.abapp.seekhoassignment.ui.placeholder.AnimeListPlaceholder
import com.abapp.seekhoassignment.ui.component.NetworkStatusIndicator
import com.abapp.seekhoassignment.viewmodel.AnimeListViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    viewModel: AnimeListViewModel,
    onAnimeClick: (Int) -> Unit
) {

    //context
    val context = LocalContext.current



    //ui sate
    val uiState by viewModel.uiState.collectAsState()




    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Top Anime",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        NetworkStatusIndicator(context)
                    }
                }
            )
        }
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {

            // list state
            val listState = rememberLazyListState()


            when {
                //is loading
                uiState.isLoading && uiState.animeList.isEmpty() -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(7) { AnimeListPlaceholder() }
                    }
                }

                //not loading and empty
                uiState.animeList.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No anime available",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Text(
                                text = "We couldn't find any anime to display right now.",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                //is error
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

                //list
                else -> {
                    LazyColumn(
                        state = listState
                    ) {

                        items(uiState.animeList) { anime ->
                            AnimeItemView(
                                anime = anime,
                                onAnimeClick = onAnimeClick
                            )
                        }

                        //if loading
                        if (uiState.isLoading) {
                            //placeholder - effect like
                            items(5) { AnimeListPlaceholder() }
                        }

                    }


                    //if list at end
                    LaunchedEffect(listState) {
                        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                            .collect { index ->
                                if (index != null && index >= uiState.animeList.size - 1) {
                                    viewModel.loadNextPage()
                                }
                            }
                    }
                }
            }

        }
    }

}