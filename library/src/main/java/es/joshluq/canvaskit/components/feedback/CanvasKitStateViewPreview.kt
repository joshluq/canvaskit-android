package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun PreviewStateView(
    type: String = "Empty"
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (type) {
            "Empty" -> CanvasKitStateView(
                title = "No messages yet",
                description = "Your primary inbox is currently empty. Check again later for new notifications.",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Inbox,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = CanvasKitTheme.colors.brandAccent
                    )
                }
            )
            "Error" -> CanvasKitStateView(
                title = "Connection lost",
                description = "We couldn't reach the server. Please check your internet connection.",
                variantColor = CanvasKitTheme.colors.error,
                icon = {
                    Icon(
                        imageVector = Icons.Default.CloudOff,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = CanvasKitTheme.colors.error
                    )
                },
                action = {
                    CanvasKitButton(onClick = {}) { contentColor ->
                        Text("Retry Connection", color = contentColor)
                    }
                }
            )
            "Success" -> CanvasKitStateView(
                title = "All caught up!",
                description = "You've completed all your tasks for today. Time to relax.",
                variantColor = CanvasKitTheme.colors.success,
                icon = {
                    Icon(
                        imageVector = Icons.Default.TaskAlt,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = CanvasKitTheme.colors.success
                    )
                }
            )
        }
    }
}

@Preview(name = "Empty State - Light", showBackground = true)
@Composable
internal fun CanvasKitStateViewEmptyLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        PreviewStateView("Empty")
    }
}

@Preview(name = "Error State - Dark", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitStateViewErrorDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        PreviewStateView("Error")
    }
}

@Preview(name = "Success State - Light", showBackground = true)
@Composable
internal fun CanvasKitStateViewSuccessLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        PreviewStateView("Success")
    }
}
