package es.joshluq.canvaskit.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

@Composable
private fun SampleCardContent(
    title: String,
    subtitle: String,
    description: String,
    footerText: String
) {
    CanvasKitCard(
        header = {
            Column {
                Text(
                    text = title,
                    style = CanvasKitTheme.typography.headingMedium,
                    color = CanvasKitTheme.colors.textPrimary
                )
                Text(
                    text = subtitle,
                    style = CanvasKitTheme.typography.labelSmall,
                    color = CanvasKitTheme.colors.textSecondary
                )
            }
        },
        footer = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = footerText,
                    style = CanvasKitTheme.typography.labelLarge,
                    color = CanvasKitTheme.colors.brandAccent
                )
            }
        }
    ) {
        Text(
            text = description,
            style = CanvasKitTheme.typography.bodyMedium,
            color = CanvasKitTheme.colors.textSecondary
        )
    }
}

@Composable
private fun CardsPreviewContainer() {
    val spacing = CanvasKitTheme.spacing
    Column(
        verticalArrangement = Arrangement.spacedBy(spacing.md),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Outlined Card
        CanvasKitCard(
            variant = CanvasKitCardVariant.Outlined,
            header = {
                Text(
                    text = "Outlined Card",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = CanvasKitTheme.colors.textPrimary
                )
            }
        ) {
            Text(
                text = "Este es un contenedor con borde sutil, ideal para estructurar layouts limpios.",
                style = CanvasKitTheme.typography.bodyMedium,
                color = CanvasKitTheme.colors.textSecondary
            )
        }

        // Elevated Card
        CanvasKitCard(
            variant = CanvasKitCardVariant.Elevated,
            header = {
                Text(
                    text = "Elevated Card",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = CanvasKitTheme.colors.textPrimary
                )
            }
        ) {
            Text(
                text = "Este contenedor tiene una elevación sutil para destacar sobre el fondo primario.",
                style = CanvasKitTheme.typography.bodyMedium,
                color = CanvasKitTheme.colors.textSecondary
            )
        }

        // Flat Card
        CanvasKitCard(
            variant = CanvasKitCardVariant.Flat,
            header = {
                Text(
                    text = "Flat Card",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = CanvasKitTheme.colors.textPrimary
                )
            }
        ) {
            Text(
                text = "Este contenedor usa el color secundario como fondo plano sin sombras ni bordes.",
                style = CanvasKitTheme.typography.bodyMedium,
                color = CanvasKitTheme.colors.textSecondary
            )
        }

        // Selected Card
        CanvasKitCard(
            variant = CanvasKitCardVariant.Outlined,
            selected = true,
            header = {
                Text(
                    text = "Selected Card",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = CanvasKitTheme.colors.textPrimary
                )
            }
        ) {
            Text(
                text = "Este contenedor está seleccionado y tiene un borde de acento de marca dinámico.",
                style = CanvasKitTheme.typography.bodyMedium,
                color = CanvasKitTheme.colors.textSecondary
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun CanvasKitCardLightPreview() {
    CanvasKitTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            CardsPreviewContainer()
        }
    }
}

@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF080C14)
@Composable
fun CanvasKitCardDarkPreview() {
    CanvasKitTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            CardsPreviewContainer()
        }
    }
}

@Preview(name = "RTL Layout", showBackground = true)
@Composable
fun CanvasKitCardRtlPreview() {
    CanvasKitTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.padding(16.dp)) {
                CardsPreviewContainer()
            }
        }
    }
}

@Preview(name = "Large Font Scale (2.0x)", showBackground = true, fontScale = 2.0f)
@Composable
fun CanvasKitCardFontScalePreview() {
    CanvasKitTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CardsPreviewContainer()
        }
    }
}
