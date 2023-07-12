package com.example.learn.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.learn.R

private val response = mutableStateListOf<String>()

@Composable
fun CheckBoxView() {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ListCustomCheckBox()

        var check by remember {
            mutableStateOf(false)
        }

        SingleCheckBox(check) {
            check = it
        }

        if (check) {
            ListCheckBox()
        }
    }
}

@Composable
private fun SingleCheckBox(check: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.show_list_checkbox),
        )

        Box(Modifier.padding(8.dp)) {
            Checkbox(check, onCheckedChange = onCheckedChange)
        }
    }
}

@Composable
private fun ListCheckBox() {
    val possibleAnswers = listOf(
        R.string.read,
        R.string.work_out,
        R.string.draw,
        R.string.play_games,
        R.string.dance,
        R.string.watch_movies,
    )

    possibleAnswers.forEach {
        val text = stringResource(it)
        val selected = response.contains(text)
        CheckboxRow(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            selected = selected,
            onOptionSelected = {
                if (!selected) {
                    response.add(text)
                } else {
                    response.remove(text)
                }
            }
        )
    }

    if (!response.isEmpty()) {
        Text(
            text = response.toList().toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        )
    }
}

@Composable
private fun CheckboxRow(
    text: String,
    selected: Boolean,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier,
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
            .clickable(onClick = onOptionSelected)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text, Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Box(Modifier.padding(8.dp)) {
                Checkbox(selected, onCheckedChange = null)
            }
        }
    }
}

@Composable
private fun ListCustomCheckBox() {
    val possibleAnswers = listOf(
        R.string.read,
        R.string.work_out,
        R.string.draw,
        R.string.play_games,
        R.string.dance,
        R.string.watch_movies,
    )

    possibleAnswers.forEach {
        val text = stringResource(it)
        val selected = response.contains(text)

        CustomCheckBoxItem(itemTitle = text, selected = selected, onToggle = {
            if (!selected) {
                response.add(text)
            } else {
                response.remove(text)
            }
        })
    }
}

@Composable
private fun CustomCheckBoxItem(
    itemTitle: String,
    selected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .toggleable(
                value = selected,
                onValueChange = { onToggle() }
            )
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val image = painterResource(R.drawable.ic_launcher_background)
        Image(
            painter = image,
            contentDescription = null, // decorative
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = itemTitle,
            modifier = Modifier
                .padding(16.dp)
                .weight(1f), // Break line if the title is too long
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.width(16.dp))
        SelectCheckBox(selected = selected)
    }
}

@Composable
private fun SelectCheckBox(
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val icon = if (selected) Icons.Filled.Done else Icons.Filled.Add
    val iconColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.primary
    }
    val borderColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    }
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onPrimary
    }
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier.size(36.dp, 36.dp)
    ) {
        Image(
            imageVector = icon,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier.padding(8.dp),
            contentDescription = null
        )
    }
}
