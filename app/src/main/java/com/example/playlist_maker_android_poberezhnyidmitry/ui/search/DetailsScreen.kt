package com.example.playlist_maker_android_poberezhnyidmitry.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_poberezhnyidmitry.R
import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlack
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPLightGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPTextGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPWhite

@Composable
fun DetailsScreen(
    track: Track,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(YPWhite)
            .statusBarsPadding()
    ) {
        TopBar(
            title = stringResource(R.string.track),
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(248.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(YPLightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_track_placeholder),
                    contentDescription = stringResource(R.string.track_placeholder),
                    tint = YPTextGray,
                    modifier = Modifier.size(72.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = track.trackName,
                modifier = Modifier.fillMaxWidth(),
                color = YPBlack,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = track.artistName,
                modifier = Modifier.fillMaxWidth(),
                color = YPBlack,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.duration),
                    color = YPTextGray,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = track.trackTime,
                    color = YPBlack,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(R.string.back),
                tint = YPBlack,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = title,
            modifier = Modifier.padding(start = 12.dp),
            color = YPBlack,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
