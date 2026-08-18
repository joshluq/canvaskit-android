package es.joshluq.canvaskit.components.feedback

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp

/**
 * CanvasKitConfirmDialog is a high-level, opinionated dialog component for common confirmation flows.
 * It follows the "Kit" philosophy of reducing boilerplate for the most frequent UI patterns.
 *
 * ### Use Case:
 * Use this component for simple "Confirm/Cancel" flows, such as deletions, logouts, or discard changes warnings.
 * It eliminates the need to manually build [CanvasKitDialog] and [CanvasKitDialogContent] structures.
 *
 * @param title The title text of the dialog.
 * @param message The body message text of the dialog.
 * @param confirmText Text for the primary confirmation button.
 * @param onConfirm Callback when the confirm button is clicked.
 * @param onDismissRequest Callback when the dialog is dismissed or cancel is clicked.
 * @param modifier Root modifier for the dialog.
 * @param cancelText Text for the cancel button. Defaults to "Cancel".
 * @param icon Optional header icon to display above the title.
 * @param isDestructive If true, the confirm button uses the error color theme.
 */
@Composable
fun CanvasKitConfirmDialog(
    title: String,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    cancelText: String = "Cancel",
    icon: ImageVector? = null,
    isDestructive: Boolean = false
) {
    val colors = CanvasKitTheme.colors
    val typography = CanvasKitTheme.typography

    CanvasKitDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        CanvasKitDialogContent(
            icon = icon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = if (isDestructive) colors.error else colors.brandAccent,
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            title = {
                Text(
                    text = title,
                    style = typography.headingLarge,
                    color = colors.textPrimary
                )
            },
            buttons = {
                CanvasKitButton(
                    onClick = onDismissRequest,
                    variant = CanvasKitButtonVariant.Ghost,
                    text = cancelText
                )
                CanvasKitButton(
                    onClick = {
                        onConfirm()
                        onDismissRequest()
                    },
                    variant = CanvasKitButtonVariant.Primary,
                    text = confirmText,
                    // We could add a 'destructive' variant to Button later, 
                    // for now we can override content color if needed, but Primary is okay.
                )
            }
        ) {
            Text(
                text = message,
                style = typography.bodyMedium,
                color = colors.textSecondary
            )
        }
    }
}
