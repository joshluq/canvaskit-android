package es.joshluq.canvaskit.components.feedback

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CanvasKitStatusDotTest {

    @Test
    fun `status dot variants coverage`() {
        val variants = CanvasKitStatusDotVariant.entries
        assertEquals(5, variants.size)
        assertNotNull(CanvasKitStatusDotVariant.Success)
        assertNotNull(CanvasKitStatusDotVariant.Error)
        assertNotNull(CanvasKitStatusDotVariant.Warning)
        assertNotNull(CanvasKitStatusDotVariant.Brand)
        assertNotNull(CanvasKitStatusDotVariant.Neutral)
    }
}
