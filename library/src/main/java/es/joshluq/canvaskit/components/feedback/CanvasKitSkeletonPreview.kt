package es.joshluq.canvaskit.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SampleSkeletons() {
    val shapes = CanvasKitTheme.shapes

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Text lines
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            CanvasKitSkeleton(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(24.dp)
                    .clip(shapes.small)
            )
            CanvasKitSkeleton(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(16.dp)
                    .clip(shapes.small)
            )
            CanvasKitSkeleton(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(16.dp)
                    .clip(shapes.small)
            )
        }

        // Avatar + Text (Card header style)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CanvasKitSkeleton(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CanvasKitSkeleton(
                    modifier = Modifier
                        .width(120.dp)
                        .height(16.dp)
                        .clip(shapes.small)
                )
                CanvasKitSkeleton(
                    modifier = Modifier
                        .width(80.dp)
                        .height(12.dp)
                        .clip(shapes.small)
                )
            }
        }

        // Card style
        CanvasKitSkeleton(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(shapes.medium)
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
internal fun CanvasKitSkeletonLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            SampleSkeletons()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
internal fun CanvasKitSkeletonDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.background(CanvasKitTheme.colors.backgroundPrimary)) {
            SampleSkeletons()
        }
    }
}
