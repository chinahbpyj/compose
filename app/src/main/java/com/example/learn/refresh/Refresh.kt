package com.example.learn.refresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learn.AlignYourBodyElementList
import com.example.learn.FavoriteCollectionList
import com.example.learn.HomeSection
import com.example.learn.R
import com.example.learn.refresh.viewmodel.RefreshViewModel
import com.example.learn.refresh.viewmodel.RefreshViewModelFactory

@Composable
fun Refresh() {
    val viewModel: RefreshViewModel = viewModel(
        factory = RefreshViewModelFactory()
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RefreshContent(
        request = uiState.request,
        fullScreenLoading = {
            LoadingUII()
            viewModel.request()
        },
        refreshing = uiState.refreshing,
        onRefresh = { viewModel.request() }
    ) {
        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            HomeSection(title = R.string.align_your_body) {
                uiState.data?.let { AlignYourBodyElementList(it.alignYourBodyElementList) }
            }
            HomeSection(title = R.string.favorite_collections) {
                uiState.data?.let { FavoriteCollectionList(it.favoriteCollectionList) }
            }
            Spacer(Modifier.height(16.dp))

            uiState.data?.let {
                Text(
                    text = it.body,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Black,
                        fontSize = 12.sp,
                        lineHeight = 16.0.sp,
                        letterSpacing = 0.3.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RefreshContent(
    request: Boolean,
    fullScreenLoading: @Composable () -> Unit,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (request) {
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