package es.joshluq.canvaskit.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Preview(name = "Replace Content Strategy")
@Composable
fun CanvasKitLoadingScaffoldReplacePreview() {
    CanvasKitTheme {
        CanvasKitLoadingScaffold(
            isLoading = true,
            loadingStrategy = CanvasKitLoadingStrategy.ReplaceContent,
            topBar = {
                CanvasKitTopBar(
                    title = { Text("Pantalla Cargando", color = CanvasKitTheme.colors.textPrimary) }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Contenido Listo", color = CanvasKitTheme.colors.textPrimary)
            }
        }
    }
}

@Preview(name = "Overlay Strategy")
@Composable
fun CanvasKitLoadingScaffoldOverlayPreview() {
    CanvasKitTheme {
        CanvasKitLoadingScaffold(
            isLoading = true,
            loadingStrategy = CanvasKitLoadingStrategy.OverlayFullscreen,
            topBar = {
                CanvasKitTopBar(
                    title = { Text("Pantalla Bloqueada", color = CanvasKitTheme.colors.textPrimary) }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Contenido de fondo visible pero no clickeable", color = CanvasKitTheme.colors.textPrimary)
            }
        }
    }
}
