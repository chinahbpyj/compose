package com.example.learn.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.learn.R

@Composable
fun RadioButtonView() {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var superheroResponse by remember {
            mutableStateOf("")
        }

        ListRadioButton(superheroResponse) {
            superheroResponse = it
        }

        if (superheroResponse.isNotEmpty()) {
            Text(
                text = superheroResponse,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .padding(vertical = 15.dp)
            )
        }
    }
}

@Composable
fun ListRadioButton(name: String, onClick: (String) -> Unit) {
    val superheroNames = listOf(
        R.string.spark,
        R.string.lenz,
        R.string.bug_chaos,
        R.string.frag
    )

    superheroNames.forEach {
        val text = stringResource(it)
        val selected = name == text
        RadioButton(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            selected = selected,
            onClick = {
                onClick(text)
            }
        )
    }
}

@Composable
fun RadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .selectable(
                selected,
                onClick = onClick,
                role = Role.RadioButton
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text, Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.padding(8.dp)) {
                RadioButton(selected, onClick = null)
            }
        }
    }
}
