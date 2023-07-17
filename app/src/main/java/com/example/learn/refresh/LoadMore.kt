package com.example.learn.refresh

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learn.AlignYourBodyElementList
import com.example.learn.FavoriteCollectionList
import com.example.learn.HomeSection
import com.example.learn.R
import com.example.learn.refresh.viewmodel.LoadMoreViewModel
import com.example.learn.refresh.viewmodel.LoadMoreViewModelFactory

@Composable
fun LoadMore() {
    val context = LocalContext.current

    val viewModel: LoadMoreViewModel = viewModel(
        factory = LoadMoreViewModelFactory()
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.request) {
        LoadingUII()
        viewModel.request()
    } else {
        SwipeRefresh(
            refreshingUI = { RefreshingUI() },
            loadMoreUI = { LoadMoreUI() },
            emptyLayout = { EmptyLayout { viewModel.request() } },
            contentUI = { ContentUI(uiState.data) },
            items = uiState.list,
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
private fun ContentUI(data: RefreshData?) {
    HomeSection(title = R.string.align_your_body) {
        data?.let { AlignYourBodyElementList(it.alignYourBodyElementList) }
    }
    HomeSection(title = R.string.favorite_collections) {
        data?.let { FavoriteCollectionList(it.favoriteCollectionList) }
    }
    Spacer(Modifier.height(16.dp))

    data?.let {
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