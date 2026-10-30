package com.example.playlist_maker_android_poberezhnyidmitry.data.di

import com.example.playlist_maker_android_poberezhnyidmitry.data.NetworkClient
import com.example.playlist_maker_android_poberezhnyidmitry.data.network.RetrofitNetworkClient
import com.example.playlist_maker_android_poberezhnyidmitry.data.network.TracksRepositoryImpl
import com.example.playlist_maker_android_poberezhnyidmitry.domain.api.TracksRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<NetworkClient> {
        RetrofitNetworkClient()
    }
    factory<TracksRepository> {
        TracksRepositoryImpl(get())
    }
}
