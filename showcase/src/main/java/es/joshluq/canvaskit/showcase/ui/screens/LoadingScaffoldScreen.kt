package es.joshluq.canvaskit.showcase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import es.joshluq.canvaskit.components.buttons.CanvasKitButton
import es.joshluq.canvaskit.components.buttons.CanvasKitIconButton
import es.joshluq.canvaskit.components.layout.CanvasKitLoadingScaffold
import es.joshluq.canvaskit.components.layout.CanvasKitLoadingStrategy
import es.joshluq.canvaskit.components.navigation.CanvasKitTopBar
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import kotlinx.coroutines.delay

@Composable
fun LoadingScaffoldScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = CanvasKitTheme.colors
    val spacing = CanvasKitTheme.spacing
    val typography = CanvasKitTheme.typography

    var isLoading by remember { mutableStateOf(false) }
    var strategy by remember { mutableStateOf(CanvasKitLoadingStrategy.ReplaceContent) }

    // Auto-stop loading after 3 seconds for demo purposes
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(3000)
            isLoading = false
        }
    }

    CanvasKitLoadingScaffold(
        isLoading = isLoading,
        loadingStrategy = strategy,
        modifier = modifier.fillMaxSize(),
        topBar = {
            CanvasKitTopBar(
                title = {
                    Column {
                        Text(
                            text = "Loading Scaffold",
                            style = typography.headingMedium,
                            color = colors.textPrimary
                        )
                        Text(
                            text = "Contenedor de alto nivel para pantallas.",
                            style = typography.labelSmall,
                            color = colors.textSecondary
                        )
                    }
                },
                navigationIcon = {
                    CanvasKitIconButton(
                        onClick = onBack,
                        contentDescription = "Atrás"
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = colors.brandPrimary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.md)
        ) {
            SpecSectionCard(
                title = "Estrategias de Carga",
                description = "Prueba las diferentes estrategias interceptando todo el contenido o usando un overlay."
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(spacing.sm)
                ) {
                    CanvasKitButton(
                        onClick = { 
                            strategy = CanvasKitLoadingStrategy.ReplaceContent
                            isLoading = true 
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cargar con ReplaceContent", style = typography.labelLarge)
                    }

                    CanvasKitButton(
                        onClick = { 
                            strategy = CanvasKitLoadingStrategy.OverlayFullscreen
                            isLoading = true 
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cargar con OverlayFullscreen", style = typography.labelLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.lg))
            Text(
                text = "Este es un contenido dummy. Durante 'ReplaceContent', este texto y las tarjetas desaparecerán. Durante 'Overlay', permanecerán de fondo.",
                style = typography.labelLarge,
                color = colors.textSecondary
            )
        }
    }
}
