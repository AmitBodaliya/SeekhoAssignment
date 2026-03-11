package com.abapp.seekhoassignment.ui.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer


@Composable
fun AnimeDetailsPlaceholder(){

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        //1
        EffectView(Modifier.fillMaxWidth().height(100.dp))

        Spacer(Modifier.height(16.dp))

        //2
        EffectView(Modifier.fillMaxWidth().height(40.dp))

        Spacer(Modifier.height(16.dp))

        //3
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            EffectView(Modifier.weight(1f).height(40.dp))

            Spacer(Modifier.width(16.dp))

            EffectView(Modifier.weight(1f).height(40.dp))
        }

        Spacer(Modifier.height(16.dp))


        //4
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            EffectView(Modifier.weight(1f).height(40.dp))

            Spacer(Modifier.width(16.dp))

            EffectView(Modifier.weight(1f).height(40.dp))
        }

        Spacer(Modifier.height(16.dp))


        //5
        EffectView(Modifier.fillMaxWidth(0.4f).height(40.dp))

        Spacer(Modifier.height(16.dp))

        //6
        EffectView(Modifier.fillMaxWidth(0.4f).height(40.dp))
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