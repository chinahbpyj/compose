package com.example.learn.view

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.learn.R
import java.util.Locale

@Composable
fun GridView() {
    val context = LocalContext.current
    val listData = mutableListOf<String>()

    for (i in 1..16) {
        listData.add(i.toString())
    }

    Column() {
        Text(
            text = stringResource(R.string.horizontal_grid).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.height(160.dp)
        ) {
            items(listData) { item ->
                HorizontalGridItem(item, context)
            }
        }

        Text(
            text = stringResource(R.string.vertical_grid).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(listData) { item ->
                VerticalGridItem(item, context)
            }
        }
    }
}

@Composable
private fun HorizontalGridItem(item: String, context: Context) {
    Row(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .clickable {
                Toast
                    .makeText(context, item, Toast.LENGTH_SHORT)
                    .show()
            }
            .background(color = Color(0xFFCCC2DC), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = item,
            modifier = Modifier
                .clickable { },
        )
    }
}

@Composable
private fun VerticalGridItem(item: String, context: Context) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .clickable {
                Toast
                    .makeText(context, item, Toast.LENGTH_SHORT)
                    .show()
            }
            .background(color = Color(0xFFCCC2DC), shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = item,
            modifier = Modifier
                .clickable { },
        )
    }
}