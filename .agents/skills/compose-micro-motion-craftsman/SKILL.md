---
name: compose-micro-motion-craftsman
description: >-
  Specializes in implementing pure artisanal micro-animations and motion physics in CanvasKit components following AGENTS.md Section 3 (Pillar 5: Pure Motion - No Ripple). Enforces custom press states, spring physics, background fades, and tokenized transitions via CanvasTheme.motion.
---

# Compose Micro-Motion Craftsman (Pure Motion)

This skill governs the interaction physics and micro-animations of **CanvasKit** components. Under Section 3, Pillar 5 of [`AGENTS.md`](file:///c:/Users/josh_/AndroidStudioProjects/canvaskit-android/AGENTS.md), CanvasKit rejects generic Android system ripples in favor of **Pure Motion**: artisanal micro-interactions that feel tactile, immediate, and technically precise.

---

## 1. Principles of Pure Motion

1. **No System Ripple (`indication = null`):** Standard material ripple circles are replaced with subtle mechanical tactile feedback.
2. **Spring Physics & Micro-Scale:** Interactive elements react to touch with a subtle tactile compression (e.g., scaling down to `CanvasTheme.motion.pressedScale` ~ `0.97f`) backed by high-stiffness springs without bouncing excessive overshoot.
3. **Background & Surface Fades:** Depth and press highlights are communicated via tokenized surface opacity/color transitions rather than unbounded radial ink drops.
4. **Tokenized Timing:** All animation durations and easing curves must derive strictly from `CanvasTheme.motion` (`short1`, `short2`, `medium1`, etc.).

---

## 2. Standard Pure Motion Modifier Pattern

When creating interactive elements (buttons, cards, chips), build or apply pure-motion modifiers using `MutableInteractionSource` and `animateFloatAsState`:

```kotlin
@Composable
fun Modifier.canvasPressMotion(
    interactionSource: InteractionSource,
    pressedScale: Float = CanvasTheme.motion.pressedScale,
    enabled: Boolean = true
): Modifier {
    if (!enabled) return this

    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) pressedScale else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "canvas_press_scale"
    )

    return this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}
```

---

## 3. Motion Token Reference (`CanvasTheme.motion`)

- **Durations:**
  - `short1` (100ms): Micro-switches, instant tactile feedback.
  - `short2` (200ms): Button state changes, badge appearances.
  - `medium1` (250ms), `medium2` (400ms): Expandable panels, modal card transitions.
  - `long1` (500ms), `long2` (800ms): Screen-level orchestrations.
- **Easings:**
  - `standard` (`FastOutSlowInEasing`): Natural entry/exit transitions.
  - `decelerate` (`LinearOutSlowInEasing`): Incoming surfaces.
  - `accelerate` (`FastOutLinearInEasing`): Outgoing elements.
- **Scales:**
  - `pressedScale` (default `0.97f`): Consistent tactile depth for interactive targets.

---

## 4. Interaction Guidelines

- **Low-Latency Feedback:** Visual feedback on touch down must commence within <16ms (single frame).
- **Reduced Motion Support:** Respect accessibility settings for users who request reduced motion by disabling scale physics and providing subtle instant color switches.
