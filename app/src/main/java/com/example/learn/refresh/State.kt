package com.example.learn.refresh

import com.example.learn.Item

data class RefreshLoadMoreState(
    val request: Boolean = true,
    val index: Int = 1,
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val data: List<String>? = null
) {
    fun toUiState(): RefreshLoadMoreUiState = RefreshLoadMoreUiState(
        request = request,
        index = index,
        loading = loading,
        refreshing = refreshing,
        data = data
    )
}

data class RefreshLoadMoreUiState(
    val request: Boolean = true,
    val index: Int = 1,
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val data: List<String>? = null
)

data class RefreshState(
    val request: Boolean = true,
    val refreshing: Boolean = false,
    val data: RefreshData? = null
) {
    fun toUiState(): RefreshUiState = RefreshUiState(
        request = request,
        refreshing = refreshing,
        data = data
    )
}

data class RefreshData(
    val alignYourBodyElementList: List<Item>,
    val favoriteCollectionList: List<Item>,
    val body: String
)

data class RefreshUiState(
    val request: Boolean = true,
    val refreshing: Boolean = false,
    val data: RefreshData? = null
)