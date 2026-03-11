package com.abapp.seekhoassignment.ui.component

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YoutubePlayer(youtubeId: String, modifier: Modifier = Modifier) {
    val videoUrl = "https://www.youtube.com/embed/$youtubeId?autoplay=0"

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.pluginState = WebSettings.PluginState.ON
                webViewClient = WebViewClient()
                loadUrl(videoUrl)
            }
        },
        modifier = modifier
    )
}