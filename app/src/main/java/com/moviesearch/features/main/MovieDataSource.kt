package com.moviesearch.features.main


interface MoviesDataSource {

    interface LoadMoviesCallBack {
        fun onMoviesLoaded(movie: Movie)
        fun onMoviesLoadFailed(message: String)
    }

    fun searchMovies(queryText: String, callBack: LoadMoviesCallBack)
}