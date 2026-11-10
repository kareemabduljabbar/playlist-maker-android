package com.example.playlist_maker_android_poberezhnyidmitry.domain.api

import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track

interface TracksRepository {
    fun searchTracks(expression: String): List<Track>
    suspend fun loadSomeData(): List<Track>
    suspend fun loadTrackDetail(trackId: Long): Track
    suspend fun searchTracksContains(expression: String): List<Track>
}
