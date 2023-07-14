package com.example.learn.refresh

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learn.refresh.RefreshLoadMoreViewDestinations.LOAD_MORE_ROUTE
import com.example.learn.refresh.RefreshLoadMoreViewDestinations.MAIN_ROUTE
import com.example.learn.refresh.RefreshLoadMoreViewDestinations.REFRESH_LOAD_MORE_ROUTE
import com.example.learn.refresh.RefreshLoadMoreViewDestinations.REFRESH_ROUTE
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.view.ListView
import com.example.learn.view.View

object RefreshLoadMoreViewDestinations {
    const val MAIN_ROUTE = "Main"
    const val REFRESH_LOAD_MORE_ROUTE = "RefreshLoadMore"
    const val REFRESH_ROUTE = "Refresh"
    const val LOAD_MORE_ROUTE = "LoadMore"
}

@Composable
fun RefreshLoadMore() {
    LearnTheme {
        RefreshLoadMoreNavHost()
    }
}

@Composable
private fun RefreshLoadMoreNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val buttonListSample = listOf(
        View(
            REFRESH_LOAD_MORE_ROUTE,
        ) { navController.navigate(REFRESH_LOAD_MORE_ROUTE) },

        View(
            REFRESH_ROUTE,
        ) { navController.navigate(REFRESH_ROUTE) },

        View(
            LOAD_MORE_ROUTE,
        ) { navController.navigate(LOAD_MORE_ROUTE) },
    )
    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE,
    ) {
        composable(MAIN_ROUTE) {
            ListView(buttonListSample)
        }

        composable(REFRESH_LOAD_MORE_ROUTE) {
            RefreshAndLoadMore()
        }

        composable(REFRESH_ROUTE) {
            Refresh()
        }

        composable(LOAD_MORE_ROUTE) {
            LoadMore()
        }
    }
}