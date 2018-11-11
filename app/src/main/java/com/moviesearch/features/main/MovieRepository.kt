package com.moviesearch.features.main



class MovieRepository private constructor(moviesRemoteDataSource: MoviesDataSource) : MoviesDataSource {

    private val mMoviesRemoteDataSource: MoviesDataSource = moviesRemoteDataSource
    internal var cachedMovie: MutableMap<String, Movie>? = null


    companion object {
        private var INSTANCE: MovieRepository? = null

        fun getInstance(moviesRemoteDataSource: MoviesDataSource): MovieRepository {
            if (INSTANCE == null) {
                INSTANCE = MovieRepository(moviesRemoteDataSource)
            }
            return INSTANCE as MovieRepository
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun searchMovies(queryText: String, callBack: MoviesDataSource.LoadMoviesCallBack) {
        if (cachedMovie != null) {
            if (cachedMovie?.get(queryText) != null) {
                callBack.onMoviesLoaded(cachedMovie?.get(queryText)!!)
                return
            }

        }
        mMoviesRemoteDataSource.searchMovies(queryText, object : MoviesDataSource.LoadMoviesCallBack {
            override fun onMoviesLoaded(movie: Movie) {
                if (cachedMovie == null) {
                    cachedMovie = HashMap()
                }
                cachedMovie?.put(queryText, movie)
                callBack.onMoviesLoaded(movie)
            }

            override fun onMoviesLoadFailed(message: String) {
                callBack.onMoviesLoadFailed(message)
            }
        })
    }

}