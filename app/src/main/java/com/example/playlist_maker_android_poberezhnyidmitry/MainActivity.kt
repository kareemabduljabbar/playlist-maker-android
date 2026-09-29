package com.example.playlist_maker_android_poberezhnyidmitry

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                onSearchClick = {
                    startActivity(Intent(this, SearchActivity::class.java))
                },
                onPlaylistsClick = {},
                onFavoritesClick = {},
                onSettingsClick = {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
            )
        }
    }
}

private val BgBlue = Color(0xFF3772E7)
private val YPBlack = Color(0xFF1A1B22)
private val YPTextGray = Color(0xFFAEAFB4)
private val YPWhite = Color(0xFFFFFFFF)

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onPlaylistsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgBlue)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    color = YPWhite,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = YPWhite,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    MenuItem(
                        iconRes = R.drawable.ic_search,
                        text = stringResource(R.string.search),
                        onClick = onSearchClick
                    )
                    MenuItem(
                        iconRes = R.drawable.ic_playlist,
                        text = stringResource(R.string.playlists),
                        onClick = onPlaylistsClick
                    )
                    MenuItem(
                        iconRes = R.drawable.ic_favorite,
                        text = stringResource(R.string.favorites),
                        onClick = onFavoritesClick
                    )
                    MenuItem(
                        iconRes = R.drawable.ic_settings,
                        text = stringResource(R.string.settings),
                        onClick = onSettingsClick
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    iconRes: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                color = YPBlack,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}