package com.abapp.seekhoassignment.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.abapp.seekhoassignment.model.Anime


@Composable
fun AnimeItemView(
    anime: Anime,
    onAnimeClick: (Int) -> Unit
){


    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onAnimeClick(anime.mal_id)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //load image
            AnimeImage(
                imageUrl = anime.images.jpg.image_url,
                modifier = Modifier.size(150.dp),
            )


            //space
            Spacer(modifier = Modifier.width(8.dp))


            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                //space
                Spacer(modifier = Modifier.height(2.dp))


                Text(
                    text = "Episodes: ${anime.episodes ?: "N/A"}",
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "Rating: ${anime.score ?: "-"}",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
