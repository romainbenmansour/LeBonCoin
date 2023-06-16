package com.icarie.lbc.ui.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.icarie.domain.albums.Album
import com.icarie.lbc.databinding.AlbumItemBinding

class AlbumAdapter(diffCallback: DiffUtil.ItemCallback<Album> = AlbumComparator) :
    PagingDataAdapter<Album, AlbumViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(AlbumItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

    suspend fun updateData(pagingData: PagingData<Album>) {
        submitData(pagingData)
    }
}

object AlbumComparator : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}
