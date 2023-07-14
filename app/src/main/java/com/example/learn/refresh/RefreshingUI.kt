package com.example.learn.refresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RefreshingUI(refreshingUIHeight: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(refreshingUIHeight)
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}