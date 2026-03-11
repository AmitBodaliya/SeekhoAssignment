package com.abapp.seekhoassignment.ui.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer


@Composable
fun AnimeListPlaceholder() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            EffectView(Modifier.size(120.dp))

            Spacer(Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                EffectView(Modifier.height(20.dp).fillMaxWidth())

                Spacer(Modifier.height(8.dp))

                EffectView(Modifier.height(20.dp).fillMaxWidth(0.5f))
            }
        }
    }
}



@Composable
private fun EffectView(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .alpha(0.5f)
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer()
            )
    )
}