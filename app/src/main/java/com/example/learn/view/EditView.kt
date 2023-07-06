package com.example.learn.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.learn.R

// https://blog.csdn.net/c10WTiybQ1Ye3/article/details/118771931
@Composable
fun EditView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) {
        EmailEdit()

        Spacer(modifier = Modifier.height(20.dp))

        PasswordEdit()

        Spacer(modifier = Modifier.height(20.dp))

        TextEdit()

        Spacer(modifier = Modifier.height(20.dp))

        BasicEdit()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailEdit() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        var edit by remember { mutableStateOf("") }

        OutlinedTextField(
            value = edit,
            onValueChange = { edit = it },
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->

                },
            textStyle = MaterialTheme.typography.bodyMedium,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Default,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                },
                onNext = {

                },
                onSearch = {

                },
                onSend = {

                }
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordEdit() {
    val showPassword = rememberSaveable { mutableStateOf(false) }
    var edit by remember { mutableStateOf("") }

    OutlinedTextField(
        value = edit,
        onValueChange = { edit = it },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->

            },
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(
                text = stringResource(id = R.string.password),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(id = R.string.hide_password)
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(id = R.string.show_password)
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {

            },
            onNext = {

            },
            onSearch = {

            },
            onSend = {

            }
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextEdit() {
    var edit by remember { mutableStateOf("") }
    TextField(
        value = edit,
        onValueChange = { edit = it },
        singleLine = true,
        // maxLines = Int.MAX_VALUE,
        leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { edit = "" }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5.dp, MaterialTheme.colorScheme.primary,RoundedCornerShape(10.dp))
            .onFocusChanged { focusState ->

            },
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Default,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onDone = {

            },
            onNext = {

            },
            onSearch = {

            },
            onSend = {

            }
        ),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFEFB8C8),//text Color
            focusedIndicatorColor = Color.Transparent,//focused Indicator Color
            unfocusedIndicatorColor = Color.Transparent,//unfocused Indicator Color
            containerColor = Color(0xFFF0F0F0),//background color
            placeholderColor = Color(0xFFEFB8C8),//placeholder Color
            cursorColor = Color(0xFF000000),//cursor Color
        )
    )
}

@Composable
fun BasicEdit() {
    var edit by remember { mutableStateOf("") }
    BasicTextField(
        value = edit,
        onValueChange = { edit = it },
        modifier = Modifier
            .background(
                color = Color(0xFFF0F0F0),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .fillMaxWidth()
    )
}

