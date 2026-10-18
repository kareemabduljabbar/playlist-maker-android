package com.example.playlist_maker_android_poberezhnyidmitry.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_poberezhnyidmitry.R
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPCursorBlue
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlack
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPLightGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPTextGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPWhite

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }

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
            onSearchClick = {},
            onClearClick = { query = "" }
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
