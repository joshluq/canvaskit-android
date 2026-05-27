package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * TopBarScreen showcases different styles, alignments, transparent states, and
 * a live interactive playground for CanvasKitTopBar configurations.
 */
@Composable
fun TopBarScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    // Playground state variables
    var centeredTitleState by remember { mutableStateOf(false) }
    var transparentState by remember { mutableStateOf(false) }
    var showBorderState by remember { mutableStateOf(true) }
    var showNavIconState by remember { mutableStateOf(true) }
    var showActionsState by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Top App Bar",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Cabeceras de aplicación con soporte de insets, alineación y acciones flotantes.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Atrás",
                        tint = colors.brandPrimary
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

        // Section 1: Live Playground
        SpecSectionCard(
            title = "Interactive Playground",
            description = "Modifica los atributos del TopBar en tiempo real para observar su comportamiento."
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.md),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Live Render Area Container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(shapes.large)
                        .then(
                            if (transparentState) {
                                // Dynamic background gradient to showcase transparent contrast
                                Modifier.background(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            colors.brandAccent.copy(alpha = 0.3f),
                                            colors.backgroundSecondary
                                        )
                                    )
                                )
                            } else {
                                Modifier.background(colors.backgroundSecondary)
                            }
                        )
                        .border(width = 1.dp, color = colors.borderSubtle, shape = shapes.large),
                    contentAlignment = Alignment.TopCenter
                ) {
                    CanvasKitTopBar(
                        title = {
                            Text(
                                text = "Preview Title",
                                style = CanvasKitTheme.typography.headingMedium,
                                color = colors.textPrimary
                            )
                        },
                        centeredTitle = centeredTitleState,
                        transparent = transparentState,
                        showBottomBorder = showBorderState,
                        navigationIcon = if (showNavIconState) {
                            {
                                CanvasKitIconButton(onClick = {
                                    Toast.makeText(context, "Back Pressed", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Atrás",
                                        tint = colors.brandPrimary
                                    )
                                }
                            }
                        } else null,
                        actions = if (showActionsState) {
                            {
                                CanvasKitIconButton(onClick = {
                                    Toast.makeText(context, "Search Clicked", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Buscar",
                                        tint = colors.brandPrimary
                                    )
                                }
                                CanvasKitIconButton(onClick = {
                                    Toast.makeText(context, "Menu Clicked", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = "Configuración",
                                        tint = colors.brandPrimary
                                    )
                                }
                            }
                        } else null
                    )
                }

                // Toggles Config Grid
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        PlaygroundToggleCard(
                            label = "Centered Title",
                            checked = centeredTitleState,
                            onToggle = { centeredTitleState = it },
                            modifier = Modifier.weight(1f)
                        )
                        PlaygroundToggleCard(
                            label = "Transparent Bar",
                            checked = transparentState,
                            onToggle = { transparentState = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        PlaygroundToggleCard(
                            label = "Show Bottom Border",
                            checked = showBorderState,
                            onToggle = { showBorderState = it },
                            modifier = Modifier.weight(1f)
                        )
                        PlaygroundToggleCard(
                            label = "Show Nav Back Icon",
                            checked = showNavIconState,
                            onToggle = { showNavIconState = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    PlaygroundToggleCard(
                        label = "Show Action Icons (Search & Settings)",
                        checked = showActionsState,
                        onToggle = { showActionsState = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Section 2: Concrete Variant Examples
        SpecSectionCard(
            title = "TopBar Layout Styles",
            description = "Estructuras comunes y jerarquías visuales disponibles."
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(spacing.md),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Style 1: Clean Centered Title
                Text(
                    text = "1. Estilo Centrado (Centered)",
                    style = CanvasKitTheme.typography.labelSmall,
                    color = colors.textSecondary
                )
                CanvasKitTopBar(
                    title = {
                        Text(
                            text = "Detalles del Item",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                    },
                    centeredTitle = true,
                    navigationIcon = {
                        CanvasKitIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Atrás",
                                tint = colors.brandPrimary
                            )
                        }
                    },
                    actions = {
                        CanvasKitIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar",
                                tint = colors.brandPrimary
                            )
                        }
                    },
                    modifier = Modifier
                        .clip(shapes.medium)
                        .border(width = 1.dp, color = colors.borderSubtle, shape = shapes.medium)
                )

                Spacer(modifier = Modifier.height(spacing.xs))

                // Style 2: Dense Navigation Bar
                Text(
                    text = "2. Cabecera Start-Aligned con Múltiples Acciones",
                    style = CanvasKitTheme.typography.labelSmall,
                    color = colors.textSecondary
                )
                CanvasKitTopBar(
                    title = {
                        Text(
                            text = "Galería de Medios",
                            style = CanvasKitTheme.typography.headingMedium,
                            color = colors.textPrimary
                        )
                    },
                    navigationIcon = {
                        CanvasKitIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = colors.brandPrimary
                            )
                        }
                    },
                    actions = {
                        CanvasKitIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notificaciones",
                                tint = colors.brandPrimary
                            )
                        }
                        CanvasKitIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Compartir",
                                tint = colors.brandPrimary
                            )
                        }
                        CanvasKitIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = colors.brandPrimary
                            )
                        }
                    },
                    modifier = Modifier
                        .clip(shapes.medium)
                        .border(width = 1.dp, color = colors.borderSubtle, shape = shapes.medium)
                )
            }
        }
    }
}
}

@Composable
private fun PlaygroundToggleCard(
    label: String,
    checked: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    CanvasKitCard(
        variant = CanvasKitCardVariant.Outlined,
        selected = checked,
        onClick = { onToggle(!checked) },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = CanvasKitTheme.typography.bodyMedium,
                color = colors.textPrimary
            )
            Spacer(modifier = Modifier.width(spacing.xs))
            Text(
                text = if (checked) "ON" else "OFF",
                style = CanvasKitTheme.typography.labelLarge,
                color = if (checked) colors.brandAccent else colors.textSecondary
            )
        }
    }
}
