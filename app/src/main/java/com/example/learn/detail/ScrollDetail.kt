package com.example.learn.detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learn.detail.ScrollDetailDestinations.APPBAR_SCROLL_ROUTE
import com.example.learn.detail.ScrollDetailDestinations.AREA_SCROLL_ROUTE
import com.example.learn.detail.ScrollDetailDestinations.MAIN_ROUTE
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.view.ListView
import com.example.learn.view.View

object ScrollDetailDestinations {
    const val MAIN_ROUTE = "Main"
    const val APPBAR_SCROLL_ROUTE = "TopAppBarScroll"
    const val AREA_SCROLL_ROUTE = "TopAreaScroll"
}

@Composable
fun ScrollDetail() {
    LearnTheme {
        ScrollDetailNavHost()
    }
}

@Composable
private fun ScrollDetailNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val buttonListSample = listOf(
        View(
            APPBAR_SCROLL_ROUTE,
        ) { navController.navigate(APPBAR_SCROLL_ROUTE) },

        View(
            AREA_SCROLL_ROUTE,
        ) { navController.navigate(AREA_SCROLL_ROUTE) },
    )

    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE,
    ) {
        composable(MAIN_ROUTE) {
            ListView(buttonListSample)
        }

        composable(APPBAR_SCROLL_ROUTE) {
            TopAppBarScroll(navController::navigateUp)
        }

        composable(AREA_SCROLL_ROUTE) {
            TopAreaScroll(navController::navigateUp)
        }
    }
}

