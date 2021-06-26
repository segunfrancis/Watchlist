package com.raywenderlich.android.watchlist.network.interceptor

import android.content.Context
import android.os.Build
import com.raywenderlich.android.watchlist.Utils.APP_VERSION
import com.raywenderlich.android.watchlist.Utils.DEVICE_MODEL
import com.raywenderlich.android.watchlist.Utils.DEVICE_PLATFORM
import com.raywenderlich.android.watchlist.Utils.OS_VERSION
import okhttp3.Interceptor
import okhttp3.Response

class AnalyticsInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val version = packageInfo.versionName

        requestBuilder.addHeader(APP_VERSION, version)
        requestBuilder.addHeader(OS_VERSION, Build.VERSION.SDK_INT.toString())
        requestBuilder.addHeader(DEVICE_MODEL, Build.MODEL)
        requestBuilder.addHeader(DEVICE_PLATFORM, "android")

        return chain.proceed(requestBuilder.build())
    }
}