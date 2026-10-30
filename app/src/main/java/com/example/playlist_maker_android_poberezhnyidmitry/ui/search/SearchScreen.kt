package com.example.playlist_maker_android_poberezhnyidmitry.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_poberezhnyidmitry.R
import com.example.playlist_maker_android_poberezhnyidmitry.domain.model.Track
import com.example.playlist_maker_android_poberezhnyidmitry.presentation.SearchState
import com.example.playlist_maker_android_poberezhnyidmitry.presentation.SearchViewModel
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlack
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlue
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPCursorBlue
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPLightGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPTextGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPWhite

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigateToDetailScreen: (Track) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenState by searchViewModel.searchScreenState.collectAsState()
    var query by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(YPWhite)
            .statusBarsPadding()
    ) {
        TopBar(
            title = stringResource(R.string.search),
            onBackClick = onBackClick
        )

        SearchField(
            query = query,
            onQueryChange = { newQuery -> query = newQuery },
            onSearchClick = {
                if (query.isNotBlank()) {
                    searchViewModel.searchTracks(query)
                    focusManager.clearFocus()
                }
            },
            onClearClick = {
                query = ""
                searchViewModel.clearSearch()
            }
        )

        SearchResults(
            screenState = screenState,
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            onTrackClick = navigateToDetailScreen
        )
    }
}

@Composable
private fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(
                    color = YPLightGray,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                fontSize = 16.sp,
                lineHeight = 19.sp,
                color = YPBlack
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.search),
                    color = YPTextGray,
                    fontSize = 16.sp,
                    lineHeight = 19.sp,
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = YPTextGray,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable(onClick = onSearchClick)
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = onClearClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear),
                            contentDescription = stringResource(R.string.clear_search_query),
                            tint = YPTextGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClick() }),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = YPBlack,
                unfocusedTextColor = YPBlack,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = YPCursorBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedPlaceholderColor = YPTextGray,
                unfocusedPlaceholderColor = YPTextGray,
                focusedLeadingIconColor = YPTextGray,
                unfocusedLeadingIconColor = YPTextGray,
                focusedTrailingIconColor = YPTextGray,
                unfocusedTrailingIconColor = YPTextGray
            )
        )
    }
}

@Composable
private fun SearchResults(
    screenState: SearchState,
    modifier: Modifier,
    onTrackClick: (Track) -> Unit
) {
    when (screenState) {
        is SearchState.Initial -> Unit

        is SearchState.Searching -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = YPBlue
                )
            }
        }

        is SearchState.Success -> {
            val tracks = screenState.foundList
            if (tracks.isEmpty()) {
                InformerState(
                    titleText = stringResource(R.string.no_tracks_found),
                    modifier = modifier
                )
            } else {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    items(tracks.size) { index ->
                        TrackListItem(
                            track = tracks[index],
                            onClick = { onTrackClick(tracks[index]) }
                        )
                    }
                }
            }
        }

        is SearchState.Fail -> {
            InformerState(
                titleText = screenState.error,
                subtitleText = stringResource(R.string.check_connection),
                modifier = modifier
            )
        }
    }
}

@Composable
private fun InformerState(
    titleText: String,
    subtitleText: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleText,
            fontSize = 18.sp,
            color = YPBlack,
            textAlign = TextAlign.Center
        )

        subtitleText?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                fontSize = 14.sp,
                color = YPTextGray,
                textAlign = TextAlign.Center
            )
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
