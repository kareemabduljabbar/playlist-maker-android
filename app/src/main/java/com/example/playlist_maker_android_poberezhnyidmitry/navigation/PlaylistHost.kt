package com.example.playlist_maker_android_poberezhnyidmitry.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.playlist_maker_android_poberezhnyidmitry.ui.screens.MainScreen
import com.example.playlist_maker_android_poberezhnyidmitry.ui.screens.SearchScreen
import com.example.playlist_maker_android_poberezhnyidmitry.ui.screens.SettingsScreen

@Composable
fun PlaylistHost(navController: NavHostController) {
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
                onBackClick = { navController.navigateBack() }
            )
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
