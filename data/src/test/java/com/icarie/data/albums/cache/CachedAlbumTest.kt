package com.icarie.data.albums.cache

import com.icarie.domain.albums.Album
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CachedAlbumTest {

    @Test
    fun `test mapping album to CachedAlbum back and forth keeps properties value`() {
        val album: Album = fakeAlbum()
        album.toCachedAlbum()
            .verifyProperties(album)
            .toAlbum()
            .verifyProperties(album)
    }

    private fun CachedAlbum.verifyProperties(album: Album): CachedAlbum {
        assertEquals(albumId, album.albumId)
        assertEquals(id, album.id)
        assertEquals(title, album.title)
        assertEquals(url, album.url)
        assertEquals(thumbnailUrl, album.thumbnailUrl)
        return this
    }

    private fun Album.verifyProperties(album: Album): Album {
        assertEquals(albumId, album.albumId)
        assertEquals(id, album.id)
        assertEquals(title, album.title)
        assertEquals(url, album.url)
        assertEquals(thumbnailUrl, album.thumbnailUrl)
        return this
    }

    private fun fakeAlbum() = Album(
        albumId = 1,
        id = 2,
        title = "title",
        url = "url",
        thumbnailUrl = "thumbnailUrl"
    )
}