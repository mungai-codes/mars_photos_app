package com.mungaicodes.marsphotos.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungaicodes.marsphotos.model.MarsPhoto
import com.mungaicodes.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    var marsPhotos: List<MarsPhoto> by mutableStateOf(listOf())

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            val photos = MarsApi.apiService.getPhotos()
            marsPhotos = photos
        }
    }

}