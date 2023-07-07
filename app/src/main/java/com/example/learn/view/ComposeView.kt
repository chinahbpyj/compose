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
import com.example.learn.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.view.ViewDestinations.BUTTON_ROUTE
import com.example.learn.view.ViewDestinations.CHECKBOX_ROUTE
import com.example.learn.view.ViewDestinations.EDIT_ROUTE
import com.example.learn.view.ViewDestinations.IMAGEVIEW_ROUTE
import com.example.learn.view.ViewDestinations.LIST_ROUTE
import com.example.learn.view.ViewDestinations.MAIN_ROUTE
import com.example.learn.view.ViewDestinations.PROGRESS_ROUTE
import com.example.learn.view.ViewDestinations.RADIOBUTTON_ROUTE
import com.example.learn.view.ViewDestinations.TEXTVIEW_ROUTE
import com.example.learn.view.ViewDestinations.TOP_APPBAR_ROUTE

object ViewDestinations {
    const val MAIN_ROUTE = "Main"
    const val TOP_APPBAR_ROUTE = "TopAppBar"
    const val BUTTON_ROUTE = "Button"
    const val PROGRESS_ROUTE = "Progress"
    const val TEXTVIEW_ROUTE = "TextView"
    const val CHECKBOX_ROUTE = "CheckBox"
    const val RADIOBUTTON_ROUTE = "RadioButton"
    const val IMAGEVIEW_ROUTE = "ImageView"
    const val EDIT_ROUTE = "EditView"
    const val LIST_ROUTE = "List"
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
            TOP_APPBAR_ROUTE,
        ) { navController.navigate(TOP_APPBAR_ROUTE) },

        View(
            BUTTON_ROUTE,
        ) { navController.navigate(BUTTON_ROUTE) },

        View(
            PROGRESS_ROUTE,
        ) { navController.navigate(PROGRESS_ROUTE) },

        View(
            TEXTVIEW_ROUTE,
        ) { navController.navigate(TEXTVIEW_ROUTE) },

        View(
            CHECKBOX_ROUTE,
        ) { navController.navigate(CHECKBOX_ROUTE) },

        View(
            RADIOBUTTON_ROUTE,
        ) { navController.navigate(RADIOBUTTON_ROUTE) },

        View(
            IMAGEVIEW_ROUTE,
        ) { navController.navigate(IMAGEVIEW_ROUTE) },

        View(
            EDIT_ROUTE,
        ) { navController.navigate(EDIT_ROUTE) },

        View(
            LIST_ROUTE,
        ) { navController.navigate(LIST_ROUTE) },
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

        composable(BUTTON_ROUTE) {
            ButtonView(navController::navigateUp)
        }

        composable(PROGRESS_ROUTE) {
            ProgressView(0, 100, 0f, 0f..100f)
        }

        composable(TEXTVIEW_ROUTE) {
            TextView(R.string.text_description)
        }

        composable(CHECKBOX_ROUTE) {
            CheckBoxView()
        }

        composable(RADIOBUTTON_ROUTE) {
            RadioButtonView()
        }

        composable(IMAGEVIEW_ROUTE) {
            ImageView()
        }

        composable(EDIT_ROUTE) {
            EditView()
        }

        composable(LIST_ROUTE) {
            ListView()
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
