package com.moviesearch.features.main

import com.moviesearch.framework.dagger.DaggerMainComponent
import com.moviesearch.framework.dagger.NetworkModule
import com.moviesearch.framework.network.MovieSearchAPI
import com.moviesearch.framework.network.ServiceCallBack
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteDataSource private constructor() : MoviesDataSource {

    @Inject
    lateinit var api: MovieSearchAPI

    init {
        DaggerMainComponent
                .builder()
                .networkModule(NetworkModule())
                .build().inject(this)
    }

    companion object {
        private var INSTANCE: MoviesRemoteDataSource? = null
        val instance: MoviesRemoteDataSource
            get() {
                if (INSTANCE == null) {
                    INSTANCE = MoviesRemoteDataSource()
                }
                return INSTANCE as MoviesRemoteDataSource
            }
    }


    override fun searchMovies(queryText: String, callBack: MoviesDataSource.LoadMoviesCallBack) {
        val call = api!!.searchMovieByName(queryText)

        call.enqueue(object : ServiceCallBack<Movie>() {
            override fun onSuccess(response: Response<Movie>?) {
                callBack.onMoviesLoaded(response?.body()!!)
            }

            override fun onFail(message: String) {
                callBack.onMoviesLoadFailed(message)
            }
        })
    }
}