package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.feedback.CanvasKitSkeleton
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlinx.coroutines.delay

/**
 * SkeletonsScreen showcases the "Artisanal Precision" Skeleton component.
 */
@Composable
fun SkeletonsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography
    val shapes = CanvasKitTheme.shapes

    // Simulated Loading State
    var isLoading by remember { mutableStateOf(true) }

    // Auto-toggle loading state for demonstration purposes
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(3000)
            isLoading = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        // Top Bar
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Skeletons",
                        style = typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Indicadores de carga con animaciones suaves (shimmer).",
                        style = typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Atrás"
                ) { contentColor ->
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = contentColor
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            
            // ──────────────────────────────────────────────────────────
            // Controls
            // ──────────────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isLoading) "Cargando datos..." else "Datos listos",
                    style = typography.labelLarge,
                    color = colors.textPrimary
                )
                CanvasKitButton(
                    onClick = { isLoading = !isLoading },
                    variant = CanvasKitButtonVariant.Secondary,
                ) {
                    Text(
                        text = if (isLoading) "Detener" else "Recargar",
                        style = typography.labelLarge,
                        color = colors.textPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.sm))

            // ──────────────────────────────────────────────────────────
            // Profile Card Example
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Perfil de Usuario",
                description = "Ejemplo de avatares y textos."
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.md)
                ) {
                    if (isLoading) {
                        CanvasKitSkeleton(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                            CanvasKitSkeleton(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(20.dp)
                                    .clip(shapes.small)
                            )
                            CanvasKitSkeleton(
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(14.dp)
                                    .clip(shapes.small)
                            )
                        }
                    } else {
                        // Data loaded state
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(colors.brandAccent.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = colors.brandAccent)
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                            Text(text = "Ana Martínez", style = typography.headingMedium, color = colors.textPrimary)
                            Text(text = "Product Designer", style = typography.labelLarge, color = colors.textSecondary)
                        }
                    }
                }
            }

            // ──────────────────────────────────────────────────────────
            // Article Card Example
            // ──────────────────────────────────────────────────────────
            SpecSectionCard(
                title = "Artículo (Card)",
                description = "Ejemplo de uso de skeletons para imágenes de portada y párrafos."
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    if (isLoading) {
                        CanvasKitSkeleton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(shapes.medium)
                        )
                        Spacer(modifier = Modifier.height(spacing.xs))
                        CanvasKitSkeleton(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(24.dp)
                                .clip(shapes.small)
                        )
                        CanvasKitSkeleton(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(24.dp)
                                .clip(shapes.small)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(shapes.medium)
                                .background(colors.backgroundPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Imagen del artículo", style = typography.labelLarge, color = colors.textSecondary)
                        }
                        Spacer(modifier = Modifier.height(spacing.xs))
                        Text(
                            text = "Diseño de interfaces con CanvasKit y Compose",
                            style = typography.headingLarge,
                            color = colors.textPrimary
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(spacing.lg))
        }
    }
}
