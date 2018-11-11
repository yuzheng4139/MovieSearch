package com.moviesearch.framework.dagger

import com.moviesearch.features.main.MoviesRemoteDataSource
import com.moviesearch.framework.MovieSearchApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))


interface MainComponent {

    fun inject(application: MovieSearchApplication)
    fun inject(moviesRemoteDataSource: MoviesRemoteDataSource)
}
