package com.georgcantor.freemovies.ui.fragment.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.model.remote.response.Item
import com.georgcantor.freemovies.util.Constants.VIDEO_ITEM
import com.georgcantor.freemovies.util.loadImage
import com.georgcantor.freemovies.util.openFragment
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.ext.android.inject

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val viewModel by inject<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getParcelable(VIDEO_ITEM) as Item?)?.let { item ->
            item.snippet?.thumbnails?.standard?.url?.let { url -> context?.loadImage(url, image) }
            title.text = item.snippet?.title
            description.text = item.snippet?.description

            viewModel.checkIsFavorite(item)

            viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
                fab_fav.setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)

                fab_fav.setOnClickListener {
                    when {
                        isFavorite -> viewModel.removeFromFavorites(item)
                        else -> viewModel.addToFavorites(item)
                    }
                }
            }

            fab_play.setOnClickListener {
                (requireActivity() as AppCompatActivity)
                    .openFragment(VideoFragment.create(item.snippet?.resourceId?.videoId), false)
            }
        }
    }
}