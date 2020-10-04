package com.georgcantor.freemovies.ui.fragment.favorites

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.ui.fragment.details.DetailsFragment
import com.georgcantor.freemovies.util.Constants.VIDEO_ITEM
import com.georgcantor.freemovies.util.openFragment
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class FavoritesFragment : Fragment(R.layout.fragment_list) {

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getSharedViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favorites.observe(viewLifecycleOwner) {
            empty.visibility = if (it.isNullOrEmpty()) VISIBLE else GONE
            recycler_view.setHasFixedSize(true)
            recycler_view.adapter = FavoritesAdapter(it) {
                (activity as AppCompatActivity).openFragment(DetailsFragment().apply {
                    arguments = Bundle().apply { putParcelable(VIDEO_ITEM, it) }
                }, false)
            }
            refresh_layout.isRefreshing = false
        }

        refresh_layout.setOnRefreshListener { viewModel.getFavorites() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }
}