package com.example.learn.animation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

private const val CONTENT_ANIMATION_DURATION = 300

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun JumpAnimation(onNavUp: () -> Unit) {
    val viewModel: AnimationViewModel = viewModel(
        factory = AnimationViewModelFactory()
    )

    val jumpScreenData = viewModel.jumpScreenData

    BackHandler {
        if (!viewModel.onBackPressed()) {
            onNavUp()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .widthIn(max = 840.dp)
    ) {
        AnimatedContent(
            targetState = jumpScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> =
                    tween(CONTENT_ANIMATION_DURATION)
                val direction = getTransitionDirection(
                    initialIndex = initialState.index,
                    targetIndex = targetState.index,
                )
                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec,
                ) with slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            }
        ) { targetState ->
            when (targetState.jumpType) {
                JumpType.A -> {
                    ScreenA(
                        onPreviousPressed = { onNavUp() },
                        onNextPressed = { viewModel.onNextPressed() }
                    )
                }

                JumpType.B -> {
                    ScreenB(
                        onPreviousPressed = { viewModel.onPreviousPressed() },
                        onNextPressed = { viewModel.onNextPressed() }
                    )
                }

                JumpType.C -> {
                    ScreenC(
                        onPreviousPressed = { viewModel.onPreviousPressed() }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        AnimatedContentScope.SlideDirection.Left
    } else {
        AnimatedContentScope.SlideDirection.Right
    }
}
