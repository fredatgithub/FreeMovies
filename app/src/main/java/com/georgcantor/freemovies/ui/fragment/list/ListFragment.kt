package com.georgcantor.freemovies.ui.fragment.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel by inject<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.videos.observe(viewLifecycleOwner) {
            recycler_view.adapter = ListAdapter(it)
        }
    }
}