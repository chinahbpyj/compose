package com.example.learn.view

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learn.R

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

        Spacer(modifier = Modifier.height(30.dp))

        TextViewFour(R.string.text_center)
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

@Composable
fun TextViewFour(@StringRes title: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth()
    ) {
        Text(
            text = stringResource(id = title),
            style = TextStyle(
                //fontFamily = CustomFontFamily
            ),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = modifier
                .widthIn(120.dp)
                .background(
                    color = Color(0xFF625b71),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(vertical = 15.dp)
        )
    }
}

/*val CustomFontFamily = FontFamily(
    listOf(
        Font(R.font.montserrat_regular),
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_semi_bold, FontWeight.SemiBold)
    )
)*/