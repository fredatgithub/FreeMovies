package com.georgcantor.freemovies.ui.fragment.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tab_layout, view_pager) { tab, _ ->
            tab.view.isClickable = false
        }.attach()
    }
}