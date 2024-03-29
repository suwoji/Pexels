package com.example.pexels.data.api

import com.example.pexels.data.models.FeaturedCollectionsResponse
import com.example.pexels.data.models.PexelsResponse
import com.example.pexels.data.models.Photo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Header("Authorization") apiKey: String,
        @Path("id") id: Int
    ): Photo

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Header("Authorization") apiKey: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PexelsResponse

    @GET("search")
    suspend fun getPhotos(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): PexelsResponse

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Header("Authorization") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): FeaturedCollectionsResponse

}