package com.icarie.lbc.ui.albums

import com.icarie.domain.models.Album

data class UIAlbumData(
    val title: String,
    val albums: List<Album>
)