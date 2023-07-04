package com.example.learn.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.view.ViewDestinations.MAIN_ROUTE
import com.example.learn.view.ViewDestinations.TOP_APPBAR_ROUTE

object ViewDestinations {
    const val MAIN_ROUTE = "Main"
    const val TOP_APPBAR_ROUTE = "TopAppBar"
    const val NAVIGATION_ROUTE = "navigation"
    const val VIEW_ROUTE = "view"
}

data class View(
    val text: String,
    val method: () -> Unit
)

@Composable
fun ComposeViewHome() {
    LearnTheme {
        ComposeViewNavHost()
    }
}

@Composable
fun ComposeViewNavHost(
    navController: NavHostController = rememberNavController(),
) {

    val buttonListSample = listOf(
        View(
            "TopAppBar",
        ) { navController.navigate(TOP_APPBAR_ROUTE) },


        )
    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE,
    ) {
        composable(MAIN_ROUTE) {
            ListView(buttonListSample)
        }

        composable(TOP_APPBAR_ROUTE) {
            TopBarView("TopAppBar", navController::navigateUp)
        }
    }
}

@Composable
fun ListView(Views: List<View>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(Views) { view ->
                SingleView(view)
            }
        }
    }
}

@Composable
fun SingleView(view: View) {
    Button(
        onClick = view.method,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ) {
        Text(
            text = view.text
        )
    }
}
