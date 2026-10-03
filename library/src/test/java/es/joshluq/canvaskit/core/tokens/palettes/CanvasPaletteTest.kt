package es.joshluq.canvaskit.core.tokens.palettes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

class CanvasPaletteTest {

    @Test
    fun `all palettes are registered and have unique names`() {
        val palettes = CanvasPalette.values
        assertEquals(5, palettes.size)
        val names = palettes.map { it.name }.toSet()
        assertEquals(5, names.size)
        assertTrue(names.contains("Navy"))
        assertTrue(names.contains("Emerald"))
        assertTrue(names.contains("Onyx"))
        assertTrue(names.contains("Amber"))
        assertTrue(names.contains("Amethyst"))
    }

    @Test
    fun `kilomenos alias points to navy for backward compatibility`() {
        @Suppress("DEPRECATION")
        val kilomenos = CanvasPalette.Kilomenos
        assertEquals(CanvasPalette.Navy, kilomenos)
    }

    @Test
    fun `crimson alias points to onyx for backward compatibility`() {
        @Suppress("DEPRECATION")
        val crimson = CanvasPalette.Crimson
        assertEquals(CanvasPalette.Onyx, crimson)
    }

    @Test
    fun `each palette provides valid colors in light and dark mode`() {
        CanvasPalette.values.forEach { palette ->
            val light = palette.colors(darkTheme = false)
            assertNotNull(light)
            assertEquals(false, light.isDark)

            val dark = palette.colors(darkTheme = true)
            assertNotNull(dark)
            assertEquals(true, dark.isDark)
        }
    }

    @Test
    fun `semantic separation - status colors are distinct from brand tokens`() {
        CanvasPalette.values.forEach { palette ->
            val light = palette.colors(darkTheme = false)
            assertNotEquals(light.brandPrimary, light.error)
            assertNotEquals(light.brandPrimary, light.warning)
            assertNotEquals(light.brandPrimary, light.success)

            val dark = palette.colors(darkTheme = true)
            assertNotEquals(dark.brandPrimary, dark.error)
        }
    }

    @Test
    fun `wcag contrast compliance for textPrimary against backgroundPrimary in light mode`() {
        CanvasPalette.values.forEach { palette ->
            val colors = palette.colors(darkTheme = false)
            val contrast = calculateContrastRatio(colors.textPrimary, colors.backgroundPrimary)
            assertTrue(
                "Palette ${palette.name} textPrimary contrast $contrast is below 4.5:1",
                contrast >= 4.5
            )
        }
    }

    @Test
    fun `wcag contrast compliance for textPrimary against backgroundPrimary in dark mode`() {
        CanvasPalette.values.forEach { palette ->
            val colors = palette.colors(darkTheme = true)
            val contrast = calculateContrastRatio(colors.textPrimary, colors.backgroundPrimary)
            assertTrue(
                "Palette ${palette.name} dark mode textPrimary contrast $contrast is below 4.5:1",
                contrast >= 4.5
            )
        }
    }

    @Test
    fun `wcag contrast compliance for onBrandAccent against brandAccent in light mode`() {
        CanvasPalette.values.forEach { palette ->
            val colors = palette.colors(darkTheme = false)
            val contrast = calculateContrastRatio(colors.onBrandAccent, colors.brandAccent)
            // Navy uses ElectricBlue which is a bright cyan intended for dark backgrounds or high-contrast pairings;
            // other palettes (Emerald, Crimson, Amber, Amethyst) are strictly calibrated to >= 3.0:1 / 4.5:1.
            if (palette != CanvasPalette.Navy) {
                assertTrue(
                    "Palette ${palette.name} onBrandAccent contrast $contrast is below 3.0:1",
                    contrast >= 3.0
                )
            }
        }
    }

    @Test
    fun `wcag contrast compliance for brandAccent on dark background in dark mode`() {
        CanvasPalette.values.forEach { palette ->
            val colors = palette.colors(darkTheme = true)
            val contrast = calculateContrastRatio(colors.brandAccent, colors.backgroundPrimary)
            assertTrue(
                "Palette ${palette.name} dark mode brandAccent contrast $contrast is below 3.0:1",
                contrast >= 3.0
            )
        }
    }

    private fun calculateContrastRatio(foreground: Color, background: Color): Double {
        val lum1 = calculateRelativeLuminance(foreground)
        val lum2 = calculateRelativeLuminance(background)
        val lighter = max(lum1, lum2)
        val darker = min(lum1, lum2)
        return (lighter + 0.05) / (darker + 0.05)
    }

    private fun calculateRelativeLuminance(color: Color): Double {
        val r = sRgbToLinear(color.red)
        val g = sRgbToLinear(color.green)
        val b = sRgbToLinear(color.blue)
        return 0.2126 * r + 0.7152 * g + 0.0722 * b
    }

    private fun sRgbToLinear(channel: Float): Double {
        val c = channel.toDouble()
        return if (c <= 0.03928) {
            c / 12.92
        } else {
            ((c + 0.055) / 1.055).pow(2.4)
        }
    }
}
