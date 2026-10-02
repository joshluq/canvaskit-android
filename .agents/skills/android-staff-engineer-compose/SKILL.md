---
name: android-staff-engineer-compose
description: >-
  Architects, reviews, and implements production-grade Jetpack Compose UI and design system components following Staff Android Engineer standards and the CanvasKit Atelier Design System architecture in AGENTS.md. Enforces tokenization, Slot APIs, zero DI lock-in, Compose compiler stability, pure motion physics, accessibility (WCAG AA), and mandatory multi-previews.
---

# Android Staff Engineer - Jetpack Compose & Design System Architecture

This skill embodies the technical leadership, architectural rigor, and system-level standards of a **Staff Android Engineer** specializing in **Jetpack Compose** and **Design System Architecture**, aligned strictly with the **CanvasKit ("Kit" Ecosystem) Architecture & Guidelines (`AGENTS.md`)**.

---

## 1. Core Architectural Pillars of CanvasKit

CanvasKit is the foundational **Design System** and visual backbone of the "Kit" modular ecosystem (`authKit`, `encryptionKit`, `analyticsKit`, `canvasKit`). It treats UI components as artisanal craft (*Atelier Design System*, inspired by Shopify Polaris, IBM Carbon, and Material 3 Expressive).

### 1.1 Zero Vendor Lock-In & Decoupled DI
- **No Framework Coupling:** CanvasKit core library components **MUST NEVER** depend on DI frameworks (such as Hilt, Dagger, or Koin) or application-level navigation frameworks at the public API level.
- **Provider & Strategy Patterns:** Expose flexible configuration and theming via Jetpack Compose `CompositionLocalProvider` (e.g., `LocalCanvasColors`, `LocalCanvasTypography`, `LocalCanvasMotion`) or strategy interfaces.

### 1.2 Strict Tokenization & Semantic Layer Abstraction
- **Zero Raw Styling Values:** Hex color literals (`#FFFFFF`), raw pixel/dp coordinates without grid mapping, or ad-hoc typography are prohibited in component code.
- **Semantic Separation:**
  - Raw tokens live in `es.joshluq.canvaskit.core.tokens` (`Color.kt`, `Type.kt`, `Shape.kt`, `Spacing.kt`, `Motion.kt`, `Opacity.kt`, `Stroke.kt`).
  - Components consume semantic theme accessors: `CanvasTheme.colors`, `CanvasTheme.typography`, `CanvasTheme.shapes`, `CanvasTheme.spacing`, `CanvasTheme.motion`.
- **Zero Material3 Leakage:** Never leak Material3 tokens (`LocalContentColor`, `MaterialTheme.*`) inside core components. All tokens originate from CanvasKit.

### 1.3 Composition over Configuration (Slot APIs)
- **Reject Monolithic Parameter Sprawl:** Avoid creating composables with dozens of rigid boolean flags.
- **Modular Slot APIs:** Utilize Composable slots (`content: @Composable () -> Unit`, `header`, `footer`, `leadingIcon`, `trailingIcon`) to empower consumers to compose rich layouts naturally.
- **First Optional Parameter Mandate:** Every composable emitting layout **must** accept `modifier: Modifier = Modifier` as its very first optional parameter and attach it to the root layout element.

### 1.4 State Hoisting & Defensive Visibility
- **Stateless Components:** Core components remain strictly stateless. All interactive states (expanded, selected, checked, query string) are hoisted to caller lambdas (`onValueChange`, `onClick`).
- **Minimal API Surface:** Keep internal algorithms, helper composables, and experimental modifiers marked as `internal`. Only expose the main composable, public defaults objects (e.g., `CanvasButtonDefaults`), and slot contracts.
- **Showcase State Isolation:** ViewModels within the `:showcase` module must protect UI states with explicit backing properties:
  ```kotlin
  private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
  val uiState: StateFlow<UiState> = _uiState.asStateFlow()
  ```

---

## 2. Compose Compiler Performance & Stability Invariants

A Staff Android Engineer guarantees 60/120 FPS by proactively designing against Compose compiler recomposition traps:

### 2.1 Stability Annotations
- Explicitly annotate component state containers, configuration models, and data holders with `@Immutable` or `@Stable`.
- Verify the Compose compiler marks all parameters as stable so composables can be safely skipped during recomposition.

### 2.2 Unstable Collections Mitigation
- Kotlin standard collection interfaces (`List`, `Set`, `Map`) are treated as unstable by the Compose Compiler.
- Prefer immutable collections (`ImmutableList` / `PersistentList` from `kotlinx.collections.immutable`) or wrap them in an `@Immutable` value class for public component APIs with frequently changing items.

### 2.3 Lambda Stability & Memoization
- Avoid passing unstable method references (e.g., `onClick = unstableClass::action`). Wrap them in lambdas: `{ unstableClass.action() }`.
- Memoize expensive calculations, formatters, and animation specs using `remember(key1, key2)`.

