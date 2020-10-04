package com.georgcantor.freemovies.ui.fragment.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.ui.fragment.favorites.FavoritesViewModel
import com.georgcantor.freemovies.util.Constants.VIDEO_ITEM
import com.georgcantor.freemovies.util.loadImage
import com.georgcantor.freemovies.util.openFragment
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getSharedViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getParcelable(VIDEO_ITEM) as FavVideo?)?.let { video ->
            video.url?.let { url -> context?.loadImage(url, image) }
            title.text = video.title
            description.text = video.description

            viewModel.checkIsFavorite(video)

            viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
                fab_fav.setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)

                fab_fav.setOnClickListener {
                    when {
                        isFavorite -> viewModel.removeFromFavorites(video.videoId)
                        else -> viewModel.addToFavorites(video)
                    }
                }
            }

            fab_play.setOnClickListener {
                (requireActivity() as AppCompatActivity)
                    .openFragment(VideoFragment.create(video.videoId), false)
            }
        }
    }
}