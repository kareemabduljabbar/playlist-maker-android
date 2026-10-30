package com.example.playlist_maker_android_poberezhnyidmitry

import android.app.Application
import com.example.playlist_maker_android_poberezhnyidmitry.data.di.repositoryModule
import com.example.playlist_maker_android_poberezhnyidmitry.presentation.SearchViewModel
import com.example.playlist_maker_android_poberezhnyidmitry.presentation.TrackViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            val viewModelModule = module {
                viewModel { SearchViewModel(get()) }
                viewModel { TrackViewModel(get()) }
            }
            modules(repositoryModule, viewModelModule)
        }
    }
}
