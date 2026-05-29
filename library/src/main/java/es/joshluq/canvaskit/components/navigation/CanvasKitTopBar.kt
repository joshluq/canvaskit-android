package es.joshluq.canvaskit.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.core.tokens.LocalCanvasKitContentColor
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitTopBar is a premium, flexible top app bar component built to handle
 * status bar insets natively. It supports start and center title alignments,
 * custom actions, back buttons, and visual separations in compliance with design tokens.
 *
 * @param title Composable content block containing the title text.
 * @param modifier Root layout modifier.
 * @param navigationIcon Optional Composable block containing a back button or drawer control.
 * @param actions Optional Composable block containing horizontal icon actions.
 * @param centeredTitle When true, centers the title horizontally regardless of icon spacing.
 * @param transparent When true, renders a transparent background with no bottom boundary border.
 * @param showBottomBorder When true, renders a thin separator border at the bottom.
 */
@Composable
fun CanvasKitTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    centeredTitle: Boolean = false,
    transparent: Boolean = false,
    showBottomBorder: Boolean = true
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    val backgroundColor = if (transparent) Color.Transparent else colors.backgroundPrimary

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .statusBarsPadding()
    ) {
        CompositionLocalProvider(LocalCanvasKitContentColor provides colors.textPrimary) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                // Title Layer
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = if (centeredTitle) 72.dp else if (navigationIcon != null) 64.dp else spacing.md,
                            end = if (centeredTitle) 72.dp else if (actions != null) 72.dp else spacing.md
                        ),
                    contentAlignment = if (centeredTitle) Alignment.Center else Alignment.CenterStart
                ) {
                    title()
                }

                // Navigation Icon Layer
                if (navigationIcon != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = spacing.sm),
                        contentAlignment = Alignment.Center
                    ) {
                        navigationIcon()
                    }
                }

                // Actions Layer
                if (actions != null) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = spacing.sm),
                        horizontalArrangement = Arrangement.spacedBy(spacing.xxs),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        actions()
                    }
                }
            }
        }

        // Bottom separator
        if (showBottomBorder && !transparent) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colors.borderSubtle)
            )
        }
    }
}
