package com.example.pexels.domain.repository

import com.example.pexels.data.models.Photo
import com.example.pexels.domain.models.PhotoDomain

interface DatabaseRepository {

    suspend fun getPhotoById(id: Int): PhotoDomain?

    suspend fun getPhotosList(): List<PhotoDomain>

    suspend fun addFavouritePhoto(photo: Photo)

    suspend fun removeFavouritePhoto(photo: Photo)

    suspend fun countPhotosById(id: Int): Int
}