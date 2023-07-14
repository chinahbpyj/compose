package com.example.learn.refresh

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun RefreshLoadMore() {
    val context = LocalContext.current

    var index by remember { mutableStateOf(1) }
    var data: List<String>? by remember { mutableStateOf(null) }
    var loading by remember { mutableStateOf(false) }
    var refreshing by remember { mutableStateOf(false) }

    val refreshingUIHeight = 80.dp

    val refresh = {
        refreshing = false
        index = 1
        data = getData(index, 15)
    }

    val loadMore = {
        loading = false
        index++
        val list = mutableListOf<String>()
        list.addAll(data!!)
        val newData: List<String> = getData(index, 15)
        list.addAll(newData)
        data = list
    }

    SwipeRefresh(
        refreshingUIHeight = refreshingUIHeight,
        refreshingUI = { RefreshingUI(refreshingUIHeight) },
        loadMoreUI = { LoadMoreUI() },
        emptyLayout = { EmptyLayout { refresh() } },
        items = data,
        refreshing = refreshing,
        onRefresh = { refresh() },
        loading = loading,
        onLoad = { loadMore() },
        itemContent = { _, item ->
            Item(item, context)
        })
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

private fun getData(index: Int, pageSize: Int): List<String> {
    val listData = mutableListOf<String>()

    for (i in ((index - 1) * pageSize + 1)..index * pageSize) {
        listData.add(i.toString())
    }

    return listData
}