package com.example.learn.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.learn.R
import androidx.compose.material3.Button

@Composable
fun ProgressView(
    index: Int,
    totalCount: Int,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>
) {
    var progressValue by remember {
        mutableStateOf(index)
    }

    var seekValue by remember {
        mutableStateOf(value)
    }

    Column() {
        Spacer(modifier = Modifier.height(15.dp))

        Progress(progressValue, totalCount)

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = {
                if (progressValue == totalCount) {
                    progressValue = 0
                } else {
                    progressValue += 1
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        SeekBar(value = seekValue, valueRange = valueRange, onValueChange = {
            seekValue = it
        })

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = {
                if (seekValue == 100f) {
                    seekValue = 0f
                } else {
                    seekValue += 1
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add)
            )
        }
    }
}

@Composable
fun Progress(
    index: Int,
    totalCount: Int
) {
    val animatedProgress by animateFloatAsState(
        targetValue = index / totalCount.toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    LinearProgressIndicator(
        progress = animatedProgress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    )
}

@Composable
fun SeekBar(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit
) {
    Row {
        Slider(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            valueRange = valueRange,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
    }
}