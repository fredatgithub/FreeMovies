package com.georgcantor.freemovies.ui.fragment.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.util.loadImage
import kotlinx.android.synthetic.main.item_video.view.*

class FavoritesAdapter(
    private val items: List<FavVideo>,
    private val clickListener: (FavVideo) -> Unit
) : ListAdapter<FavVideo, FavoritesAdapter.FavoritesViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<FavVideo>() {
        override fun areItemsTheSame(oldItem: FavVideo, newItem: FavVideo) = oldItem == newItem

        override fun areContentsTheSame(old: FavVideo, new: FavVideo) = old.videoId == new.videoId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoritesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            item.url.let {
                itemView.context.loadImage(it, holder.image)
            }
            itemView.setOnClickListener { clickListener(item) }
        }
    }

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
    }
}