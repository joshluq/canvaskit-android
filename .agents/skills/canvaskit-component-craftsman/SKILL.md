---
name: canvaskit-component-craftsman
description: >-
  Architects, crafts, and maintains CanvasKit design system components following the Atelier Design System philosophy in AGENTS.md. Enforces strict tokenization (CanvasTheme), Slot APIs, defensive visibility, Compose compiler stability (@Immutable/@Stable), zero DI lock-in, and mandatory multi-preview generation (Light, Dark, RTL, Dynamic font scale).
---

# CanvasKit Component Craftsman (Atelier Design System)

This skill governs the architecture, development, and maintenance of UI components for **CanvasKit**, the foundational Design System of the "Kit" ecosystem. It enforces strict compliance with [`AGENTS.md`](file:///c:/Users/josh_/AndroidStudioProjects/canvaskit-android/AGENTS.md) and the **Atelier Design System** philosophy (inspired by Shopify Polaris, IBM Carbon, and Material 3 Expressive).

---

## 1. Core Architectural Mandates

### 1.1 Strict Tokenization (Zero Hardcoded Styling)
- **Zero Raw Primitives:** Never hardcode hex colors (`#FFFFFF`), raw dp dimensions (`16.dp` without token mapping), or arbitrary typography.
- **CanvasTheme System:** All styling must resolve from `CanvasTheme`:
  - `CanvasTheme.colors` (Semantic tokens: surface, primary, border, status, etc.)
  - `CanvasTheme.typography` (Heading, body, label styles using `sp`)
  - `CanvasTheme.shapes` (Corner radii tokens)
  - `CanvasTheme.spacing` (Grid scale: 4dp/8dp base increments)
  - `CanvasTheme.motion` (Spring curves, easing, transitions)
- **Zero Material3 Leakage:** Never reference Material3 `LocalContentColor` or `MaterialTheme` directly in core components unless authoring an explicit compatibility adapter.

### 1.2 Composition over Configuration (Slot APIs)
- **No Monolithic Parameter Bloat:** Avoid components with 30 boolean/configuration flags.
- **Compose Slots:** Use `@Composable () -> Unit` slot parameters (`header`, `footer`, `leadingIcon`, `trailingIcon`, `content`) for layout composability and nesting.
- **Root Modifier Contract:** Every Composable that produces layout MUST accept `modifier: Modifier = Modifier` as its very first optional parameter and apply it to the root layout node.

### 1.3 State Hoisting & Stability
- **Pure Statelessness:** Core components must be stateless. Hoist all interaction state (checked, selected, expanded, text value) to the consumer via lambda events (`onValueChange`, `onClick`).
- **Compose Compiler Stability:**
  - Annotate state data structures with `@Immutable` or `@Stable`.
  - Avoid unstable standard Kotlin collections (`List`, `Set`, `Map`) in Composable parameter signatures. Use `ImmutableList` (from `kotlinx.collections.immutable`) or wrap them in an `@Immutable` container.
  - Properly scope `remember` keys for animated values and derived state.

### 1.4 Defensive Visibility & API Surface
- **Internal by Default:** Internal helpers, layout math, private composables, and experimental utilities must be marked `internal`.
- **Minimal Public API:** Expose only the primary component composable, its public defaults/colors/sizes object (e.g., `CanvasButtonDefaults`), and public slot contracts.
- **Preserve Documentation:** Document all public composables and parameters with clear KDoc comments.

---

## 2. Mandatory Multi-Preview Rule

Every public Composable component `Canvas[Name]` **MUST** have an accompanying preview file `Canvas[Name]Preview.kt` in the exact same package.

The preview file must declare previews covering all 4 core scenarios:

```kotlin
@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun CanvasComponentPreviewLight() {
    CanvasTheme(darkTheme = false) {
        CanvasComponent(...)
    }
}

@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CanvasComponentPreviewDark() {
    CanvasTheme(darkTheme = true) {
        CanvasComponent(...)
    }
}

@Preview(name = "Right-To-Left (RTL)", locale = "ar")
@Composable
private fun CanvasComponentPreviewRtl() {
    CanvasTheme {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            CanvasComponent(...)
        }
    }
}

@Preview(name = "Dynamic Font Scale (2.0x)", fontScale = 2.0f)
@Composable
private fun CanvasComponentPreviewFontScale() {
    CanvasTheme {
        CanvasComponent(...)
    }
}
```

Use `PreviewParameterProvider` when a component supports multiple state variants (e.g., Default, Hovered, Focused, Disabled, Error, Loading).

---

## 3. Component Creation Workflow

When requested to create or refactor a CanvasKit component:
1. **Analyze Token Requirements:** Determine required semantic colors, typography styles, spacing, and shapes.
2. **Design Slot API:** Define the public signature starting with `modifier: Modifier = Modifier`, followed by semantic content slots and event callbacks.
3. **Implement Stateless Composable:** Apply `CanvasTheme` tokens, pure motion interaction (no ripple), and proper accessibility semantics.
4. **Generate Multi-Preview File:** Automatically create `Canvas[Name]Preview.kt` with Light, Dark, RTL, and 2.0x font scaling previews.
5. **Verify Compilation:** Ensure zero warnings, valid imports, and strict stability.
