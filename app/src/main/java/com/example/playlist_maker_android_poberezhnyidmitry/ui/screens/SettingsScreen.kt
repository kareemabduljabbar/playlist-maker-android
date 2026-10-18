package com.example.playlist_maker_android_poberezhnyidmitry.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_poberezhnyidmitry.R
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPBlack
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPTextGray
import com.example.playlist_maker_android_poberezhnyidmitry.ui.theme.YPWhite

private const val SETTINGS_ROW_HEIGHT = 61

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val shareMessage = stringResource(R.string.share_message)
    val supportEmail = stringResource(R.string.support_email)
    val emailSubject = stringResource(R.string.email_subject)
    val emailBody = stringResource(R.string.email_body)
    val userAgreementLink = stringResource(R.string.user_agreement_link)

    var isDarkThemeEnabled by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(YPWhite)
            .statusBarsPadding()
    ) {
        TopBar(
            title = stringResource(R.string.settings),
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        DarkThemeRow(
            isChecked = isDarkThemeEnabled,
            onCheckedChange = { isChecked -> isDarkThemeEnabled = isChecked }
        )

        SettingsRow(
            iconRes = R.drawable.ic_share,
            titleRes = R.string.share_app,
            onClick = { shareApp(context = context, message = shareMessage) }
        )

        SettingsRow(
            iconRes = R.drawable.ic_support,
            titleRes = R.string.write_to_support,
            onClick = {
                writeToSupport(
                    context = context,
                    email = supportEmail,
                    subject = emailSubject,
                    body = emailBody
                )
            }
        )

        SettingsRow(
            iconRes = R.drawable.ic_arrow_forward,
            titleRes = R.string.user_agreement,
            onClick = { openUserAgreement(context = context, link = userAgreementLink) }
        )
    }
}

@Composable
private fun DarkThemeRow(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SETTINGS_ROW_HEIGHT.dp)
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.dark_theme),
            color = YPBlack,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 1
        )

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun SettingsRow(
    iconRes: Int,
    titleRes: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SETTINGS_ROW_HEIGHT.dp)
            .clickable(onClick = onClick)
            .padding(start = 16.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(titleRes),
            color = YPBlack,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 1
        )

        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = YPTextGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

private fun shareApp(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(shareIntent, null))
}

private fun writeToSupport(
    context: Context,
    email: String,
    subject: String,
    body: String
) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    context.startActivity(emailIntent)
}

private fun openUserAgreement(context: Context, link: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    context.startActivity(browserIntent)
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
