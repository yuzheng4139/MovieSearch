package com.moviesearch.framework.network

import com.moviesearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
                .addQueryParameter("apikey", BuildConfig.ApiKeyProperty)
                .build()
        val request = chain.request().newBuilder()
                .url(url)
                .build()
        return chain.proceed(request)
    }
}