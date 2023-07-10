package com.example.learn.view

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.learn.R
import java.util.Locale

/*https://developer.android.com/jetpack/compose/layouts/flow?hl=zh-cn*/

@Composable
fun FlowLayout() {
    val context = LocalContext.current
    val flowData = mutableListOf<String>()
    flowData.add("1213")
    flowData.add("123124234234324")
    flowData.add("342342342342342323422343454334532342")
    flowData.add("1212")
    flowData.add("2323424234")
    flowData.add("5465675676543534")
    flowData.add("23")
    flowData.add("1")

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        FlowSection(title = R.string.flow_row) {
            FlowRowView(context, flowData)
        }

        FlowSection(title = R.string.flow_column) {
            FlowColumnView(context, flowData)
        }
    }
}

@Composable
private fun FlowSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowView(
    context: Context,
    data: MutableList<String>
) {
    FlowRow(modifier = Modifier.padding(5.dp)) {
        val itemModifier = Modifier
            .padding(vertical = 5.dp, horizontal = 5.dp)
            .background(
                color = Color(0xFFCCC2DC),
                shape = RoundedCornerShape(10.dp)
            )

        data.forEach {
            Box(modifier = itemModifier) {
                TextButton(
                    onClick = {
                        Toast
                            .makeText(context, it, Toast.LENGTH_SHORT)
                            .show()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    )
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowColumnView(
    context: Context,
    data: MutableList<String>
) {
    FlowColumn {
        val itemModifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .background(
                color = Color(0xFFCCC2DC),
                shape = RoundedCornerShape(10.dp)
            )

        data.forEach {
            Box(modifier = itemModifier) {
                TextButton(
                    onClick = {
                        Toast
                            .makeText(context, it, Toast.LENGTH_SHORT)
                            .show()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    )
                ) {
                    Text(text = it)
                }
            }

        }
    }
}