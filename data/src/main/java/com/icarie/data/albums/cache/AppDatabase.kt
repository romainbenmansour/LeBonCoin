package com.icarie.data.albums.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CachedAlbum::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cachedAlbumDao(): CachedAlbumDao
}