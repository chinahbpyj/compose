package com.example.learn.view

import com.example.learn.R
import androidx.annotation.StringRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learn.ui.theme.LearnTheme

enum class Sections(@StringRes val titleResId: Int) {
    TabA(R.string.tab_a),
    TabB(R.string.tab_b),
    TabC(R.string.tab_c)
}

class TabContent(val section: Sections, val content: @Composable () -> Unit)

@Composable
fun TabView() {
    var currentSection by remember { mutableStateOf(Sections.TabA) }
    val tabContent = mutableListOf<TabContent>()
    val tabA = TabContent(Sections.TabA) { TabContent(color = Color(0xFFD0BCFF)) }
    tabContent.add(tabA)
    val tabB = TabContent(Sections.TabB) { TabContent(color = Color(0xFFEFB8C8)) }
    tabContent.add(tabB)
    val tabC = TabContent(Sections.TabC) { TabContent(color = Color(0xFFCCC2DC)) }
    tabContent.add(tabC)

    TabScreenContent(
        currentSection = currentSection,
        isExpandedScreen = false,
        updateSection = {
            currentSection = it
        },
        tabContent = tabContent
    )
}

@Composable
private fun TabContent(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {

    }
}

@Composable
private fun TabScreenContent(
    currentSection: Sections,
    isExpandedScreen: Boolean,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>,
    modifier: Modifier = Modifier
) {
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }
    Column(modifier) {
        TabRow(selectedTabIndex, updateSection, tabContent, isExpandedScreen)
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
        Box(modifier = Modifier.weight(1f)) {
            tabContent[selectedTabIndex].content()
        }
    }
}

@Composable
private fun TabDivider() {
    Divider(
        thickness = 1.dp,
        color = Color(0xFF000000)
    )
}

@Composable
private fun TabIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    /*TabRowDefaults.Indicator(
        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
    )*/

    TabRowDefaults.Indicator(
        modifier = Modifier
            .customTabIndicatorOffset(tabPositions[selectedTabIndex]),
        height = 3.dp,
        color = Color(0xFF6650a4)
    )
}

private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )

    val tabWidth = currentTabWidth / 3
    val offsetX = indicatorOffset + tabWidth

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = offsetX)
        .width(tabWidth)
}

@Composable
private fun TabRow(
    selectedTabIndex: Int,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>,
    isExpandedScreen: Boolean
) {
    when (isExpandedScreen) {
        false -> {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = TabRowDefaults.containerColor,
                indicator = { TabIndicator(it, selectedTabIndex) },
                divider = { TabDivider() }) {
                TabRowContent(selectedTabIndex, updateSection, tabContent)
            }
        }

        true -> {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = MaterialTheme.colorScheme.primary,
                edgePadding = 0.dp
            ) {
                TabRowContent(
                    selectedTabIndex = selectedTabIndex,
                    updateSection = updateSection,
                    tabContent = tabContent,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun TabRowContent(
    selectedTabIndex: Int,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>,
    modifier: Modifier = Modifier
) {
    tabContent.forEachIndexed { index, content ->
        val colorText = if (selectedTabIndex == index) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        }
        Tab(
            selected = selectedTabIndex == index,
            onClick = { updateSection(content.section) },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(id = content.section.titleResId),
                color = colorText,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.paddingFromBaseline(top = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LearnTheme {
        TabView()
    }
}