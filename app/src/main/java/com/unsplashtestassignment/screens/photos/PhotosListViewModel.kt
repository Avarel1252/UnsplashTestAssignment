package com.unsplashtestassignment.screens.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsplashtestassignment.data.PhotosRepository
import com.unsplashtestassignment.locateLazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class PhotosListViewModel : ViewModel() {
    private val photosRepository: PhotosRepository by locateLazy()

    val photos = photosRepository.getPhotos().asLiveDataFlow()
    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch { photosRepository.deleteAllPhotos() }
    }

    fun uploadNewPhotos(count: Int) {
        viewModelScope.launch { photosRepository.uploadNewPhotos(count) }
    }
}
