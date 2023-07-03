package com.example.learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learn.Destinations.LOGIN_ROUTE
import com.example.learn.Destinations.REGISTER_ROUTE
import com.example.learn.Destinations.SURVEY_RESULTS_ROUTE
import com.example.learn.login.EmailState
import com.example.learn.login.EmailStateSaver
import com.example.learn.login.PasswordState
import com.example.learn.ui.theme.LearnTheme
import com.example.learn.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch

class LoginRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnTheme {
                ComposeViewNavHost()
            }
        }
    }
}

object Destinations {
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ROUTE = "register"
    const val SURVEY_RESULTS_ROUTE = "surveyresults"
}

const val stronglyDeemphasizedAlpha = 0.6f

@Composable
fun ComposeViewNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = LOGIN_ROUTE,
    ) {
        composable(LOGIN_ROUTE) {
            LoginScreen(
                onOperator = { email, passport ->
                    navController.navigate(SURVEY_RESULTS_ROUTE)
                },
                onRegister = {
                    navController.navigate(REGISTER_ROUTE)
                },
            )
        }

        composable(REGISTER_ROUTE) {
            RegisterScreen(
                onOperator = { email, passport ->
                    navController.navigate(SURVEY_RESULTS_ROUTE)
                },
                onBack = navController::navigateUp
            )
        }

        composable(SURVEY_RESULTS_ROUTE) {

        }
    }
}

@Composable
fun LoginScreen(
    onOperator: (String, String) -> Unit,
    onRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .widthIn(max = 840.dp)
            .background(color = PurpleGrey80)
            .verticalScroll(rememberScrollState())
            .wrapContentHeight(align = Alignment.CenterVertically)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
        /* .padding(horizontal = 20.dp)*/,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_create_account),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        SignInRegisterScreen(stringResource(id = R.string.sign_in), onOperator)

        Button(
            onClick = onRegister,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onOperator: (String, String) -> Unit,
    onBack: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val snackBarErrorText = stringResource(id = R.string.feature_not_available)
    val snackBarActionLabel = stringResource(id = R.string.dismiss)

    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarText = stringResource(id = R.string.register),
                onNavUp = onBack,
                onRight = {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = snackBarErrorText,
                            actionLabel = snackBarActionLabel
                        )
                    }
                }
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(contentPadding),
                //  horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInRegisterScreen(stringResource(id = R.string.register), onOperator)
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackBar(
            snackBarHostState = snackBarHostState,
            onDismiss = { snackBarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SignInRegisterScreen(
    buttonText: String,
    onOperator: (String, String) -> Unit
) {
    //Spacer(modifier = Modifier.height(44.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            val focusRequester = remember { FocusRequester() }

            val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
                mutableStateOf(EmailState(""))
            }
            Email(emailState, onImeAction = { focusRequester.requestFocus() })

            Spacer(modifier = Modifier.height(16.dp))

            val passwordState = remember { PasswordState() }

            val onSubmit = {
                if (emailState.isValid && passwordState.isValid) {
                    onOperator(emailState.text, passwordState.text)
                }
            }
            Password(
                label = stringResource(id = R.string.password),
                passwordState = passwordState,
                modifier = Modifier.focusRequester(focusRequester),
                onImeAction = { onSubmit() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onSubmit() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                enabled = emailState.isValid && passwordState.isValid
            ) {
                Text(
                    text = buttonText
                )
            }
        }
    }

    // Spacer(modifier = Modifier.height(16.dp))
}

@OptIn(ExperimentalMaterial3Api::class) // CenterAlignedTopAppBar is experimental in m3
@Composable
fun TopAppBar(
    topAppBarText: String,
    onNavUp: () -> Unit,
    onRight: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topAppBarText,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavUp) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        // We need to balance the navigation icon, so we add a spacer.
        actions = {
            TextButton(onClick = onRight) {
                Text(text = "rightArea")
            }
        },
    )
}

@Composable
fun ErrorSnackBar(
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { }
) {
    SnackbarHost(
        hostState = snackBarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                content = {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                action = {
                    data.visuals.actionLabel?.let {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = stringResource(id = R.string.dismiss),
                                color = MaterialTheme.colorScheme.inversePrimary
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}
