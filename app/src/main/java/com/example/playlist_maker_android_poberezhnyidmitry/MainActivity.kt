package com.example.playlist_maker_android_poberezhnyidmitry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.playlist_maker_android_poberezhnyidmitry.navigation.PlaylistHost
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.PlaylistmakerandroidPoberezhnyiDmitryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistmakerandroidPoberezhnyiDmitryTheme {
                PlaylistMakerApp()
            }
        }
    }
}

@Composable
private fun PlaylistMakerApp() {
    val navController = rememberNavController()

    PlaylistHost(navController = navController)
}
