package com.example.pexels.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexels.domain.models.FeaturedCollectionDomain
import com.example.pexels.domain.models.PhotoDomain
import com.example.pexels.domain.repository.DataRepository
import com.example.pexels.utils.Constants.DEFAULT_PER_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    val photos = MutableStateFlow<List<PhotoDomain>>(listOf())
    val isErrorState = MutableStateFlow<Boolean>(false)
    val featuredState = MutableStateFlow<List<FeaturedCollectionDomain>>(listOf())
    val searchText = MutableStateFlow<String>("")
    val selectedFeaturedCollectionId = MutableStateFlow<String>("")


    init {
        viewModelScope.launch {
            getFeaturedCollections()
            getCuratedPhotos()
        }
    }
    fun changeSearchText(text: String) {
        searchText.value = text
        selectedFeaturedCollectionId.value = ""
    }

    fun changeSelectedId(id: String) {
        selectedFeaturedCollectionId.value = id
    }

    suspend fun searchPhotos(text: String) {
        val newPhotosList = dataRepository.getPhotosList(
            query = text,
            page = 1,
            per_page = DEFAULT_PER_PAGE
        )
        isErrorState.value = newPhotosList.isEmpty()
        photos.emit(newPhotosList)
    }

    private suspend fun getFeaturedCollections() {
        val featuredCollections = dataRepository.getFeaturedCollectionsList(
            page = 1,
            per_page = 7
        )
        featuredState.emit(featuredCollections)
    }

    suspend fun getCuratedPhotos() {
        val curatedPhotos = dataRepository.getCuratedPhotosList(
            per_page = DEFAULT_PER_PAGE,
            page = 1
        )
        isErrorState.value = curatedPhotos.isEmpty()
        photos.emit(curatedPhotos)
    }


}