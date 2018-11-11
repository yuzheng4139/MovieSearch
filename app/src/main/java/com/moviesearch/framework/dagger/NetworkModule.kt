package com.moviesearch.framework.dagger

import com.moviesearch.framework.network.ApiKeyInterceptor
import com.moviesearch.framework.network.MovieSearchAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieSearchAPI(retrofit: Retrofit): MovieSearchAPI =
            retrofit.create(MovieSearchAPI::class.java)
}