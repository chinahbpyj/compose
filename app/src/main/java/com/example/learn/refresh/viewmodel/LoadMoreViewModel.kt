package com.example.learn.refresh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.learn.SampleData
import com.example.learn.refresh.LoadMoreState
import com.example.learn.refresh.RefreshData
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadMoreViewModel : ViewModel() {
    private var index = 1

    private val viewModelState = MutableStateFlow(LoadMoreState())

    val uiState = viewModelState
        .map(LoadMoreState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun request() {
        viewModelState.update { it.copy(request = true) }
        loadData()
    }

    private fun loadData() {
        index = 1

        viewModelScope.launch {
            val dataAsync = async { getData() }
            val listDataAsync = async { getListData(index, 15) }

            val data = dataAsync.await()
            listData = listDataAsync.await()

            viewModelState.update {
                it.copy(
                    request = false,
                    data = data,
                    list = listData,
                    refreshing = false
                )
            }
        }
    }

    fun refresh() {
        viewModelState.update { it.copy(refreshing = true) }
        loadData()
    }

    fun loadMore() {
        viewModelState.update { it.copy(loading = true) }
        index++

        val list = mutableListOf<String>()
        list.addAll(listData!!)
        viewModelScope.launch {
            val newData: List<String> = getListData(index, 15)
            list.addAll(newData)
            listData = list

            viewModelState.update {
                it.copy(list = listData, loading = false)
            }
        }
    }

    private suspend fun getData(): RefreshData {
        delay(3000)

        val text =
            "You are a curious developer, always willing to try something new. You want to stay up to date with the trends to Compose is your middle name."

        return RefreshData(
            alignYourBodyElementList = SampleData.alignYourBodyElementSample,
            favoriteCollectionList = SampleData.favoriteCollectionSample,
            body = text
        )
    }

    private var listData: List<String>? = listOf()

    private suspend fun getListData(index: Int, pageSize: Int): List<String> {
        delay(5000)

        val listData = mutableListOf<String>()

        for (i in ((index - 1) * pageSize + 1)..index * pageSize) {
            listData.add(i.toString())
        }

        return listData
    }
}

class LoadMoreViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoadMoreViewModel::class.java)) {
            return LoadMoreViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

