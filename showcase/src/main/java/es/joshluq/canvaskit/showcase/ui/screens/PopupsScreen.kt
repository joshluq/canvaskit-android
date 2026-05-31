package es.joshluq.canvaskit.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.menus.CanvasKitDropdownMenu
import es.joshluq.canvaskit.components.menus.CanvasKitDropdownMenuItem
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

/**
 * PopupsScreen showcases the new Expressive Dropdown Menus.
 */
@Composable
fun PopupsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showMenuPopup by remember { mutableStateOf(false) }

    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundSecondary)
    ) {
        CanvasKitTopBar(
            title = {
                Column {
                    Text(
                        text = "Expressive Menus",
                        style = CanvasKitTheme.typography.headingLarge,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "Refined floating modules following M3 Expressive.",
                        style = CanvasKitTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                }
            },
            navigationIcon = {
                CanvasKitIconButton(
                    onClick = onBack,
                    contentDescription = "Back"
                ) { contentColor ->
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
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
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            
            // Introduction Section
            Column(modifier = Modifier.padding(horizontal = spacing.xs)) {
                Text(
                    text = "Dropdown\nEvolution",
                    style = CanvasKitTheme.typography.displayMedium,
                    color = colors.textPrimary
                )
                Spacer(modifier = Modifier.height(spacing.sm))
                Text(
                    text = "The new menu system combines artisanal rounding with high-density interactive slots.",
                    style = CanvasKitTheme.typography.bodyLarge,
                    color = colors.textSecondary
                )
            }

            // Section: Expressive Menu
            SpecSectionCard(
                title = "Dropdown Precision",
                description = "Expressive rounding and semantic slots."
            ) {
                Box {
                    CanvasKitIconButton(
                        onClick = { showMenuPopup = true },
                        backgroundColor = colors.backgroundSecondary,
                        contentDescription = "Menu"
                    ) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = colors.brandPrimary)
                    }

                    CanvasKitDropdownMenu(
                        expanded = showMenuPopup,
                        onDismissRequest = { showMenuPopup = false },
                        offset = DpOffset(0.dp, 8.dp)
                    ) {
                        CanvasKitDropdownMenuItem(
                            text = "Profile Settings",
                            onClick = { 
                                showMenuPopup = false
                                Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show()
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Person, contentDescription = null)
                            }
                        )
                        CanvasKitDropdownMenuItem(
                            text = "Share Module",
                            onClick = { 
                                showMenuPopup = false
                                Toast.makeText(context, "Share clicked", Toast.LENGTH_SHORT).show()
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Share, contentDescription = null)
                            }
                        )
                        CanvasKitDropdownMenuItem(
                            text = "System Config",
                            onClick = { 
                                showMenuPopup = false
                                Toast.makeText(context, "Settings clicked", Toast.LENGTH_SHORT).show()
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Settings, contentDescription = null)
                            },
                            trailingContent = {
                                Text(
                                    text = "v2.0",
                                    style = CanvasKitTheme.typography.labelSmall,
                                    color = colors.brandAccent
                                )
                            }
                        )
                        CanvasKitDropdownMenuItem(
                            text = "Notifications",
                            onClick = { },
                            leadingIcon = {
                                Icon(Icons.Default.Notifications, contentDescription = null)
                            },
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}
