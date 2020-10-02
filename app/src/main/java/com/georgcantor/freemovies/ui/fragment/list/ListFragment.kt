package com.georgcantor.freemovies.ui.fragment.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.ui.fragment.details.DetailsFragment
import com.georgcantor.freemovies.util.Constants.PLAYLIST_ID
import com.georgcantor.freemovies.util.Constants.VIDEO_ITEM
import com.georgcantor.freemovies.util.openFragment
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.ext.android.inject

class ListFragment : Fragment(R.layout.fragment_list) {

    companion object {
        fun create(id: String) = ListFragment().apply {
            arguments = Bundle().apply { putString(PLAYLIST_ID, id) }
        }
    }

    private val viewModel by inject<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(PLAYLIST_ID)?.let { viewModel.getVideos(it) }

        viewModel.videos.observe(viewLifecycleOwner) {
            recycler_view.setHasFixedSize(true)
            recycler_view.adapter = ListAdapter(it) {
                (activity as AppCompatActivity).openFragment(DetailsFragment().apply {
                    arguments = Bundle().apply { putParcelable(VIDEO_ITEM, it) }
                })
            }
        }
    }
}