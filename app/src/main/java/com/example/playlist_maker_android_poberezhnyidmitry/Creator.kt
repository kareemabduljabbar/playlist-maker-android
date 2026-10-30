package com.example.playlist_maker_android_poberezhnyidmitry

import com.example.playlist_maker_android_poberezhnyidmitry.data.network.RetrofitNetworkClient
import com.example.playlist_maker_android_poberezhnyidmitry.data.network.TracksRepositoryImpl
import com.example.playlist_maker_android_poberezhnyidmitry.domain.api.TrackSearchInteractor
import com.example.playlist_maker_android_poberezhnyidmitry.domain.api.TracksRepository
import com.example.playlist_maker_android_poberezhnyidmitry.domain.impl.TrackSearchInteractorImpl

object Creator {
    private fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideTrackSearchInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(getTracksRepository())
    }
}
