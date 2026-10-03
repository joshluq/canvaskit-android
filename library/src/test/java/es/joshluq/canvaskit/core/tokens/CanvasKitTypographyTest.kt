package es.joshluq.canvaskit.core.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CanvasKitTypographyTest {
    @Test
    fun `typography provides overline and tabularNumber styles with tnum enabled`() {
        val typography = CanvasKitTypography()

        assertNotNull(typography.overline)
        assertEquals("tnum", typography.overline.fontFeatureSettings)

        assertNotNull(typography.tabularNumber)
        assertEquals("tnum", typography.tabularNumber.fontFeatureSettings)

        // Headings must have tnum for stable metric scannability
        assertEquals("tnum", typography.displayLarge.fontFeatureSettings)
        assertEquals("tnum", typography.displayMedium.fontFeatureSettings)
        assertEquals("tnum", typography.headingLarge.fontFeatureSettings)
        assertEquals("tnum", typography.headingMedium.fontFeatureSettings)
    }
}
