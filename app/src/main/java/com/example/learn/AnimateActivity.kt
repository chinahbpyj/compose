package com.example.learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.ui.theme.Pink80
import com.example.learn.ui.theme.Purple40

class AnimateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}

private enum class TabPage {
    Home, Profile
}

@Composable
private fun Home() {
    var tabPage by remember { mutableStateOf(TabPage.Home) }

    val backgroundColor by animateColorAsState(if (tabPage == TabPage.Home) Purple40 else Pink80)

    LearnTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colorScheme.background,
                ) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Spa,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(stringResource(R.string.bottom_navigation_home))
                        },
                        selected = true,
                        onClick = {
                            tabPage = TabPage.Home
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(stringResource(R.string.bottom_navigation_profile))
                        },
                        selected = false,
                        onClick = {
                            tabPage = TabPage.Profile
                        }
                    )
                }
            }
        ) { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                Text(text = "",Modifier.background(backgroundColor).fillMaxSize())

            }
        }
    }
}