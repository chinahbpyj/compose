package com.example.learn.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Loading(onNavUp: () -> Unit) {
    var isEmpty by remember { mutableStateOf(true) }
    var refreshing by remember { mutableStateOf(false) }

    BackHandler {
        if (isEmpty) {
            isEmpty = false
        } else {
            onNavUp()
        }
    }

    LoadingContent(
        empty = isEmpty,
        fullScreenLoading = { FullScreenLoading() },
        refreshing = refreshing,
        onRefresh = { refreshing = true }
    ) {
        ListView()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun LoadingContent(
    empty: Boolean,
    fullScreenLoading: @Composable () -> Unit,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        fullScreenLoading()
    } else {
        val pullRefreshState = rememberPullRefreshState(refreshing, { onRefresh() })

        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            content()

            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}


@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}