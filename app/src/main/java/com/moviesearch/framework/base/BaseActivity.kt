package com.moviesearch.framework.base

import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import com.moviesearch.framework.MovieSearchApplication
import com.moviesearch.framework.dagger.MainComponent

open abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    protected fun getMainComponent(): MainComponent {
        return (getApplication() as MovieSearchApplication).getApplicationComponent()
    }

}