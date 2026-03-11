package com.abapp.seekhoassignment.ui.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.abapp.seekhoassignment.utils.observeNetworkStatus


@Composable
fun NetworkStatusIndicator(context: Context) {
    val isOnline by context.observeNetworkStatus().collectAsState(initial = true)

    if (!isOnline) {
        Text(
            text = "Offline Mode",
            modifier = Modifier
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Red,
            ),
        )
    }
}