package com.abapp.seekhoassignment

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient


@HiltAndroidApp
class SeekhoAssignment : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                val response = chain.proceed(chain.request())
                response.newBuilder()
                    .header(
                        "Cache-Control",
                        "public, max-age=31536000"
                    )
                    .build()
            }
            .build()

        return ImageLoader.Builder(this)
            .okHttpClient(okHttpClient)
            .build()
    }
}