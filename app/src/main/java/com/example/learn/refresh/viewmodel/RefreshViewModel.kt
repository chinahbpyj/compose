package com.example.learn.refresh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.learn.SampleData
import com.example.learn.refresh.RefreshData
import com.example.learn.refresh.RefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RefreshViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow(RefreshState())

    val uiState = viewModelState
        .map(RefreshState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    private var data: RefreshData? = null

    fun request() {
        viewModelState.update { it.copy(refreshing = true) }

        viewModelScope.launch {
            data = getData()
            viewModelState.update {
                it.copy(
                    request = false,
                    data = data,
                    refreshing = false
                )
            }
        }
    }

    private suspend fun getData(): RefreshData {
        delay(5000)

        val text =
            "You are a curious developer, always willing to try something new. You want to stay up to date with the trends to Compose is your middle name."

        return RefreshData(
            alignYourBodyElementList = SampleData.alignYourBodyElementSample,
            favoriteCollectionList = SampleData.favoriteCollectionSample,
            body = text
        )
    }
}

class RefreshViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RefreshViewModel::class.java)) {
            return RefreshViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

