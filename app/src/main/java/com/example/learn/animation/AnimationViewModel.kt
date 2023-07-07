package com.example.learn.animation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

enum class JumpType {
    A,
    B,
    C
}

data class JumpScreenData(
    val index: Int,
    val jumpType: JumpType,
)

class AnimationViewModel : ViewModel() {
    private val screenOrder: List<JumpType> = listOf(
        JumpType.A,
        JumpType.B,
        JumpType.C
    )

    private var index = 0

    private val _jumpScreenData = mutableStateOf(createJumpScreenData())
    val jumpScreenData: JumpScreenData
        get() = _jumpScreenData.value

    fun onBackPressed(): Boolean {
        if (index == 0) {
            return false
        }
        changeIndex(index - 1)
        return true
    }

    fun onPreviousPressed() {
        if (index == 0) {
            throw IllegalStateException("onPreviousPressed when on question 0")
        }
        changeIndex(index - 1)
    }

    fun onNextPressed() {
        changeIndex(index + 1)
    }

    private fun changeIndex(newIndex: Int) {
        index = newIndex
        _jumpScreenData.value = createJumpScreenData()
    }

    private fun createJumpScreenData(): JumpScreenData {
        return JumpScreenData(
            index = index,
            jumpType = screenOrder[index],
        )
    }
}

class AnimationViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimationViewModel::class.java)) {
            return AnimationViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

