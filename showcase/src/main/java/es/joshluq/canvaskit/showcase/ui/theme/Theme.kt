package es.joshluq.canvaskit.showcase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import es.joshluq.canvaskit.core.tokens.darkCanvasKitColors
import es.joshluq.canvaskit.core.tokens.lightCanvasKitColors
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
fun ShowcaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val canvasColors = if (darkTheme) darkCanvasKitColors() else lightCanvasKitColors()

    val materialColorScheme = if (darkTheme) {
        darkColorScheme(
            primary = canvasColors.brandAccent,
            background = canvasColors.backgroundPrimary,
            surface = canvasColors.backgroundSecondary,
            onPrimary = canvasColors.backgroundPrimary,
            onBackground = canvasColors.textPrimary,
            onSurface = canvasColors.textPrimary
        )
    } else {
        lightColorScheme(
            primary = canvasColors.brandAccent,
            background = canvasColors.backgroundPrimary,
            surface = canvasColors.backgroundSecondary,
            onPrimary = canvasColors.backgroundPrimary,
            onBackground = canvasColors.textPrimary,
            onSurface = canvasColors.textPrimary
        )
    }

    CanvasKitTheme(
        darkTheme = darkTheme,
        colors = canvasColors
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}

