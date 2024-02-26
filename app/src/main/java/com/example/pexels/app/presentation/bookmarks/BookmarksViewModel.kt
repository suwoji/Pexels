package com.example.pexels.app.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexels.domain.models.PhotoDomain
import com.example.pexels.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    val favouritePhotos = MutableStateFlow<List<PhotoDomain>>(listOf())
    val isErrorState = MutableStateFlow<Boolean>(false)

    init {
        viewModelScope.launch {
            getFavouritePhotos()
        }
    }

    suspend fun getFavouritePhotos() {
        val photos = databaseRepository.getPhotosList()
        favouritePhotos.emit(photos)
        isErrorState.value = photos.isEmpty()
    }

}