package es.joshluq.canvaskit.components.sheets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import es.joshluq.canvaskit.foundations.modifiers.rememberAtmosphericHaloBrush
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * CanvasKitBottomSheet is a premium modal container that slides up from the bottom.
 * It features the signature Atelier specular hairline along the top curved rim and a refined drag handle.
 *
 * @param onDismissRequest Callback to fire when the sheet should be closed.
 * @param modifier Root layout modifier.
 * @param sheetState The state of the bottom sheet.
 * @param showDragHandle Whether to show the drag handle at the top.
 * @param specularHighlight Whether to render the signature specular hairline border along the top rim.
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
    specularHighlight: Boolean = true,
    containerColor: Color = CanvasKitTheme.colors.backgroundPrimary,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val specularBrush = rememberAtmosphericHaloBrush()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = sheetShape,
        containerColor = containerColor,
        scrimColor = scrimColor,
        dragHandle = null,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (specularHighlight) {
                            Modifier.border(BorderStroke(1.dp, specularBrush), sheetShape)
                        } else {
                            Modifier
                        }
                    )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    if (showDragHandle) {
                        CanvasKitDragHandle()
                    }
                    content()
                }
            }
        }
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
    val shapes = CanvasKitTheme.shapes

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.md),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(4.dp)
                .clip(shapes.pill)
                .background(colors.textSecondary.copy(alpha = 0.28f))
                .border(
                    BorderStroke(0.5.dp, colors.borderSubtle.copy(alpha = 0.40f)),
                    shapes.pill
                )
        )
    }
}
