package com.example.learn.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.learn.R
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun Dialog() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var isShowAlertDialog by remember { mutableStateOf(false) }

    var isShowBottomSheetDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(15.dp)) {
        Button(
            onClick = { isShowAlertDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Text(
                text = "AlertDialog"
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { isShowBottomSheetDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Text(
                text = "BottomSheetDialog"
            )
        }
    }

    AlertDialog(isShowAlertDialog, screenWidth, onDismissRequest = { isShowAlertDialog = false })

    BottomSheetDialog(
        isShowBottomSheetDialog,
        onDismissRequest = { isShowBottomSheetDialog = false })
}

@Composable
private fun AlertDialog(isShowDialog: Boolean, screenWidth: Dp, onDismissRequest: () -> Unit) {
    if (isShowDialog) {
        AlertDialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            modifier = Modifier.widthIn(max = screenWidth - 80.dp),
            onDismissRequest = { onDismissRequest() },
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
                        .clickable { onDismissRequest() },
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(R.string.dialog_button_ok),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { onDismissRequest() },
                )
            },
        )
    }
}

@Composable
private fun BottomSheetDialog(isShowDialog: Boolean, onDismissRequest: () -> Unit) {
    BottomSheetDialog(
        modifier = Modifier,
        visible = isShowDialog,
        cancelable = true,
        canceledOnTouchOutside = true,
        backgroundColor = Color(color = 0x99000000),
        shape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp
        ),
        onDismissRequest = onDismissRequest
    ) {
        BottomSheetDialogContent(onDismissRequest = onDismissRequest)
    }
}

@Composable
private fun BottomSheetDialogContent(onDismissRequest: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.6f)
            .background(color = Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onClick = onDismissRequest
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "dismissDialog",
                fontSize = 16.sp
            )
        }
    }
}

