package com.georgcantor.freemovies.ui.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.model.local.FavVideo
import com.georgcantor.freemovies.model.remote.response.Item
import com.georgcantor.freemovies.ui.fragment.list.ListAdapter.ListViewHolder
import com.georgcantor.freemovies.util.loadImage
import kotlinx.android.synthetic.main.item_video.view.*

class ListAdapter(
    private val items: List<Item>,
    private val clickListener: (FavVideo) -> Unit
) : ListAdapter<Item, ListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

        override fun areContentsTheSame(old: Item, new: Item) = old.id == new.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            item.snippet?.thumbnails?.standard?.url?.let {
                itemView.context.loadImage(it, holder.image)
            }
            itemView.setOnClickListener {
                clickListener(
                    FavVideo(
                        item.snippet?.title,
                        item.snippet?.description,
                        item.snippet?.resourceId?.videoId,
                        item.snippet?.thumbnails?.standard?.url
                    )
                )
            }
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
    }
}