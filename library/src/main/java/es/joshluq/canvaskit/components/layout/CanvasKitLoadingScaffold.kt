package es.joshluq.canvaskit.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import es.joshluq.canvaskit.components.feedback.CanvasKitLoadingSpinner
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitLoadingScaffold is an advanced layout container that automatically handles
 * application loading states with artisanal precision.
 *
 * ### Loading Strategies:
 * - **ReplaceContent:** (Default) Hides the main content and shows a central spinner. Best for initial data loads.
 * - **OverlayFullscreen:** Blocks the entire screen (including top/bottom bars) with a semi-transparent overlay. Use for critical blocking operations (e.g. submitting a payment).
 * - **ProgressLine:** Shows a subtle linear progress bar below the TopBar. Best for background refreshes where the user can still interact with the current content.
 *
 * @param isLoading Whether the screen is currently in a loading state.
 * @param loadingStrategy Defines how the loading state is presented to the user.
 * @param loadingContent The composable to display when loading. Defaults to [CanvasKitLoadingSpinner].
 * @param modifier The modifier to be applied to the layout.
 * @param topBar Top app bar of the screen.
 * @param bottomBar Bottom bar of the screen.
 * @param snackbarHost Component to host Snackbars.
 * @param floatingActionButton Main action button of the screen.
 * @param floatingActionButtonPosition Position of the FAB.
 * @param containerColor The background color for the scaffold.
 * @param contentColor The preferred color for content inside this scaffold.
 * @param contentWindowInsets Window insets to be passed to content.
 * @param content The main content of the screen.
 */
@Composable
fun CanvasKitLoadingScaffold(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    loadingStrategy: CanvasKitLoadingStrategy = CanvasKitLoadingStrategy.ReplaceContent,
    loadingContent: @Composable () -> Unit = {
        CanvasKitLoadingSpinner(
            color = CanvasKitTheme.colors.brandAccent,
            modifier = Modifier.size(48.dp)
        )
    },
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    contentColor: Color = CanvasKitTheme.colors.textPrimary,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Main Content
            if (loadingStrategy == CanvasKitLoadingStrategy.ReplaceContent) {
                if (!isLoading) {
                    content(paddingValues)
                }
            } else {
                content(paddingValues)
            }

            // In-place Loading Replacement
            if (loadingStrategy == CanvasKitLoadingStrategy.ReplaceContent) {
                AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.matchParentSize()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        loadingContent()
                    }
                }
            }

            // Progress Line Strategy
            if (loadingStrategy == CanvasKitLoadingStrategy.ProgressLine) {
                AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = CanvasKitTheme.colors.brandAccent,
                        trackColor = CanvasKitTheme.colors.brandAccent.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }

    // Fullscreen Overlay Loading
    if (loadingStrategy == CanvasKitLoadingStrategy.OverlayFullscreen && isLoading) {
        Dialog(
            onDismissRequest = { /* Cannot be dismissed by user */ },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false // Fills entire screen
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    // Consume all pointer input to block interaction completely behind the dialog
                    .pointerInput(Unit) {},
                contentAlignment = Alignment.Center
            ) {
                loadingContent()
            }
        }
    }
}
