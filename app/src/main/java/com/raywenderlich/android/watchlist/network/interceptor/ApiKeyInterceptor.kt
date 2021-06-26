package com.raywenderlich.android.watchlist.network.interceptor

import com.raywenderlich.android.watchlist.BuildConfig
import com.raywenderlich.android.watchlist.Utils.API_KEY_QUERY_PARAMETER_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val url = originalUrl.newBuilder()
                .addQueryParameter(API_KEY_QUERY_PARAMETER_KEY, BuildConfig.THE_MOVIE_DB_API_TOKEN)
                .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}