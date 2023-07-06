package com.example.learn.view

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextView(@StringRes title: Int) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextViewOne(title)

        Spacer(modifier = Modifier.height(30.dp))

        TextViewTwo(title)

        Spacer(modifier = Modifier.height(30.dp))

        TextViewThree(title)
    }
}

@Composable
fun TextViewOne(@StringRes title: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = title),
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 24.0.sp,
            letterSpacing = 0.sp,
        ),
        color = Color(0xFFFFFFFF),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF625b71),
                shape = RoundedCornerShape(8.0.dp)
            )
            .padding(vertical = 15.dp, horizontal = 15.dp)
    )
}

@Composable
fun TextViewTwo(@StringRes title: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = title),
        color = Color(0xFF6650a4),
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.0.sp,
            letterSpacing = 0.3.sp,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    )
}

@Composable
fun TextViewThree(@StringRes title: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = title),
        // color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Black,
            fontSize = 12.sp,
            lineHeight = 16.0.sp,
            letterSpacing = 0.3.sp,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    )
}