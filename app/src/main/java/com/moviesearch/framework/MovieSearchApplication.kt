package com.moviesearch.framework

import android.app.Application
import com.moviesearch.framework.dagger.ApplicationModule
import com.moviesearch.framework.dagger.DaggerMainComponent
import com.moviesearch.framework.dagger.MainComponent
import com.moviesearch.framework.dagger.NetworkModule

class MovieSearchApplication : Application() {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        mainComponent.inject(this)
    }

    fun getApplicationComponent(): MainComponent {
        return mainComponent
    }

}