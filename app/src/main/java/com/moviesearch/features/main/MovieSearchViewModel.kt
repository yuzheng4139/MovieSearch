package com.moviesearch.features.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MovieSearchViewModel : ViewModel() {

    var mMovieRepository: MovieRepository = MovieRepository.getInstance(MoviesRemoteDataSource.instance)
    var mMovie: MutableLiveData<Movie> = MutableLiveData()
    var mMovieLoadFailedMessage: MutableLiveData<String> = MutableLiveData()

    fun searchMovies(query:String){

        mMovieRepository.searchMovies(query,object :MoviesDataSource.LoadMoviesCallBack{
            override fun onMoviesLoaded(movie: Movie) {
                mMovie.value = movie
            }

            override fun onMoviesLoadFailed(message: String) {
                mMovieLoadFailedMessage.value = message
            }
        })
    }

}