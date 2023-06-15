package com.icarie.lbc.ui.albums

import com.icarie.domain.utils.StringRetriever
import com.icarie.domain.models.Album
import com.icarie.lbc.R
import javax.inject.Inject

interface AlbumTransformer {
    operator fun invoke(albums: List<Album>): UIAlbumData
}

class AlbumTransformerImpl @Inject constructor(
    private val stringRetriever: StringRetriever
) : AlbumTransformer {

    override fun invoke(albums: List<Album>): UIAlbumData = UIAlbumData(
        title = stringRetriever.getString(R.string.album_count_title, albums.size),
        albums = albums
    )
}
