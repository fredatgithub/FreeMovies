package com.georgcantor.freemovies.ui.fragment.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.model.response.Item
import com.georgcantor.freemovies.util.Constants.VIDEO_ITEM
import com.georgcantor.freemovies.util.loadImage
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item: Item? = arguments?.getParcelable(VIDEO_ITEM)

        item?.let {
            it.snippet?.thumbnails?.standard?.url?.let { url -> context?.loadImage(url, image) }
            title.text = it.snippet?.title
            description.text = it.snippet?.description
        }
    }
}