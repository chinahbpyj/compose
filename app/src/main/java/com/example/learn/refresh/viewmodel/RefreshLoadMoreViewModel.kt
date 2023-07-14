package com.example.learn.refresh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.learn.refresh.RefreshLoadMoreState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RefreshLoadMoreViewModel : ViewModel() {
    private var index = 1

    private val viewModelState = MutableStateFlow(RefreshLoadMoreState())

    val uiState = viewModelState
        .map(RefreshLoadMoreState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    private var data: List<String>? = listOf()

    fun request() {
        viewModelState.update { it.copy(request = true) }
        index = 1

        viewModelScope.launch {
            data = getData(index, 15)
            viewModelState.update {
                it.copy(
                    request = false,
                    data = data,
                    refreshing = false
                )
            }
        }
    }

    fun refresh() {
        viewModelState.update { it.copy(refreshing = true) }
        index = 1

        viewModelScope.launch {
            data = getData(index, 15)
            viewModelState.update {
                it.copy(
                    data = data,
                    refreshing = false
                )
            }
        }
    }

    fun loadMore() {
        viewModelState.update { it.copy(loading = true) }
        index++

        val list = mutableListOf<String>()
        list.addAll(data!!)
        viewModelScope.launch {
            val newData: List<String> = getData(index, 15)
            list.addAll(newData)
            data = list

            viewModelState.update {
                it.copy(data = data, loading = false)
            }
        }
    }


    private suspend fun getData(index: Int, pageSize: Int): List<String> {
        delay(5000)

        val listData = mutableListOf<String>()

        for (i in ((index - 1) * pageSize + 1)..index * pageSize) {
            listData.add(i.toString())
        }

        return listData
    }
}

class RefreshLoadMoreViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RefreshLoadMoreViewModel::class.java)) {
            return RefreshLoadMoreViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

