package com.example.learn.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ListView(modifier: Modifier = Modifier, state: LazyListState = rememberLazyListState()) {
    val context = LocalContext.current
    val listData = mutableListOf<String>()

    for (i in 1..50) {
        listData.add(i.toString())
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = state
    ) {
        items(listData) { item ->
            Item(item, context)
        }
    }
}

@Composable
private fun Item(item: String, context: Context) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable {
                Toast
                    .makeText(context, item, Toast.LENGTH_SHORT)
                    .show()
            }
            .background(color = Color(0xFFCCC2DC), shape = RoundedCornerShape(10.dp)),
    ) {
        Text(
            text = item, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable { },
            textAlign = TextAlign.Center
        )
    }
}