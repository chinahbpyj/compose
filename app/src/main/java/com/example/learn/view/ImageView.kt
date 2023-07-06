package com.example.learn.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.learn.R

@Composable
fun ImageView() {
    Column(Modifier.padding(horizontal = 15.dp, vertical = 15.dp)) {
        ImageViewWithCircle()

        Spacer(modifier = Modifier.height(15.dp))

        ImageViewWithBorder()

        Spacer(modifier = Modifier.height(15.dp))

        ImageViewDefault()

        Spacer(modifier = Modifier.height(15.dp))

        ImageViewRoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 15.dp,
            bottomStart = 15.dp
        )

        Spacer(modifier = Modifier.height(15.dp))

        ImageViewItem(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 15.dp,
            bottomStart = 15.dp
        )
    }
}

@Composable
fun ImageViewWithCircle() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(66.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ImageViewWithBorder() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(66.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
    )
}

@Composable
fun ImageViewDefault() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(66.dp)
    )
}

@Composable
fun ImageViewRoundedCornerShape(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp
) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(66.dp)
            .clip(
                RoundedCornerShape(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = bottomEnd,
                    bottomStart = bottomStart
                )
            )
    )
}

@Composable
fun ImageViewItem(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp
) {
    Surface(
        color = Color(0xFF625b71),
        shape = RoundedCornerShape(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 100.dp, height = 120.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = stringResource(id = R.string.play_games), modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}