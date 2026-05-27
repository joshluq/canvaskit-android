package es.joshluq.canvaskit.showcase.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant
import es.joshluq.canvaskit.components.navigation.CanvasKitBadge
import es.joshluq.canvaskit.components.navigation.CanvasKitBottomBar
import es.joshluq.canvaskit.components.navigation.CanvasKitBottomBarItem
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * BottomBarScreen showcases the custom CanvasKitBottomBar navigation component.
 * It provides control panels for dynamic badges, label visibility rules, border lines,
 * and renders a fully functional live bottom navigation bar at the screen's base.
 */
@Composable
fun BottomBarScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    // Playground state variables
    var selectedIndex by remember { mutableIntStateOf(0) }
    var alwaysShowLabel by remember { mutableStateOf(true) }
    var showTopBorder by remember { mutableStateOf(true) }
    var showBadges by remember { mutableStateOf(true) }
    var enableItems by remember { mutableStateOf(true) }
    var badgeCount by remember { mutableIntStateOf(5) }

    val destinations = listOf(
        Triple("Inicio", Icons.Default.Home, "Sección de inicio del panel dashboard."),
        Triple("Buscar", Icons.Default.Search, "Buscador global de componentes y fichas."),
        Triple("Avisos", Icons.Default.Notifications, "Centro de novedades e insets del Atelier."),
        Triple("Ajustes", Icons.Default.Settings, "Configuraciones avanzadas de la biblioteca.")
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Pinned Header
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Bottom App Bar",
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Navegación inferior con insets nativos y animaciones de selección.",
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

        // Scrollable spec content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            // Live status preview card
            SpecSectionCard(
                title = "Live Destination Preview",
                description = "Vista simulada de la pantalla destino según la selección del BottomBar."
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.backgroundSecondary)
                        .padding(spacing.lg),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val dest = destinations[selectedIndex]
                    Icon(
                        imageVector = dest.second,
                        contentDescription = null,
                        tint = colors.brandAccent,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(spacing.sm))
                    Text(
                        text = dest.first,
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(spacing.xxs))
                    Text(
                        text = dest.third,
                        style = CanvasKitTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                }
            }

            // Interactive Playground controls
            SpecSectionCard(
                title = "Playground Controls",
                description = "Modifica los atributos del CanvasKitBottomBar inferior en tiempo real."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.sm),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        PlaygroundToggleCard(
                            label = "Always Show Label",
                            checked = alwaysShowLabel,
                            onToggle = { alwaysShowLabel = it },
                            modifier = Modifier.weight(1f)
                        )
                        PlaygroundToggleCard(
                            label = "Show Top Border",
                            checked = showTopBorder,
                            onToggle = { showTopBorder = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm)
                    ) {
                        PlaygroundToggleCard(
                            label = "Enable All Items",
                            checked = enableItems,
                            onToggle = { enableItems = it },
                            modifier = Modifier.weight(1f)
                        )
                        PlaygroundToggleCard(
                            label = "Show Mock Badges",
                            checked = showBadges,
                            onToggle = { showBadges = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    if (showBadges) {
                        // Custom Interactive Badge Counter
                        CanvasKitCard(
                            variant = CanvasKitCardVariant.Outlined,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Badge Counter (Avisos)",
                                        style = CanvasKitTheme.typography.labelLarge,
                                        color = colors.textPrimary
                                    )
                                    Text(
                                        text = "Ajusta el número de notificaciones del badge.",
                                        style = CanvasKitTheme.typography.labelSmall,
                                        color = colors.textSecondary
                                    )
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                                ) {
                                    CanvasKitIconButton(
                                        onClick = { if (badgeCount > 1) badgeCount-- },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close, // using close as minus
                                            contentDescription = "Decrementar",
                                            tint = colors.brandPrimary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }

                                    Text(
                                        text = badgeCount.toString(),
                                        style = CanvasKitTheme.typography.headingMedium,
                                        color = colors.textPrimary,
                                        modifier = Modifier.padding(horizontal = spacing.xxs)
                                    )

                                    CanvasKitIconButton(
                                        onClick = { badgeCount++ },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Incrementar",
                                            tint = colors.brandPrimary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Specs information
            SpecSectionCard(
                title = "Design Tokens Reference",
                description = "Especificaciones de diseño del componente."
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(spacing.xs),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TokenRow(name = "Container Height", value = "80.dp")
                    TokenRow(name = "Active Pill Width", value = "64.dp")
                    TokenRow(name = "Active Pill Height", value = "32.dp")
                    TokenRow(name = "Active Pill Shape", value = "shapes.pill (CircleShape)")
                    TokenRow(name = "Selected Color", value = "colors.brandAccent")
                    TokenRow(name = "Inactive Color", value = "colors.textSecondary")
                    TokenRow(name = "Separator Border", value = "1.dp / colors.borderSubtle")
                }
            }
        }

        // Pinned Bottom Navigation Bar
        CanvasKitBottomBar(
            showTopBorder = showTopBorder
        ) {
            destinations.forEachIndexed { index, dest ->
                val isSelected = selectedIndex == index
                val itemBadge: (@Composable () -> Unit)? = if (showBadges) {
                    when (dest.first) {
                        "Avisos" -> {
                            {
                                CanvasKitBadge {
                                    Text(
                                        text = badgeCount.toString(),
                                        style = CanvasKitTheme.typography.labelSmall,
                                        color = colors.backgroundPrimary
                                    )
                                }
                            }
                        }
                        "Buscar" -> {
                            {
                                CanvasKitBadge() // Simple status dot
                            }
                        }
                        else -> null
                    }
                } else null

                CanvasKitBottomBarItem(
                    selected = isSelected,
                    onClick = { selectedIndex = index },
                    icon = { tint ->
                        Icon(
                            imageVector = dest.second,
                            contentDescription = dest.first,
                            tint = tint
                        )
                    },
                    label = { tint ->
                        Text(
                            text = dest.first,
                            style = CanvasKitTheme.typography.labelSmall,
                            color = tint
                        )
                    },
                    alwaysShowLabel = alwaysShowLabel,
                    badge = itemBadge,
                    enabled = enableItems
                )
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

@Composable
private fun TokenRow(name: String, value: String) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = CanvasKitTheme.typography.bodyMedium,
            color = colors.textSecondary
        )
        Text(
            text = value,
            style = CanvasKitTheme.typography.labelLarge,
            color = colors.textPrimary
        )
    }
}
