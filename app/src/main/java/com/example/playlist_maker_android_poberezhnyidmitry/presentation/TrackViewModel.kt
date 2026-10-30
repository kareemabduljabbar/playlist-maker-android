package com.example.playlist_maker_android_poberezhnyidmitry.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playlist_maker_android_poberezhnyidmitry.domain.api.TracksRepository
import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(
    private val repository: TracksRepository,
) : ViewModel() {

    sealed class TrackScreenState {
        object Initial : TrackScreenState()
        object Loading : TrackScreenState()
        data class Content(val track: Track) : TrackScreenState()
    }

    private val _screenState = MutableStateFlow<TrackScreenState>(TrackScreenState.Initial)
    val screenState: StateFlow<TrackScreenState> = _screenState.asStateFlow()

    fun loadTrack(trackId: Long) {
        viewModelScope.launch {
            _screenState.value = TrackScreenState.Loading
            val track = repository.loadTrackDetail(trackId)
            _screenState.value = TrackScreenState.Content(track)
        }
    }
}
