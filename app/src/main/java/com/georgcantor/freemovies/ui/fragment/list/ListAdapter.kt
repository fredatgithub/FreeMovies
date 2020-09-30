package com.georgcantor.freemovies.ui.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.freemovies.R
import com.georgcantor.freemovies.model.response.Item
import com.georgcantor.freemovies.util.loadImage
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(private val items: List<Item>) :
    ListAdapter<Item, com.georgcantor.freemovies.ui.fragment.list.ListAdapter.ListViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem

        override fun areContentsTheSame(old: Item, new: Item) = old.id == new.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.context.loadImage(
            item.snippet?.thumbnails?.standard?.url,
            holder.image
        )
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.image
    }
}