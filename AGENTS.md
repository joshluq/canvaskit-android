# AGENTS.md — Core Project Context & Architecture Guidelines

You are an expert Android Software Engineer and Architect. This document serves as your absolute source of truth regarding the project's architecture, philosophy, and development standards. Read and adhere to these guidelines before suggesting any code modifications, refactors, or new implementations.

---

## 1. Project Overview & Ecosystem
This project is part of a modularized suite of libraries known as the **"Kit" ecosystem**. The ecosystem is designed to provide decoupled, production-ready, and highly scalable solutions for Android applications.

### Current Library Suite:
*   `authKit`: Authentication flows, OAuth, session management, and credential providers.
*   `encryptionKit`: Security, crypto utilities, biometric encryption, and secure storage wrapper.
*   `analyticsKit`: Telemetry, unified logging interfaces, event tracking, and performance monitoring.
*   `canvasKit` *(This Library)*: The foundational **Design System** and visual backbone of the ecosystem. It provides premium, elegant, accessible, and modular UI components.

---

## 2. Technical Stack & Target Environment
*   **Language:** Kotlin 2.3+ (Fully utilizing the K2 Compiler, type-safe builders, and idiomatic Kotlin features).
*   **UI Framework:** Jetpack Compose (100% declarative UI, zero legacy XML Views).
*   **Architecture Pattern:** Unidirectional Data Flow (UDF), Clean Architecture principles within modules, and strict separation of concerns.
*   **Dependency Injection:** Decoupled. Prefer the **Provider Pattern** or **Strategy Pattern** to expose components, avoiding tight coupling or vendor lock-in to specific DI frameworks (e.g. Hilt, Koin) at the library's public API level.
*   **Minimum SDK:** 26 (Android 8.0)
*   **Target SDK:** 35 (Android 15)

---

## 3. Design System Philosophy (Atelier Design System)
`canvasKit` is inspired by high-end design systems like *Shopify Polaris*, *IBM Carbon*, and *Material 3*. It treats UI components as artisanal craft—combining extreme aesthetic refinement with technical rigor.

### Key Pillars:
1.  **Strict Tokenization (Design Tokens):** Never hardcode styling values. Values must originate from design tokens.
2.  **Semantic Layer Abstraction:** We separate the raw design values (e.g., `#FF2E2E` or `Red-50`) from their semantic purpose (e.g., `theme.color.status.error` or `theme.color.border.destructive`).
3.  **Composition over Configuration:** Do not write monolithic components with 30 optional parameters. Build modular building blocks using Jetpack Compose Slot APIs (`content: @Composable () -> Unit`) to allow consumers to nest components naturally.
4.  **Accessibility by Default:** A component is not complete unless it is accessible to everyone. Contrast, touch targets, screen readers, and focus traversal must be handled natively.

---

## 4. Component Taxonomy & Module Structure
The library components are grouped in a clear taxonomic structure following atomic design and modularity rules. New components or features must fit into the established structure:

```
/library/src/main/java/es/joshluq/canvaskit/
│
├── core/
│   └── tokens/         # Core foundations: colors, typography, shapes, spacing, and animations.
│       ├── Color.kt    # Base palette and Semantic Color Schemes.
│       ├── Type.kt     # Typography systems and custom FontFamilies.
│       ├── Shape.kt    # Shape/Corner-radius tokens.
│       ├── Spacing.kt  # Grid scale (4dp/8dp base increments).
│       └── Motion.kt   # Easing curves and transition durations.
│
├── components/         # Reusable interactive elements.
│   ├── buttons/        # Atomic: Primary, Secondary, Ghost, Icon buttons.
│   ├── cards/          # Molecular: Content containers, selectable cards.
│   ├── inputs/         # Molecular: Text fields, switches, checkboxes.
│   └── feedback/       # Molecular: Banners, snackbars, skeletons.
│
├── foundations/        # Lower-level layout building blocks and system modifiers.
│   ├── layout/         # Base layout constraints, grid utilities, and dividers.
│   ├── modifiers/      # Custom modifiers (e.g., bounce effects, custom focus borders).
│   └── theme/          # Custom CanvasTheme composition local providers.
│
└── showcase/           # Showcase/Catalog Application (separate module for documentation and previews).
```

---

## 5. Coding Standards & Architecture Rules

### 5.1 Design Token Usage & Theming
*   **CanvasTheme:** Do not reference Material3 `LocalContentColor` or `MaterialTheme` directly inside core components unless explicitly building an adapter. Create and reference `CanvasTheme.colors`, `CanvasTheme.typography`, `CanvasTheme.shapes`, `CanvasTheme.spacing`, and `CanvasTheme.motion`.
*   **Composition Locals:** Theme values must be supplied via `CompositionLocalProvider` (e.g., `LocalCanvasColors`, `LocalCanvasTypography`).
*   **Theme Switchability:** Ensure all custom components automatically react to light/dark mode and brand theme overrides.

### 5.2 Component Contract & API Guidelines
*   **Modifier Parameter:** Every Composable function that produces a layout **must** accept a `modifier: Modifier = Modifier` as its very first optional parameter and apply it to the root layout element.
*   **Slot APIs:** Use Composable slots for flexible content blocks.
    ```kotlin
    @Composable
    fun CanvasCard(
        modifier: Modifier = Modifier,
        onClick: (() -> Unit)? = null,
        header: (@Composable () -> Unit)? = null,
        footer: (@Composable () -> Unit)? = null,
        content: @Composable () -> Unit
    )
    ```
