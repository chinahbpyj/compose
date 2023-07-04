package com.example.learn.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.learn.R
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton

@Composable
fun ButtonView(onClick: () -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Button(onClick)

        OutlinedButton(onClick)

        TextButton(onClick)
    }
}

@Composable
fun Button(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.register)
        )
    }
}

@Composable
fun OutlinedButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp, start = 15.dp, end = 15.dp),
    ) {
        Text(text = stringResource(id = R.string.register))
    }
}

@Composable
fun TextButton(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.register))
    }
}