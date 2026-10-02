package es.joshluq.canvaskit.core.haptics

import android.os.Build
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView

/**
 * Interface defining artisanal haptic patterns for the CanvasKit Atelier Design System.
 *
 * Implementations translate these semantic requests into platform-appropriate
 * physical vibrations, honoring system accessibility settings and battery constraints.
 */
@Immutable
interface CanvasKitHapticFeedback {
    /** Ultra-light tactile click for toggles, checkboxes, and segmented switches. */
    fun click()

    /** Micro mechanical tick for discrete sliders, pagers, and step transitions. */
    fun tick()

    /** Tactile confirmation pulse for successful completion or positive actions. */
    fun success()

    /** Distinct rejection buzz / double tap for errors or invalid inputs. */
    fun error()

    /** Magnetic snap feedback when a continuous gesture crosses an activation threshold. */
    fun gestureThreshold()
}

/**
 * Default implementation of [CanvasKitHapticFeedback] utilizing native [View.performHapticFeedback].
 *
 * It uses expressive modern constants available on Android 11-15 (API 30+) with graceful
 * fallbacks for earlier supported API levels (API 26+).
 */
@Immutable
class AndroidCanvasKitHapticFeedback(
    private val view: View
) : CanvasKitHapticFeedback {

    override fun click() {
        performHaptic(HapticFeedbackConstants.KEYBOARD_TAP)
    }

    override fun tick() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            performHaptic(HapticFeedbackConstants.SEGMENT_TICK)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            performHaptic(HapticFeedbackConstants.CLOCK_TICK)
        } else {
            performHaptic(HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }

    override fun success() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            performHaptic(HapticFeedbackConstants.CONFIRM)
        } else {
            performHaptic(HapticFeedbackConstants.VIRTUAL_KEY)
        }
    }

    override fun error() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            performHaptic(HapticFeedbackConstants.REJECT)
        } else {
            performHaptic(HapticFeedbackConstants.LONG_PRESS)
        }
    }

    override fun gestureThreshold() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            performHaptic(HapticFeedbackConstants.GESTURE_THRESHOLD_ACTIVATE)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            performHaptic(HapticFeedbackConstants.GESTURE_END)
        } else {
            performHaptic(HapticFeedbackConstants.VIRTUAL_KEY)
        }
    }

    private fun performHaptic(feedbackConstant: Int) {
        try {
            view.performHapticFeedback(feedbackConstant)
        } catch (_: Throwable) {
            // Defensive: ensure haptic failures never crash the UI
        }
    }
}

/**
 * No-op implementation of [CanvasKitHapticFeedback] used when haptics are disabled or in preview mode.
 */
@Immutable
object NoOpCanvasKitHapticFeedback : CanvasKitHapticFeedback {
    override fun click() {}
    override fun tick() {}
    override fun success() {}
    override fun error() {}
    override fun gestureThreshold() {}
}

/**
 * CompositionLocal providing access to [CanvasKitHapticFeedback].
 */
val LocalCanvasKitHapticFeedback = staticCompositionLocalOf<CanvasKitHapticFeedback> {
    NoOpCanvasKitHapticFeedback
}

/**
 * Remembers a platform [CanvasKitHapticFeedback] bound to the current Compose view hierarchy.
 */
@Composable
fun rememberCanvasKitHapticFeedback(enabled: Boolean = true): CanvasKitHapticFeedback {
    if (!enabled) return NoOpCanvasKitHapticFeedback
    val view = LocalView.current
    return remember(view) { AndroidCanvasKitHapticFeedback(view) }
}
