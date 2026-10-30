package com.example.playlist_maker_android_poberezhnyidmitry.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track
import com.example.playlist_maker_android_poberezhnyidmitry.presentation.SearchViewModel
import com.example.playlist_maker_android_poberezhnyidmitry.ui.main.MainScreen
import com.example.playlist_maker_android_poberezhnyidmitry.ui.search.DetailsScreen
import com.example.playlist_maker_android_poberezhnyidmitry.ui.search.SearchScreen
import com.example.playlist_maker_android_poberezhnyidmitry.ui.setting.SettingsScreen
import com.google.gson.Gson

@Composable
fun PlaylistHost(
    navController: NavHostController,
    gson: Gson,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MAIN.route
    ) {
        composable(route = Screen.MAIN.route) {
            MainScreen(
                onSearchClick = { navController.navigateToScreen(Screen.SEARCH) },
                onPlaylistsClick = {},
                onFavoritesClick = {},
                onSettingsClick = { navController.navigateToScreen(Screen.SETTINGS) }
            )
        }

        composable(route = Screen.SEARCH.route) {
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetailScreen = { track ->
                    val trackJson = Uri.encode(gson.toJson(track))
                    navController.navigate("details/$trackJson")
                },
                onBackClick = { navController.navigateBack() }
            )
        }

        composable(route = Screen.DETAILS.route) { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            if (trackJson != null) {
                val track = gson.fromJson(trackJson, Track::class.java)
                DetailsScreen(
                    track = track,
                    onBackClick = { navController.navigateBack() }
                )
            }
        }

        composable(route = Screen.SETTINGS.route) {
            SettingsScreen(
                onBackClick = { navController.navigateBack() }
            )
        }
    }
}

private fun NavController.navigateToScreen(screen: Screen) {
    navigate(screen.route)
}

private fun NavController.navigateBack() {
    popBackStack()
}
