package com.example.playlist_maker_android_poberezhnyidmitry.domain.api

import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track

interface TrackSearchInteractor {
    fun searchTracks(expression: String): List<Track>
    suspend fun loadSomeData(): List<Track>
}
