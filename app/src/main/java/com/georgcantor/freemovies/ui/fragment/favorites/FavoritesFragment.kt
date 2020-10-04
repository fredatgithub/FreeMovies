package com.georgcantor.freemovies.ui.fragment.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject

class FavoritesFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by inject<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favorites.observe(viewLifecycleOwner) {
            recycler_view.setHasFixedSize(true)
            recycler_view.adapter = FavoritesAdapter(it) {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }
}