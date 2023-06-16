package com.icarie.lbc.ui.albums

import androidx.recyclerview.widget.RecyclerView
import com.icarie.domain.albums.Album
import com.icarie.lbc.databinding.AlbumItemBinding

class AlbumViewHolder(private val itemBinding: AlbumItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: Album?) {
        item?.let {
            itemBinding.album = item
            itemBinding.executePendingBindings()
        }
    }
}