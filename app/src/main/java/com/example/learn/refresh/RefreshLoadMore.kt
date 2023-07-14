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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RefreshLoadMore() {
    val context = LocalContext.current

    val viewModel: RefreshLoadMoreViewModel = viewModel(
        factory = RefreshLoadMoreViewModelFactory()
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.request) {
        LoadingUII()
        viewModel.request()
    } else {
        SwipeRefresh(
            refreshingUI = { RefreshingUI() },
            loadMoreUI = { LoadMoreUI() },
            emptyLayout = { EmptyLayout { viewModel.refresh() } },
            items = uiState.data,
            refreshing = uiState.refreshing,
            onRefresh = { viewModel.refresh() },
            loading = uiState.loading,
            onLoad = { viewModel.loadMore() },
            itemContent = { _, item ->
                Item(item, context)
            })
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