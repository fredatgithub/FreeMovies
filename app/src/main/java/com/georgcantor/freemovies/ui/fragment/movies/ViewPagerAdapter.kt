package com.georgcantor.freemovies.ui.fragment.movies

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.georgcantor.freemovies.ui.fragment.favorites.FavoritesFragment
import com.georgcantor.freemovies.ui.fragment.list.ListFragment
import com.georgcantor.freemovies.util.Constants.COMEDY_PAGE
import com.georgcantor.freemovies.util.Constants.HORROR_PAGE
import com.georgcantor.freemovies.util.Constants.THRILLER_PAGE

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        THRILLER_PAGE to { ListFragment() },
        HORROR_PAGE to { FavoritesFragment() },
        COMEDY_PAGE to { ListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}