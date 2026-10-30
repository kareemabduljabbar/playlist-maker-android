package com.example.playlist_maker_android_poberezhnyidmitry.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.playlist_maker_android_poberezhnyidmitry.R
import com.example.playlist_maker_android_poberezhnyidmitry.presentation.TrackViewModel
import com.example.playlist_maker_android_poberezhnyidmitry.ui.search.DetailsScreen
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlue
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPWhite
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.PlaylistmakerandroidPoberezhnyiDmitryTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackActivity : ComponentActivity() {

    private val viewModel: TrackViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaylistmakerandroidPoberezhnyiDmitryTheme {
                TrackScreen(
                    viewModel = viewModel,
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@Composable
private fun TrackScreen(
    viewModel: TrackViewModel,
    onBackClick: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()

    when (val state = screenState) {
        is TrackViewModel.TrackScreenState.Initial -> {
            LoadingContent(
                viewModel = viewModel,
                text = stringResource(R.string.press_to_load),
                showProgressBar = false
            )
        }

        is TrackViewModel.TrackScreenState.Loading -> {
            LoadingContent(
                viewModel = viewModel,
                text = stringResource(R.string.loading),
                showProgressBar = true
            )
        }

        is TrackViewModel.TrackScreenState.Content -> {
            DetailsScreen(
                track = state.track,
                onBackClick = onBackClick
            )
        }
    }
}

@Composable
private fun LoadingContent(
    viewModel: TrackViewModel,
    text: String,
    showProgressBar: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(YPWhite)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { viewModel.loadTrack(trackId = 123L) },
            colors = ButtonDefaults.buttonColors(containerColor = YPBlue)
        ) {
            Text(text = stringResource(R.string.load_track))
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (showProgressBar) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(text = text)
    }
}
