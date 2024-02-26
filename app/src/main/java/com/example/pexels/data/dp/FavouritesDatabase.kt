package com.example.pexels.data.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pexels.app.di.Dao
import com.example.pexels.data.models.Photo

@Database(entities = [Photo::class], version = 1)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract val dao: Dao
}