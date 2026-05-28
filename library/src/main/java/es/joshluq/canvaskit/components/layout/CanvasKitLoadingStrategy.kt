package es.joshluq.canvaskit.components.layout

/**
 * Defines how the loading state should be displayed within a [CanvasKitLoadingScaffold].
 */
enum class CanvasKitLoadingStrategy {
    /**
     * Replaces the main content with the loading indicator. The top bar, bottom bar,
     * and other scaffold elements remain visible and interactive.
     */
    ReplaceContent,

    /**
     * Displays a full-screen overlay over the entire scaffold (including bars),
     * blocking all interactions with the background content while loading.
     */
    OverlayFullscreen
}
