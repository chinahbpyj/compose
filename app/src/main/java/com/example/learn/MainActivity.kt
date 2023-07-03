package com.example.learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.learn.ButtonDestinations.LOGIN_ROUTE
import com.example.learn.ButtonDestinations.NAVIGATION_ROUTE
import com.example.learn.ui.theme.LearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnTheme {
                MainNavHost()
            }
        }
    }
}

object ButtonDestinations {
    const val LOGIN_ROUTE = "login"
    const val NAVIGATION_ROUTE = "navigation"
}

data class Button(
    val text: String,
    val method: () -> Unit
)

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val buttonListSample = listOf(
        Button(
            "LoginRegisterActivity",
        ) { navController.navigate(LOGIN_ROUTE) },

        Button(
            "NavigationActivity",
        ) { navController.navigate(NAVIGATION_ROUTE) }
    )

    NavHost(
        navController = navController,
        startDestination = "Main",
    ) {
        composable("Main") {
            ListButton(buttonListSample)
        }

        composable(LOGIN_ROUTE) {
            ComposeViewNavHost()
        }

        composable(NAVIGATION_ROUTE) {
            NavigationHome()
        }
    }
}

@Composable
fun ListButton(buttons: List<Button>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            items(buttons) { button ->
                SingleButton(button)
            }
        }
    }
}

@Composable
fun SingleButton(button: Button) {
    Button(
        onClick = button.method,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ) {
        Text(
            text = button.text
        )
    }
}