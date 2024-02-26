package com.example.pexels.app.di

import android.app.Application
import androidx.room.Room
import com.example.pexels.data.api.ApiService
import com.example.pexels.data.dp.FavouritesDatabase
import com.example.pexels.data.repository.DatabaseRepositoryImpl
import com.example.pexels.data.repository.NetworkDataRepositoryImpl
import com.example.pexels.domain.repository.DataRepository
import com.example.pexels.domain.repository.DatabaseRepository
import com.example.pexels.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataRepository(apiService: ApiService): DataRepository {
        return NetworkDataRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideFavouritesDatabase(app: Application): FavouritesDatabase {
        return Room.databaseBuilder(
            app,
            FavouritesDatabase::class.java,
            "favourites.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(db: FavouritesDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(db)
    }

}