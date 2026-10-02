package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.navigation.CanvasKitPagerIndicator
import es.joshluq.canvaskit.components.navigation.CanvasKitPagerIndicatorVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitSegmentItem
import es.joshluq.canvaskit.components.navigation.CanvasKitSegmentedControl
import es.joshluq.canvaskit.components.navigation.CanvasKitTab
import es.joshluq.canvaskit.components.navigation.CanvasKitTabIndicatorVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitTabRow
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * NavigationScreen showcases Segmented Controls, Pager Indicators, and Tabs.
 */
@Composable
fun NavigationScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    // States for interactive demos
    var selectedSegment1 by remember { mutableIntStateOf(0) }
    var selectedSegment2 by remember { mutableIntStateOf(1) }
    var pagerPage1 by remember { mutableIntStateOf(1) }
    var pagerPage2 by remember { mutableIntStateOf(2) }
    var activeTab1 by remember { mutableIntStateOf(0) }
    var activeTab2 by remember { mutableIntStateOf(1) }

    val segmentOptions = remember {
        listOf(
            CanvasKitSegmentItem(id = "day", label = "Daily", icon = Icons.Default.DateRange),
            CanvasKitSegmentItem(id = "week", label = "Weekly", icon = Icons.Default.List),
            CanvasKitSegmentItem(id = "month", label = "Monthly", icon = Icons.Default.Person)
        )
    }

    val authSegments = remember {
        listOf(
            CanvasKitSegmentItem(id = "signin", label = "Sign In"),
            CanvasKitSegmentItem(id = "signup", label = "Sign Up")
        )
    }

    val tabList = remember { listOf("Overview", "Transactions", "Settings") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundPrimary)
    ) {
        CanvasKitTopBar(
            title = {
                Text(
                    text = "Navigation & Pagination",
                    style = typography.headingMedium,
                    color = colors.textPrimary
                )
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = colors.textPrimary
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            // 1. Segmented Control
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Segmented Control",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "3-Option with Icons (Sliding Pill Spring Physics):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitSegmentedControl(
                        items = segmentOptions,
                        selectedIndex = selectedSegment1,
                        onSegmentSelected = { selectedSegment1 = it }
                    )

                    Spacer(modifier = Modifier.height(spacing.xs))

                    Text(
                        text = "2-Option Binary Switch:",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitSegmentedControl(
                        items = authSegments,
                        selectedIndex = selectedSegment2,
                        onSegmentSelected = { selectedSegment2 = it }
                    )
                }
            }

            // 2. Pager Indicator
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Pager Indicators",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.md),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Worm / Expanding Pill (Tap a dot):",
                        style = typography.labelSmall,
                        color = colors.textSecondary,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    CanvasKitPagerIndicator(
                        pageCount = 5,
                        currentPage = pagerPage1,
                        variant = CanvasKitPagerIndicatorVariant.Worm,
                        onPageClick = { pagerPage1 = it }
                    )

                    Spacer(modifier = Modifier.height(spacing.xs))

                    Text(
                        text = "Dots Variant (Discreet):",
                        style = typography.labelSmall,
                        color = colors.textSecondary,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    CanvasKitPagerIndicator(
                        pageCount = 5,
                        currentPage = pagerPage2,
                        variant = CanvasKitPagerIndicatorVariant.Dots,
                        onPageClick = { pagerPage2 = it }
                    )
                }
            }

            // 3. Tab Rows
            CanvasKitCard(
                modifier = Modifier.fillMaxWidth(),
                header = {
                    Text(
                        text = "Tab Rows",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                }
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Text(
                        text = "Underline Variant (With Icons):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitTabRow(
                        selectedTabIndex = activeTab1,
                        tabsCount = tabList.size,
                        indicatorVariant = CanvasKitTabIndicatorVariant.Underline
                    ) {
                        tabList.forEachIndexed { index, title ->
                            val isSelected = activeTab1 == index
                            val icon = when (index) {
                                0 -> Icons.Default.Home
                                1 -> Icons.Default.Favorite
                                else -> Icons.Default.Person
                            }
                            CanvasKitTab(
                                selected = isSelected,
                                onClick = { activeTab1 = index },
                                modifier = Modifier.weight(1f),
                                icon = {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = if (isSelected) colors.brandAccent else colors.textSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                },
                                text = {
                                    Text(
                                        text = title,
                                        style = typography.labelLarge.copy(
                                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                                        ),
                                        color = if (isSelected) colors.brandAccent else colors.textSecondary
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(spacing.sm))

                    Text(
                        text = "Pill Variant (Encapsulated Capsule):",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                    CanvasKitTabRow(
                        selectedTabIndex = activeTab2,
                        tabsCount = tabList.size,
                        indicatorVariant = CanvasKitTabIndicatorVariant.Pill
                    ) {
                        tabList.forEachIndexed { index, title ->
                            val isSelected = activeTab2 == index
                            CanvasKitTab(
                                selected = isSelected,
                                onClick = { activeTab2 = index },
                                modifier = Modifier.weight(1f),
                                text = {
                                    Text(
                                        text = title,
                                        style = typography.labelLarge.copy(
                                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
                                        ),
                                        color = if (isSelected) colors.brandPrimary else colors.textSecondary
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
