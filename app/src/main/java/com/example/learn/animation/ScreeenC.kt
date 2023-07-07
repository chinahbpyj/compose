package com.example.learn.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.learn.view.TopBarViewOne

@Composable
fun ScreenC(
    onPreviousPressed: () -> Unit
) {
    Column() {
        TopBarViewOne("ScreenC", onPreviousPressed)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFCCC2DC)),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}