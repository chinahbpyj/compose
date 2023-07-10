package com.example.learn.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.learn.R

@Composable
fun Dialog() {
    val configuration = LocalConfiguration.current

    var isShowDialog by remember { mutableStateOf(true) }

    if (isShowDialog) {
        AlertDialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
            onDismissRequest = { isShowDialog = false },
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    text = stringResource(R.string.dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            text = {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    Divider(Modifier.padding(vertical = 10.dp))

                    Text(
                        text = stringResource(R.string.text_description),
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            lineHeight = 24.0.sp,
                            letterSpacing = 0.sp,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Divider(Modifier.padding(vertical = 10.dp))
                }
            },
            dismissButton = {
                Text(
                    text = stringResource(R.string.dialog_button_cancel),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { isShowDialog = false },
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(R.string.dialog_button_ok),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { isShowDialog = false },
                )
            },
        )
    }

}