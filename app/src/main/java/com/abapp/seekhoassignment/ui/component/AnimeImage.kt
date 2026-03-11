package com.abapp.seekhoassignment.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.abapp.seekhoassignment.R


@Composable
fun AnimeImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    size: Dp = 150.dp
) {

    val context = LocalContext.current


    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .size(300)
        .diskCacheKey(imageUrl)
        .memoryCacheKey(imageUrl)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()


    SubcomposeAsyncImage(
        model = request,
        contentDescription = "Anime Image",
        modifier = modifier
    ) {

        when (painter.state) {

            is AsyncImagePainter.State.Loading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_broken_image_24),
                        contentDescription = null,
                        modifier = Modifier.size(size * 0.2f)
                    )
                }

            }

            is AsyncImagePainter.State.Error -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_wifi_off),
                        contentDescription = null,
                        modifier = Modifier.size(size * 0.2f)
                    )
                }

            }

            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}