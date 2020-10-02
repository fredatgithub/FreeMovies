package com.georgcantor.freemovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.ui.fragment.videos.VideosFragment
import com.georgcantor.freemovies.util.NetworkUtils.getNetworkLiveData
import com.georgcantor.freemovies.util.gone
import com.georgcantor.freemovies.util.openFragment
import com.georgcantor.freemovies.util.visible
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_movies -> {
                    openFragment(VideosFragment.create(0))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_tv -> {
                    openFragment(VideosFragment.create(1))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_sport -> {
                    openFragment(VideosFragment.create(2))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        nav_view.setOnNavigationItemSelectedListener(itemSelectedListener)

        nav_view.selectedItemId = R.id.nav_movies

        getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            no_internet_warning.apply {
                when (isConnected) {
                    true -> if (isVisible) gone()
                    false -> if (isGone) visible()
                }
            }
        }
    }

    override fun onBackPressed() {
        when (nav_view.selectedItemId) {
            R.id.nav_sport -> nav_view.selectedItemId = R.id.nav_tv
            R.id.nav_tv -> nav_view.selectedItemId = R.id.nav_movies
            R.id.nav_movies -> finish()
        }
    }
}