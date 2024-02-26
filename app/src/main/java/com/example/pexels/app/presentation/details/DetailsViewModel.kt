package com.example.pexels.app.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexels.data.models.Photo
import com.example.pexels.data.models.asPhoto
import com.example.pexels.domain.models.PhotoDomain
import com.example.pexels.domain.repository.DataRepository
import com.example.pexels.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val networkDataRepository: DataRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    val selectedPhoto = MutableStateFlow<PhotoDomain?>(null)
    val isPhotoFavourite = MutableStateFlow<Boolean>(false)

    fun getPhotoFromHomeScreen(id: Int?) {
        if (id != null) {
            viewModelScope.launch {
                val photo = networkDataRepository.getPhotoById(id)
                selectedPhoto.emit(photo)
                countPhotosById(id)
            }
        }

    }

    fun getPhotoFromBookmarksScreen(id: Int?) {
        if (id != null) {
            viewModelScope.launch {
                val photo = databaseRepository.getPhotoById(id)
                selectedPhoto.emit(photo)
                countPhotosById(id)
            }
        }
    }

    fun onFavouriteButtonClicked(photoDomain: PhotoDomain) {
        val photo = photoDomain.asPhoto()
        viewModelScope.launch {
            if (isPhotoFavourite.value) {
                removeFromFavourites(photo)
                isPhotoFavourite.value = false
            } else {
                addToFavourites(photo)
                isPhotoFavourite.value = true
            }
        }
    }

    suspend fun addToFavourites(photo: Photo) {
        viewModelScope.launch {
            databaseRepository.addFavouritePhoto(photo)
        }

    }

    suspend fun removeFromFavourites(photo: Photo) {
        viewModelScope.launch {
            databaseRepository.removeFavouritePhoto(photo)
        }
    }

    suspend fun countPhotosById(id: Int) {
        viewModelScope.launch {
            val count = databaseRepository.countPhotosById(id)
            isPhotoFavourite.value = count != 0
        }
    }


}