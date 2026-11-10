package com.example.playlist_maker_android_poberezhnyidmitry.domain.impl

import com.example.playlist_maker_android_poberezhnyidmitry.domain.api.TrackSearchInteractor
import com.example.playlist_maker_android_poberezhnyidmitry.domain.api.TracksRepository
import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track

class TrackSearchInteractorImpl(
    private val repository: TracksRepository,
) : TrackSearchInteractor {

    override fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }

    override suspend fun loadSomeData(): List<Track> {
        return repository.loadSomeData()
    }
}
