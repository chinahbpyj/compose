package com.example.learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.ui.theme.Purple40
import com.example.learn.ui.theme.PurpleGrey80

class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnTheme {
                //MyAppNavHost()
                Home()
            }
        }
    }
}

@Composable
private fun Home() {
    val items = listOf(
        Screen.Profile,
        Screen.FriendsList,
    )

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true

                    BottomNavigationItem(
                        icon = { Icon(screen.imageVector, contentDescription = null) },

                        label = {
                            Text(
                                stringResource(screen.resourceId),
                                color = if (isSelected) Purple40 else PurpleGrey80
                            )
                        },

                        selected = isSelected,

                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reSelecting the same item
                                launchSingleTop = true
                                // Restore state when reSelecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        //NavHost1(navController,innerPadding)
        NavHost2(navController, innerPadding)
    }
}

@Composable
fun NavHost1(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController,
        startDestination = Screen.Profile.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Profile.route) {
            Profile(
                onNavigateToFriends = { navController.navigate(Screen.FriendsList.route) },
            )
        }

        composable(Screen.FriendsList.route) {
            FriendsList(
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
            )
        }
    }
}

@Composable
fun NavHost2(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController,
        startDestination = Screen.Profile.route,
        Modifier.padding(innerPadding)
    ) {
        composable(Screen.Profile.route) {
            HomeScreen()
        }

        composable(Screen.FriendsList.route) {
            Conversation(SampleData.conversationSample)
        }
    }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val imageVector: ImageVector
) {
    object Profile :
        Screen("home", R.string.bottom_navigation_home, Icons.Default.Spa)

    object FriendsList :
        Screen("profile", R.string.bottom_navigation_profile, Icons.Default.AccountCircle)
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "friendsList"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        loginGraph(navController)

        composable(
            route = "profile/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {
            it.arguments?.getString("userId")?.let { valueString ->
                ProfileScreen(
                    valueString,
                    onNavigateToFriends = { navController.navigate("friendsList") },
                )
            }
        }

        composable(
            route = "friendsList?userId={userId}",
            arguments = listOf(navArgument("userId") { defaultValue = "friendsList 88888" })
        ) {
            it.arguments?.getString("userId")?.let { valueString ->
                FriendsListScreen(
                    valueString,
                    onNavigateToProfile = { navController.navigate("profile/profile 88888") },
                )
            }
        }
    }
}

fun NavGraphBuilder.loginGraph(navController: NavController) {
    // Navigating to the graph via its route ('login') automatically
    // navigates to the graph's start destination - 'username'
    // therefore encapsulating the graph's internal routing logic
    navigation(startDestination = "username", route = "login") {
        composable("username") { }
        composable("password") { }
        composable("registration") { }
    }
}

@Composable
fun ProfileScreen(
    valueString: String,
    onNavigateToFriends: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = onNavigateToFriends,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(text = "See friends list")
        }

        Text(
            text = valueString,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun Profile(
    onNavigateToFriends: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = onNavigateToFriends,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(text = "See friends list")
        }

        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun FriendsListScreen(
    valueString: String,
    onNavigateToProfile: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(text = "See profile")
        }

        Text(
            text = valueString,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun FriendsList(
    onNavigateToProfile: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Text(text = "See profile")
        }

        Text(
            text = "FriendsList",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    navController.navigate("friendsList")

    // Pop everything up to the "home" destination off the back stack before
    // navigating to the "friendsList" destination
    navController.navigate("friendsList") {
        popUpTo("home")
    }

    // Pop everything up to and including the "home" destination off
    // the back stack before navigating to the "friendsList" destination
    navController.navigate("friendsList") {
        popUpTo("home") {
            inclusive = true
        }
    }

    // Navigate to the "search” destination only if we’re not already on
    // the "search" destination, avoiding multiple copies on the top of the
    // back stack
    navController.navigate("search") {
        launchSingleTop = true
    }
}