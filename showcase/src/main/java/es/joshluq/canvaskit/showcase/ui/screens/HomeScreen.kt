package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitButtonVariant
import es.joshluq.canvaskit.components.feedback.CanvasKitDialog
import es.joshluq.canvaskit.components.feedback.CanvasKitDialogContent
import es.joshluq.canvaskit.components.feedback.CanvasKitPopup
import es.joshluq.canvaskit.components.inputs.CanvasKitTextField
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * HomeScreen is the upgraded dashboard landing screen for the Design System catalog.
 * It presents a personalized, cohesive real-world workspace showcasing top bar dropdowns,
 * notifications, search-filter panels, and dashboard component cards.
 */
@Composable
fun HomeScreen(
    onNavigateToButtons: () -> Unit,
    onNavigateToTextFields: () -> Unit,
    onNavigateToDialogs: () -> Unit,
    onNavigateToPopups: () -> Unit,
    onNavigateToCards: () -> Unit,
    onNavigateToTopBar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    
    // States for interactive popups/dialogs
    var showLocationMenu by remember { mutableStateOf(false) }
    var showNotificationsMenu by remember { mutableStateOf(false) }
    var showRoadmapDialog by remember { mutableStateOf<String?>(null) }
    var showFilterPopup by remember { mutableStateOf(false) }

    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(spacing.md),
        verticalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        // 1. Dashboard Top Header Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Location / Ecosystem dropdown selector
            Box {
                Row(
                    modifier = Modifier
                        .clip(shapes.pill)
                        .clickable { showLocationMenu = true }
                        .padding(horizontal = spacing.sm, vertical = spacing.xs),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.xxs)
                ) {
                    Icon(imageVector = Icons.Default.Place, contentDescription = "Ubicación", tint = colors.brandPrimary, modifier = Modifier.size(20.dp))
                    Column {
                        Text(
                            text = "Ecosistema Kit",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "canvasKit DS",
                                style = CanvasKitTheme.typography.labelLarge,
                                color = colors.textPrimary
                            )
                            Spacer(modifier = Modifier.width(spacing.xxxs))
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = colors.textSecondary, modifier = Modifier.size(16.dp))
                        }
                    }
                }

                CanvasKitPopup(
                    expanded = showLocationMenu,
                    onDismissRequest = { showLocationMenu = false },
                    offset = DpOffset(0.dp, 48.dp)
                ) {
                    Column(modifier = Modifier.width(180.dp)) {
                        Text(
                            text = "Cambiar Biblioteca",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = spacing.xs)
                        )
                        val modules = listOf("authKit", "encryptionKit", "analyticsKit", "canvasKit (Activo)")
                        modules.forEach { mod ->
                            val isActive = mod.startsWith("canvasKit")
                            Text(
                                text = mod,
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = if (isActive) colors.brandAccent else colors.textPrimary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(enabled = !isActive) {
                                        showLocationMenu = false
                                        Toast.makeText(context, "Navegando a: $mod", Toast.LENGTH_SHORT).show()
                                    }
                                    .padding(vertical = spacing.xxs)
                            )
                        }
                    }
                }
            }

            // Right: Notification Bell + Monogram Avatar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.xs)
            ) {
                // Bell
                Box {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(colors.backgroundSecondary)
                            .clickable { showNotificationsMenu = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notificaciones", tint = colors.brandPrimary, modifier = Modifier.size(24.dp))
                        // Notification badge dot
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(colors.error)
                                .align(Alignment.TopEnd)
                                .graphicsLayer(translationX = -12f, translationY = 12f)
                        )
                    }

                    CanvasKitPopup(
                        expanded = showNotificationsMenu,
                        onDismissRequest = { showNotificationsMenu = false },
                        alignment = Alignment.TopEnd,
                        offset = DpOffset(0.dp, 48.dp)
                    ) {
                        Column(modifier = Modifier.width(220.dp)) {
                            Text(
                                text = "Novedades Atelier",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.textSecondary,
                                modifier = Modifier.padding(bottom = spacing.xs)
                            )
                            Text(
                                text = "• Nuevas transiciones añadidas a Dialogs.",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = colors.textPrimary,
                                modifier = Modifier.padding(vertical = spacing.xxs)
                            )
                            Text(
                                text = "• Navegación migrada a Navigation 3.",
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = colors.textPrimary,
                                modifier = Modifier.padding(vertical = spacing.xxs)
                            )
                        }
                    }
                }

                // Monogram Avatar
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(colors.brandAccent),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JL",
                        style = CanvasKitTheme.typography.labelLarge,
                        color = colors.backgroundPrimary
                    )
                }
            }
        }

        // 2. Custom Title Area
        Column {
            Text(
                text = "Find your component",
                style = CanvasKitTheme.typography.displayMedium,
                color = colors.textPrimary
            )
            Spacer(modifier = Modifier.height(spacing.xxxs))
            Text(
                text = "Workspace de diseño del ecosistema de bibliotecas Kit.",
                style = CanvasKitTheme.typography.bodyLarge,
                color = colors.textSecondary
            )
        }

        // 3. Search and Filter Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CanvasKitTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = "Buscar componentes, fichas...",
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = colors.textSecondary, modifier = Modifier.padding(start = spacing.xs).size(20.dp))
                },
                modifier = Modifier.weight(1f)
            )

            // Filter button matching reference screens
            Box {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(shapes.medium)
                        .background(colors.brandPrimary)
                        .clickable { showFilterPopup = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Filtros", tint = colors.backgroundPrimary, modifier = Modifier.size(24.dp))
                }

                CanvasKitPopup(
                    expanded = showFilterPopup,
                    onDismissRequest = { showFilterPopup = false },
                    alignment = Alignment.TopEnd,
                    offset = DpOffset(0.dp, 52.dp)
                ) {
                    Column(modifier = Modifier.width(180.dp)) {
                        Text(
                            text = "Filtros Rápidos",
                            style = CanvasKitTheme.typography.labelSmall,
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = spacing.xs)
                        )
                        val filters = listOf("Mostrar Todos", "Solo Disponibles", "Próximos Lanzamientos")
                        filters.forEach { filter ->
                            Text(
                                text = filter,
                                style = CanvasKitTheme.typography.bodyMedium,
                                color = colors.textPrimary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        showFilterPopup = false
                                        Toast.makeText(context, "Filtro: $filter", Toast.LENGTH_SHORT).show()
                                    }
                                    .padding(vertical = spacing.xxs)
                            )
                        }
                    }
                }
            }
        }

        // 4. Section: Available Components
        val activeComponents = listOf(
            Triple("Buttons", "Botones Primary, Secondary, Ghost e Icon con animaciones.", onNavigateToButtons),
            Triple("Text Fields", "Inputs con validaciones de errores y visuales.", onNavigateToTextFields),
            Triple("Dialogs", "Modales de alertas y contenidos personalizados.", onNavigateToDialogs),
            Triple("Popups & Menus", "Ventanas flotantes y tooltips anclados.", onNavigateToPopups),
            Triple("Cards & Containers", "Tarjetas modulares, bloques de contenidos y layouts.", onNavigateToCards),
            Triple("Top App Bar", "Cabeceras de aplicación con soporte de insets y acciones.", onNavigateToTopBar)
        ).filter {
            searchQuery.isBlank() || it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
        }

        if (activeComponents.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                Text(
                    text = "Componentes Disponibles",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.textPrimary
                )

                activeComponents.forEach { (name, description, onClick) ->
                    ComponentCard(
                        name = name,
                        description = description,
                        iconVector = when (name) {
                            "Buttons" -> Icons.Default.PlayArrow
                            "Text Fields" -> Icons.Default.Edit
                            "Dialogs" -> Icons.Default.Warning
                            "Cards & Containers" -> Icons.Default.List
                            "Top App Bar" -> Icons.Default.Home
                            else -> Icons.Default.Info
                        },
                        iconBg = colors.brandAccent.copy(alpha = 0.1f),
                        iconColor = colors.brandAccent,
                        onClick = onClick
                    )
                }
            }
        }

        // 5. Section: Planned Components
        val plannedComponents = listOf(
            Pair("Switches & Toggles", "Switches, casillas checkboxes y botones radio."),
            Pair("Banners & Alerts", "Notificaciones snackbars y banners inline."),
            Pair("Skeletons", "Marcadores de posición de cargas asíncronas.")
        ).filter {
            searchQuery.isBlank() || it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
        }

        if (plannedComponents.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
                Text(
                    text = "Futuros Lanzamientos (Roadmap)",
                    style = CanvasKitTheme.typography.headingMedium,
                    color = colors.textPrimary
                )

                plannedComponents.forEach { (name, description) ->
                    ComponentCard(
                        name = name,
                        description = description,
                        iconVector = when (name) {
                            "Switches & Toggles" -> Icons.Default.Check
                            "Banners & Alerts" -> Icons.Default.Notifications
                            "Skeletons" -> Icons.Default.Refresh
                            else -> Icons.Default.Info
                        },
                        iconBg = colors.borderSubtle,
                        iconColor = colors.textSecondary,
                        enabled = false,
                        onClick = { showRoadmapDialog = name }
                    )
                }
            }
        }
    }

    // Roadmap Dialogue confirmation
    showRoadmapDialog?.let { name ->
        CanvasKitDialog(
            onDismissRequest = { showRoadmapDialog = null }
        ) {
            CanvasKitDialogContent(
                title = {
                    Text(
                        text = name,
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                },
                content = {
                    Text(
                        text = "El componente '$name' se encuentra en fase de diseño de tokens y especificación arquitectónica. Estará disponible en futuras entregas del Atelier Design System.",
                        style = CanvasKitTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                },
                buttons = {
                    CanvasKitButton(
                        onClick = { showRoadmapDialog = null },
                        variant = CanvasKitButtonVariant.Ghost
                    ) {
                        Text(
                            text = "Entendido",
                            style = CanvasKitTheme.typography.labelLarge,
                            color = colors.brandAccent
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun ComponentCard(
    name: String,
    description: String,
    iconVector: ImageVector,
    iconBg: androidx.compose.ui.graphics.Color,
    iconColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val colors = CanvasKitTheme.colors
    val shapes = CanvasKitTheme.shapes
    val spacing = CanvasKitTheme.spacing

    val borderStrokeColor = if (enabled) colors.borderSubtle else colors.borderSubtle.copy(alpha = 0.5f)
    val cardAlpha = if (enabled) 1.0f else 0.6f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shapes.medium)
            .background(colors.backgroundSecondary)
            .border(width = 1.dp, color = borderStrokeColor, shape = shapes.medium)
            .clickable(onClick = onClick)
            .padding(spacing.md)
            .alpha(cardAlpha)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rounded Icon Box
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(shapes.medium)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = iconVector,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Description block
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.xs)
                ) {
                    Text(
                        text = name,
                        style = CanvasKitTheme.typography.headingMedium,
                        color = colors.textPrimary
                    )
                    if (!enabled) {
                         Box(
                            modifier = Modifier
                                .clip(shapes.pill)
                                .background(colors.borderSubtle)
                                .padding(horizontal = spacing.xs, vertical = spacing.xxxs)
                        ) {
                            Text(
                                text = "Próximamente",
                                style = CanvasKitTheme.typography.labelSmall,
                                color = colors.textSecondary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(spacing.xxxs))
                Text(
                    text = description,
                    style = CanvasKitTheme.typography.bodyMedium,
                    color = colors.textSecondary
                )
            }

            if (enabled) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = colors.brandAccent,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