*   **State Hoisting:** Components must be stateless. Hoist all UI control states (e.g., expanded, selected, input text) up to the consumer, exposing state change lambda events (e.g., `onValueChange: (String) -> Unit`).
*   **Explicit Backing Properties:** For view models or state holding objects within the showcase application, protect states with backing properties:
    ```kotlin
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    ```

### 5.3 Jetpack Compose Performance & Stability
*   **Stability Annotations:** Ensure classes used as Composable parameters are recognized as stable by the Compose compiler. Use `@Stable` or `@Immutable` on data structures to avoid unnecessary recompositions.
*   **Avoid Collection Parameters:** Kotlin standard collections (`List`, `Set`, `Map`) are treated as unstable by the Compose Compiler. Use immutable collections (e.g., `ImmutableList` from kotlinx-collections-immutable) or wrap them in a `@Immutable` helper class if they are frequently changed.
*   **Lambda References:** Avoid passing method references like `onClick = viewModel::onClicked` directly if the class instance is not stable. Prefer a standard lambda wrapper `{ viewModel.onClicked() }`.
*   **Remember Keys:** Properly scope `remember` keys in custom calculations and animations to avoid memory leaks and stale state.

### 5.4 Accessibility (A11y)
*   **Touch Targets:** Interactive elements must have a minimum touch target size of `48.dp` (using `Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)` or wrapping the interaction region appropriately). Do not bloat visual bounds; use padding, bounds extension, or Compose's `minimumInteractiveComponentSize()` helper to expand interactive areas.
*   **Content Descriptions:** Never leave `contentDescription` as null on interactive elements. Provide clear, localized string parameters, or accept a non-nullable description. Static/decorational icons must have `contentDescription = null` so screen readers ignore them.
*   **Semantics Merging:** Use `Modifier.semantics(mergeDescendants = true)` for compound elements (e.g., list items, cards) so screen readers present them as a single cohesive announcement rather than multiple fragmented pieces.
*   **Custom Semantics, Roles & States:**
    - Always declare the semantic `Role` (e.g., `Role.Button`, `Role.Checkbox`, `Role.Switch`, `Role.Tab`) for custom interactive components.
    - Explicitly state component conditions using custom `stateDescription` (e.g., announcing "checked" vs "unchecked", "expanded" vs "collapsed") for dynamic changes.
    - Use `LiveRegionMode.Polite` or `LiveRegionMode.Assertive` via semantics for feedback components (e.g., snackbars, banners, error prompts) to notify screen readers immediately of screen changes.
*   **Typography & Font Scaling:**
    - Use scale-independent pixels (`sp`) for all typography definitions.
    - Avoid hardcoding vertical heights on text containers (use `wrapContentHeight()` or min/max constraint boundaries). Layouts must scale elegantly without text clipping, truncation, or overlaps at `2.0x` font scale.
*   **Color Contrast Standards:**
    - All color combinations must strictly adhere to WCAG AA guidelines (minimum contrast ratio of 4.5:1 for normal text and 3:1 for large text/icons).
    - Ensure contrast remains compliant across Light, Dark, and High-Contrast brand variations.
*   **Focus & Hardware Navigation:**
    - Provide highly visible and visually distinct focus indicators (a focus ring or focus halo token) for physical keyboard or switch-controlled navigation.
    - Control custom focus traversal orders using `FocusRequester` or `focusProperties` when standard layout rendering alters the logical hierarchy.
*   **A11y Testing & Validation:**
    - Use Compose testing APIs to write semantic tests (e.g., checking assertions such as `assertHasClickAction()`, `assertIsOn()`, `assertContentDescriptionContains()`).
    - Test interactive components with Google's Accessibility Scanner and Compose Layout Inspector.

### 5.5 Previews & Showcase App
*   **Multi-Preview Setup:** Every public Composable must have a corresponding preview file containing:
    *   Light Mode preview.
    *   Dark Mode preview.
    *   RTL (Right-To-Left) layout preview.
    *   Dynamic font scale preview (e.g., 1.5x / 2.0x font scaling).
*   **Preview Parameter Providers:** Use `PreviewParameterProvider` to inject mock model data and check rendering of different states (e.g., Loading, Error, Disabled, Empty) in Compose Previews.

---

## 6. Prompting Rules & Agent Behaviors

*   **No Vendor Lock-In:** Keep core UI components decoupled from external styling libraries or strict DI container annotations.
*   **Defensive Visibility:** Keep helper utilities, base classes, and experimental components marked as `internal` or annotate them with custom API visibility markers (e.g. `@RequiresOptIn`). Only expose the minimal public API surface.
*   **Explain the "Why" First:** When proposing refactors, bug fixes, or new components, you must present the architectural and design system context (e.g., how this affects recomposition, accessibility, theme customization) before presenting code snippets.
*   **Preserve Documentation & Comments:** Maintain all existing comments, KDoc documentation, and license headers. Ensure new public components have clear, descriptive KDoc comments explaining their purpose, parameters, and design tokens.
*   **Verify Compilation & Layouts:** When building components, ensure they compile cleanly without warnings, and check preview rendering layout sizes to avoid layout breaks or container overflows.
