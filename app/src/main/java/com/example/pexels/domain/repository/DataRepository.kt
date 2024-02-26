package com.example.pexels.domain.repository

import com.example.pexels.domain.models.FeaturedCollectionDomain
import com.example.pexels.domain.models.PhotoDomain

interface DataRepository {
    suspend fun getFeaturedCollectionsList(
        page: Int,
        per_page: Int
    ): List<FeaturedCollectionDomain>

    suspend fun getPhotosList(
        query: String,
        per_page: Int,
        page: Int
    ): List<PhotoDomain>

    suspend fun getCuratedPhotosList(
        per_page: Int,
        page: Int
    ): List<PhotoDomain>

    suspend fun getPhotoById(
        id: Int
    ): PhotoDomain?
}