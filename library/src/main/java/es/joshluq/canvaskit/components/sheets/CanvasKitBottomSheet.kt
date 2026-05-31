package es.joshluq.canvaskit.components.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitBottomSheet is a premium modal container that slides up from the bottom.
 * It follows Material 3 Expressive guidelines with organic rounding and refined drag handle.
 *
 * @param onDismissRequest Callback to fire when the sheet should be closed.
 * @param modifier Root layout modifier.
 * @param sheetState The state of the bottom sheet.
 * @param showDragHandle Whether to show the drag handle at the top.
 * @param containerColor Background color of the sheet.
 * @param scrimColor Color of the background overlay when the sheet is open.
 * @param content Composable slot for the sheet's content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasKitBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    showDragHandle: Boolean = true,
    containerColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = containerColor,
        scrimColor = scrimColor,
        dragHandle = if (showDragHandle) {
            { CanvasKitDragHandle() }
        } else null,
        content = content
    )
}

/**
 * A refined, artisanal drag handle for [CanvasKitBottomSheet].
 */
@Composable
fun CanvasKitDragHandle(
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.md),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(4.dp)
                .clip(CanvasKitTheme.shapes.pill)
                .background(colors.borderSubtle)
        )
    }
}
