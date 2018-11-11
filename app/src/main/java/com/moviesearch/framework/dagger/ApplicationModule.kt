package com.moviesearch.framework.dagger

import com.moviesearch.framework.MovieSearchApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: MovieSearchApplication) {
    @Provides @Singleton
    fun provideApplication() = app
}
