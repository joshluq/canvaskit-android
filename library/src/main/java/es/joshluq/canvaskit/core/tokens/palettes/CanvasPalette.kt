package es.joshluq.canvaskit.core.tokens.palettes

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import es.joshluq.canvaskit.core.tokens.CanvasKitColors
import es.joshluq.canvaskit.core.tokens.darkCanvasKitColors
import es.joshluq.canvaskit.core.tokens.lightCanvasKitColors

/**
 * Representation of a curated chromatic palette for CanvasKit.
 *
 * Each palette defines a cohesive set of primary and accent brand colors paired with
 * accessible semantic states in both Light and Dark modes conforming to WCAG AA contrast standards.
 */
@Immutable
sealed interface CanvasPalette {
    /**
     * Unique display name of the palette.
     */
    val name: String

    /**
     * Resolves the full [CanvasKitColors] semantic scheme for this palette.
     *
     * @param darkTheme Whether to resolve dark mode colors.
     */
    fun colors(darkTheme: Boolean): CanvasKitColors

    /**
     * Navy / Blue palette (Default).
     *
     * Deep Navy foundation paired with Electric Blue accent.
     * Used as the foundational default across the Kit ecosystem.
     */
    @Immutable
    data object Navy : CanvasPalette {
        override val name: String = "Navy"

        override fun colors(darkTheme: Boolean): CanvasKitColors = if (darkTheme) darkCanvasKitColors() else lightCanvasKitColors()
    }

    /**
     * Emerald / Green palette.
     *
     * Deep Pine Green foundation paired with Emerald Green accent.
     * Formulated for fintech, logistics, sustainability, and productivity experiences.
     * Calibrated to distinguish between brand identity and operational success states.
     */
    @Immutable
    data object Emerald : CanvasPalette {
        override val name: String = "Emerald"

        private val PinePrimary = Color(0xFF064E3B)
        private val EmeraldAccentLight = Color(0xFF059669)
        private val EmeraldAccentDark = Color(0xFF34D399)
        private val OnEmeraldAccentDark = Color(0xFF064E3B)

        override fun colors(darkTheme: Boolean): CanvasKitColors =
            if (darkTheme) {
                darkCanvasKitColors(
                    brandPrimary = Color.White,
                    brandAccent = EmeraldAccentDark,
                    onBrandAccent = OnEmeraldAccentDark,
                )
            } else {
                lightCanvasKitColors(
                    brandPrimary = PinePrimary,
                    brandAccent = EmeraldAccentLight,
                    textPrimary = PinePrimary,
                    onBrandAccent = Color.White,
                )
            }
    }

    /**
     * Onyx / Monochrome high-contrast palette.
     *
     * Carbon Black primary foundation paired with high-contrast monochrome accents.
     * Inspired by Uber Base UI, Apple, and minimalist engineering aesthetics.
     * In Light Mode, primary actions and buttons render in pure carbon black with crisp white text.
     * In Dark Mode, primary actions invert to pure white with crisp black text for maximum affordance.
     */
    @Immutable
    data object Onyx : CanvasPalette {
        override val name: String = "Onyx"

        private val CarbonBlack = Color(0xFF09090B)
        private val PureWhite = Color(0xFFFFFFFF)

        override fun colors(darkTheme: Boolean): CanvasKitColors =
            if (darkTheme) {
                darkCanvasKitColors(
                    brandPrimary = PureWhite,
                    brandAccent = PureWhite,
                    onBrandAccent = CarbonBlack,
                    textPrimary = PureWhite,
                )
            } else {
                lightCanvasKitColors(
                    brandPrimary = CarbonBlack,
                    brandAccent = CarbonBlack,
                    textPrimary = CarbonBlack,
                    onBrandAccent = PureWhite,
                )
            }
    }

    /**
     * Amber / Orange palette.
     *
     * Deep Bronze foundation paired with Vivid Amber accent.
     * Formulated for energy, mobility, delivery, and creative experiences.
     * Calibrated to maintain clear visual separation from warning states.
     */
    @Immutable
    data object Amber : CanvasPalette {
        override val name: String = "Amber"

        private val BronzePrimary = Color(0xFF78350F)
        private val AmberAccentLight = Color(0xFFC2410C)
        private val AmberAccentDark = Color(0xFFFBBF24)
        private val OnAmberAccentDark = Color(0xFF451A03)

        override fun colors(darkTheme: Boolean): CanvasKitColors =
            if (darkTheme) {
                darkCanvasKitColors(
                    brandPrimary = Color.White,
                    brandAccent = AmberAccentDark,
                    onBrandAccent = OnAmberAccentDark,
                )
            } else {
                lightCanvasKitColors(
                    brandPrimary = BronzePrimary,
                    brandAccent = AmberAccentLight,
                    textPrimary = BronzePrimary,
                    onBrandAccent = Color.White,
                )
            }
    }

    /**
     * Amethyst / Purple palette.
     *
     * Deep Royal Purple foundation paired with Vivid Violet accent.
     * Formulated for media, streaming, rewards, and modern fintech experiences.
     */
    @Immutable
    data object Amethyst : CanvasPalette {
        override val name: String = "Amethyst"

        private val RoyalPurplePrimary = Color(0xFF3B0764)
        private val VioletAccentLight = Color(0xFF7C3AED)
        private val VioletAccentDark = Color(0xFFA78BFA)
        private val OnVioletAccentDark = Color(0xFF2E1065)

        override fun colors(darkTheme: Boolean): CanvasKitColors =
            if (darkTheme) {
                darkCanvasKitColors(
                    brandPrimary = Color.White,
                    brandAccent = VioletAccentDark,
                    onBrandAccent = OnVioletAccentDark,
                )
            } else {
                lightCanvasKitColors(
                    brandPrimary = RoyalPurplePrimary,
                    brandAccent = VioletAccentLight,
                    textPrimary = RoyalPurplePrimary,
                    onBrandAccent = Color.White,
                )
            }
    }

    companion object {
        /**
         * List of all curated palettes available in CanvasKit.
         */
        val values: List<CanvasPalette> =
            listOf(
                Navy,
                Emerald,
                Onyx,
                Amber,
                Amethyst,
            )

        /**
         * Backward-compatible alias for the initial Navy palette.
         */
        @Deprecated(
            message = "Use CanvasPalette.Navy instead to decouple from specific application brand names.",
            replaceWith = ReplaceWith("CanvasPalette.Navy"),
        )
        val Kilomenos: CanvasPalette = Navy

        /**
         * Backward-compatible alias for the Crimson palette superseded by Onyx.
         */
        @Deprecated(
            message = "Crimson has been superseded by Onyx (high-contrast monochrome).",
            replaceWith = ReplaceWith("CanvasPalette.Onyx"),
        )
        val Crimson: CanvasPalette = Onyx
    }
}

/**
 * CompositionLocal key for providing and accessing the active [CanvasPalette].
 */
val LocalCanvasPalette =
    staticCompositionLocalOf<CanvasPalette> {
        CanvasPalette.Navy
    }
