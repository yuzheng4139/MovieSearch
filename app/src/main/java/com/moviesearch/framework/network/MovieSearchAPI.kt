package com.moviesearch.framework.network

import com.moviesearch.features.main.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchAPI {



    @GET(".")
    fun searchMovieByName(@Query("t") name: String): Call<Movie>

//    @GET(".")
//    fun searchMovieByName(@Query("apikey") apiKey: String, @Query("t") name: String): Call<Movie>


// todo Add more APIs here.
}