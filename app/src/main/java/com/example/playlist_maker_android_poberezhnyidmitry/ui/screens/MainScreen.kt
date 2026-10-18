package com.example.playlist_maker_android_poberezhnyidmitry.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_poberezhnyidmitry.R
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlack
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlue
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPTextGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPWhite

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onPlaylistsClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(YPBlue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
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
private fun MenuItem(
    iconRes: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = YPBlack,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                color = YPBlack,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = null,
            tint = YPTextGray,
            modifier = Modifier.size(24.dp)
        )
    }
}
