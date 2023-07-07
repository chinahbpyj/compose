package com.example.learn.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.learn.view.TopBarViewOne

@Composable
fun ScreenA(
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    Column() {
        TopBarViewOne("ScreenA", onPreviousPressed)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFD0BCFF)),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onNextPressed,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = "ScreenB"
                )
            }
        }
    }
}