### 2.4 Defer State Reads to Phased Pipelines
- Defer rapidly mutating state reads (e.g., scroll offsets, drag progress) to layout or draw phases using lambda modifiers (`Modifier.offset { ... }`, `Modifier.graphicsLayer { ... }`) to avoid recomposing the entire sub-tree.

---

## 3. Pure Motion Architecture (No System Ripple)

Under Pillar 5 of [`AGENTS.md`](file:///c:/Users/josh_/AndroidStudioProjects/canvaskit-android/AGENTS.md), CanvasKit components reject generic Android circular ink ripples:

```kotlin
// Tactical micro-motion: Spring scale compression + background transition
val scale by animateFloatAsState(
    targetValue = if (isPressed) CanvasTheme.motion.pressedScale else 1.0f,
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMedium
    ),
    label = "canvas_tactile_press"
)
```

- **Artisanal Tactile Compression:** Interactive elements compress on press (`pressedScale = 0.97f`), providing immediate mechanical response (<16ms).
- **Tokenized Timing:** Transition durations (`short1 = 100ms`, `medium1 = 250ms`, `long1 = 500ms`) and easing curves (`standard`, `decelerate`, `accelerate`) derive strictly from `CanvasTheme.motion`.

---

## 4. Accessibility (A11y) & WCAG AA Invariants

No component is approved without meeting Section 5.4 standards:

1. **48.dp Minimum Touch Target:** Enforce `Modifier.minimumInteractiveComponentSize()` or semantics bounds extensions without bloating visual bounds.
2. **TalkBack Cohesion:**
   - Interactive elements must require or provide localized, descriptive `contentDescription`.
   - Decorative icons must have `contentDescription = null`.
   - Compound elements (cards, list rows) must declare `Modifier.semantics(mergeDescendants = true)`.
3. **Semantic Roles & State Descriptions:**
   - Declare `role = Role.Button`, `Role.Switch`, `Role.Checkbox`, `Role.Tab`, etc.
   - Dynamic states must be announced via `stateDescription` (e.g., "checked", "expanded").
4. **2.0x Dynamic Font Scale Resilience:**
   - All typography uses scale-independent pixels (`sp`).
   - Containers must not fix vertical heights (`wrapContentHeight()` or min/max boundaries). No text clipping or overlaps at `2.0x`.
5. **Contrast Compliance:**
   - Adhere strictly to WCAG AA: Minimum 4.5:1 for normal text, 3:1 for large text and functional icons across Light, Dark, and High-Contrast variants.
6. **Hardware & Switch Navigation:**
   - Visible focus indicators (`CanvasTheme.tokens.focusRing`) for keyboard or switch navigation.

---

## 5. Mandatory Multi-Preview Setup

Every public Composable component `Canvas[Name]` **MUST** have an accompanying preview file `Canvas[Name]Preview.kt` in the same package containing:
- **Light Mode** preview.
- **Dark Mode** preview (`uiMode = Configuration.UI_MODE_NIGHT_YES`).
- **Right-To-Left (RTL)** layout preview (`LocalLayoutDirection provides LayoutDirection.Rtl`).
- **Dynamic Font Scale (2.0x)** preview (`fontScale = 2.0f`).
- `PreviewParameterProvider` for multi-state matrices (Loading, Disabled, Selected, Error).

---

## 6. Staff Review Quality Gate (Pull Request Checklist)

Before approving or finalizing any CanvasKit component or foundational change:

- [ ] **Architecture:** Is the component decoupled from DI frameworks (zero Hilt/Koin)?
- [ ] **Taxonomy:** Does the component belong to the proper package (`core/tokens`, `components/`, `foundations/`)?
- [ ] **Tokenization:** Are all colors, typography, shapes, and spacing resolved from `CanvasTheme`?
- [ ] **Slot APIs:** Are customizable content blocks exposed as `@Composable () -> Unit` slots?
- [ ] **Modifiers:** Is `modifier: Modifier = Modifier` the first optional parameter and applied to the root?
- [ ] **Stability:** Are parameter classes annotated with `@Immutable`/`@Stable` and collections non-leaking?
- [ ] **Pure Motion:** Are system ripples avoided in favor of spring-based tactile press motion?
- [ ] **Accessibility (A11y):** Are 48dp touch targets, semantic roles, `mergeDescendants`, and 4.5:1 contrast verified?
- [ ] **Font Scaling:** Does the preview render cleanly at `2.0x` font scale without truncation or clipping?
- [ ] **Multi-Preview:** Does `Canvas[Name]Preview.kt` exist with Light, Dark, RTL, and 2.0x font previews?
- [ ] **Defensive Visibility:** Are internal helpers marked `internal` with complete KDoc on public APIs?
