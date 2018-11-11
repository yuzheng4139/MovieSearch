package com.moviesearch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.bumptech.glide.Glide
import com.moviesearch.features.main.Movie
import com.moviesearch.features.main.MovieSearchViewModel
import com.moviesearch.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MovieSearchActivity : BaseActivity() {

    private var mMovieSearchViewModel: MovieSearchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        mMovieSearchViewModel = ViewModelProviders.of(this).get(MovieSearchViewModel::class.java)

        mMovieSearchViewModel?.mMovie?.observe(this, Observer<Movie> { movie ->

            if (movie != null) {
                if (movie.Response.equals("True", true)) {
                    Glide.with(this)
                            .load(movie.Poster)
                            .into(movie_image)
                    movie_title.setText(movie.Title)
                    search_result_layout.visibility = VISIBLE
                    movie_not_found.visibility = GONE
                } else {
                    search_result_layout.visibility = GONE
                    movie_not_found.visibility = VISIBLE
                }
            } else {
                search_result_layout.visibility = GONE
                movie_not_found.visibility = VISIBLE
                movie_not_found.setText(R.string.movie_not_found)
            }
            search_edit_text.hideKeyboard()
        })

        mMovieSearchViewModel?.mMovieLoadFailedMessage?.observe(this, Observer<String> { message ->
            if (message != null) {
                search_result_layout.visibility = GONE
                movie_not_found.visibility = VISIBLE
                movie_not_found.setText(R.string.loading_error)
            }
            search_edit_text.hideKeyboard()
        })

        search_btn.setOnClickListener { mMovieSearchViewModel?.searchMovies(search_edit_text.text.toString()) }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

private fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
