package com.georgcantor.freemovies.ui.fragment.videos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.ui.fragment.list.ListFragment
import com.georgcantor.freemovies.util.Constants.AFTER_FOOTBALL
import com.georgcantor.freemovies.util.Constants.COMEDY
import com.georgcantor.freemovies.util.Constants.FIRST_TAB
import com.georgcantor.freemovies.util.Constants.GERMANY
import com.georgcantor.freemovies.util.Constants.HELL_KITCHEN
import com.georgcantor.freemovies.util.Constants.HORROR
import com.georgcantor.freemovies.util.Constants.MAD_GIRLS
import com.georgcantor.freemovies.util.Constants.REVIZORRO
import com.georgcantor.freemovies.util.Constants.RUSSIA
import com.georgcantor.freemovies.util.Constants.SECOND_TAB
import com.georgcantor.freemovies.util.Constants.THIRD_TAB
import com.georgcantor.freemovies.util.Constants.THRILLER
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_videos.*

class VideosFragment : Fragment(R.layout.fragment_videos) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_pager.adapter = ViewPagerAdapter(this,
            when (findNavController().currentDestination?.id) {
                R.id.nav_movies -> {
                    mapOf(
                        FIRST_TAB to { ListFragment.create(THRILLER) },
                        SECOND_TAB to { ListFragment.create(HORROR) },
                        THIRD_TAB to { ListFragment.create(COMEDY) }
                    )
                }
                R.id.nav_tv -> {
                    mapOf(
                        FIRST_TAB to { ListFragment.create(HELL_KITCHEN) },
                        SECOND_TAB to { ListFragment.create(MAD_GIRLS) },
                        THIRD_TAB to { ListFragment.create(REVIZORRO) }
                    )
                }
                else -> {
                    mapOf(
                        FIRST_TAB to { ListFragment.create(RUSSIA) },
                        SECOND_TAB to { ListFragment.create(GERMANY) },
                        THIRD_TAB to { ListFragment.create(AFTER_FOOTBALL) }
                    )
                }
            }
        )

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = when (findNavController().currentDestination?.id) {
                R.id.nav_movies -> {
                    when (position) {
                        0 -> getString(R.string.thriller)
                        1 -> getString(R.string.horror)
                        else -> getString(R.string.comedy)
                    }
                }
                R.id.nav_tv -> {
                    when (position) {
                        0 -> getString(R.string.kitchen)
                        1 -> getString(R.string.bad_girls)
                        else -> getString(R.string.revizorro)
                    }
                }
                else -> {
                    when (position) {
                        0 -> getString(R.string.russia)
                        1 -> getString(R.string.germany)
                        else -> getString(R.string.after_football)
                    }
                }
            }
        }.attach()
    }
}