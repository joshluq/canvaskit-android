# 🎨 CanvasKit — Atelier Design System

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84.svg?style=flat-square&logo=android)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.3%2B-7F52FF.svg?style=flat-square&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-1.7%2B-4285F4.svg?style=flat-square&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Ecosystem](https://img.shields.io/badge/Ecosystem-Kit-FF5722.svg?style=flat-square)](https://github.com/joshluq)

**CanvasKit** is the foundational Design System and visual backbone of the **"Kit" ecosystem** (alongside `authKit`, `encryptionKit`, and `analyticsKit`). Inspired by high-end design languages like *Shopify Polaris*, *IBM Carbon*, and **Material 3 Expressive**, CanvasKit treats UI components as artisanal craft—combining extreme aesthetic refinement with technical rigor, pure motion physics, and native accessibility.

---

## 🏛️ Architecture & Taxonomy

CanvasKit is organized under atomic modular design principles to guarantee separation of concerns:

```
/library/src/main/java/es/joshluq/canvaskit/
│
├── core/
│   ├── tokens/         # Tokens: Color, Type, Shape, Spacing, Motion, Opacity, Stroke
│   └── haptics/        # Haptic Feedback Engine: CanvasKitHapticFeedback, native Android providers
│
├── components/         # Reusable interactive components
│   ├── buttons/        # Primary, Secondary, Ghost, Icon buttons
│   ├── cards/          # Content containers, selectable cards
│   ├── chips/          # Action, Filter, Input chips
│   ├── content/        # Avatars, Avatar groups, Notification badges, Badged boxes
│   ├── feedback/       # Linear & circular progress, Step indicators, Banners, Dialogs, Spinners, Skeletons
│   ├── inputs/         # OTP/PIN inputs, Search bars, Text fields, Switches, Checkboxes, Sliders, Radio buttons
│   ├── layout/         # Hairline dividers, Inset dividers, Accordions, Loading scaffolds
│   ├── lists/          # Swipe-to-dismiss action rows, Molecular list items
│   ├── menus/          # Expressive dropdowns, contextual menus
│   ├── navigation/     # Segmented controls, Pager dots/worms, Tab rows, Top & Bottom app bars
│   ├── sheets/         # Modal and persistent bottom sheets
│   └── text/           # Expressive typography wrappers and rich links
│
├── foundations/        # Theme Engine (CanvasKitTheme) and CompositionLocal providers
│
└── showcase/           # Interactive Catalog Application for sandboxing, testing, and previews
```

* **`:library`** (`canvaskit`): The reusable design system library containing tokens, modifiers, and custom UI components.
* **`:showcase`**: An isolated demonstration application used to catalog, sandbox, and test CanvasKit components in real-time.

---

## ⚡ The 5 Key Pillars

### 1. Strict Tokenization
No hardcoded styling values. All components derive styling from raw design tokens mapped to a semantic layer (e.g., `#FF2E2E` ➔ `Red-50` ➔ `CanvasKitTheme.colors.status.error` or `CanvasKitTheme.colors.borderSubtle`).

### 2. Composition over Configuration (Slot APIs)
CanvasKit leverages Jetpack Compose Slot APIs (`content: @Composable () -> Unit`, `header`, `footer`, `leadingIcon`, `trailingIcon`) to create flexible building blocks instead of bloated, monolithic composables with dozens of rigid boolean flags.

### 3. Native Accessibility (A11y by Default)
* **Touch Targets:** Guaranteed minimum `48.dp` interactive touch targets utilizing `defaultMinSize(48.dp)` or bounds extension.
* **Screen Readers:** Semantics merging (`mergeDescendants = true`) for compound cards/rows, explicit `Role` declarations (`Role.Tab`, `Role.Button`, `Role.Switch`), and dynamic `stateDescription` ("Option 1 of 3", "Page 2 of 5").
* **Accessibility Actions:** Gestural containers like `CanvasKitSwipeToDismissBox` expose custom accessibility actions so TalkBack and switch-navigation users can dismiss or archive without touch gestures.
* **Dynamic Scaling:** Resilient layouts without fixed vertical heights (`wrapContentHeight`), rendering beautifully at `2.0x` font scaling without text clipping.
* **WCAG AA Compliance:** Strict text-to-background contrast enforcement (minimum 4.5:1 for body text, 3:1 for large text/icons).

### 4. Pure Motion & Tactile Haptics (No System Ripple)
Replaces generic circular Android ripples with tactile, artisanal micro-interactions synchronized with an expressive haptic feedback engine:
* **Spring Dynamics:** Press compression on touch down (`CanvasKitTheme.motion.pressedScale` ~ `0.97f`), sliding pills, and connectors glide elastically using `spring(dampingRatio = LowBouncy, stiffness = MediumLow)`.
* **Immediate Feedback:** Immediate visual response (<16ms) and tokenized surface fades.
* **CanvasKitHaptics Engine:** Native decoupled haptic layer (`CanvasKitTheme.haptics`) mapped to modern Android 11–15 physical vibrations:
  * `click()`: Ultra-light mechanical click on toggles, checkboxes, and segmented switches.
  * `tick()`: Micro-tick for sliders, pagers, and step transitions.
  * `success()`: Tactile confirmation pulse on completion.
  * `error()`: Rejection buzz on validation failures (e.g. invalid OTP).
  * `gestureThreshold()`: Magnetic snap feedback when a swipe gesture crosses an activation threshold.
* **Accessibility Honored:** Fully respects system `HAPTIC_FEEDBACK_ENABLED` preferences and fails gracefully as a zero-overhead no-op when disabled.

### 5. Compose Compiler Stability & Zero Vendor Lock-In
* **Zero DI Lock-In:** Core library components have zero dependency on Hilt or Koin at the public API level, relying entirely on Compose `CompositionLocalProvider` (`LocalCanvasKitColors`, `LocalCanvasKitTypography`, etc.).
* **Compiler Stability:** All data models are annotated with `@Immutable` or `@Stable`. Public APIs avoid unstable parameters to eliminate unnecessary recompositions.

---

## 🧩 Complete Component Library

| Package | Components | Highlights |
|---|---|---|
| **`navigation`** | `CanvasKitSegmentedControl`<br>`CanvasKitPagerIndicator`<br>`CanvasKitTabRow` & `CanvasKitTab`<br>`CanvasKitTopBar`<br>`CanvasKitBottomBar` | Elastic sliding pill indicator, Worm & Dots page transitions, Underline & Pill tab indicators. |
| **`feedback`** | `CanvasKitStatusDot`<br>`CanvasKitLinearProgressBar`<br>`CanvasKitCircularProgressBar`<br>`CanvasKitStepIndicator`<br>`CanvasKitBanner`<br>`CanvasKitDialog`<br>`CanvasKitSkeleton` | Tactical radar LED status indicators with live pulse waves, spring-backed determinate & continuous sweep indeterminate modes, Horizontal wizard & Vertical timeline steppers. |
| **`inputs`** | `CanvasKitOtpField`<br>`CanvasKitSearchField`<br>`CanvasKitSwitch`<br>`CanvasKitCheckbox`<br>`CanvasKitRadioButton`<br>`CanvasKitSlider`<br>`TextField` | Single-field consolidated OTP (SMS auto-fill compatible, masked PIN support), tactile haptic switch toggle, pill search bar with animated clear & loading states. |
| **`content`** | `CanvasKitBadge`<br>`CanvasKitBadgedBox`<br>`CanvasKitAvatar`<br>`CanvasKitAvatarGroup` | Spring pop-in notification dots and 99+ counters, user initials extraction fallback, presence indicators with cutout rings, overlapping avatar stacks. |
| **`lists`** | `CanvasKitSwipeToDismissBox`<br>`CanvasKitListItem` | Spring rebound dismiss gestures, bidirectional archive/delete reveal, custom TalkBack accessibility actions. |
| **`layout`** | `CanvasKitDivider`<br>`CanvasKitVerticalDivider`<br>`CanvasKitAccordion`<br>`CanvasKitLoadingScaffold` | Tokenized hairline strokes, inset text alignment margins, collapsible animated groups. |
| **`buttons`** | `CanvasKitButton`<br>`CanvasKitIconButton` | Primary, Secondary, Ghost variants, tactile mechanical haptics, spring press scaling, slot-based content. |
| **`cards`** | `CanvasKitCard` | Atelier specular hairline highlight, Elevated, Outlined, and Flat containers with slot-based headers and footers. |
| **`chips`** | `CanvasKitChip` | Filter, Suggestion, and Assist chips with tactile haptics, active borders, and selection states. |

---

## 🎨 Multi-Palette & Theming System

CanvasKit features a **curated multi-palette engine** (`CanvasPalette`) that completely decouples visual identity from specific brand names, enabling any application in the ecosystem to switch identities in a single line of code with **100% certified WCAG AA contrast ratios**:

| Palette | Identity / Tone | Primary (`brandPrimary`) | Accent (`brandAccent`) | Best For |
|---|---|---|---|---|
| **`CanvasPalette.Navy`** *(Default)* | Deep Navy + Electric Blue | `#001E50` | `#00B0F0` | Kilomenos, Corporate, Banking |
| **`CanvasPalette.Emerald`** | Pine Green + Emerald Mint | `#064E3B` | `#059669` (Light) / `#34D399` (Dark) | Sustainability, Logistics, Health |
| **`CanvasPalette.Onyx`** | High-Contrast Monochrome (Uber-Style) | `#09090B` (Light) / `#FFFFFF` (Dark) | `#09090B` (Light: Black Buttons) / `#FFFFFF` (Dark: White Buttons) | Mobility, High-Tech, E-Commerce, Minimalist |
| **`CanvasPalette.Amber`** | Warm Bronze + Vivid Amber | `#78350F` | `#C2410C` (Light) / `#FBBF24` (Dark) | Delivery, Energy, Mobility |
| **`CanvasPalette.Amethyst`** | Royal Purple + Vivid Violet | `#3B0764` | `#7C3AED` (Light) / `#A78BFA` (Dark) | Media, Streaming, Web3, Loyalty |

*(Note: `CanvasPalette.Kilomenos` and `CanvasPalette.Crimson` are preserved as deprecated aliases for seamless backward compatibility).*

---

## 💎 Atelier Signature Identity ("Atelier Precision")

CanvasKit incorporates a distinctive visual and tactile signature inspired by high-end precision instruments:

1. **Specular Top Hairline (`Modifier.specularBorder`):** Outlined cards and containers feature a directional specular highlight that catches the top rim (subtle brand accent in Light Mode, moonlight white in Dark Mode), creating an architectural beveled appearance without blurry drop shadows.
2. **Precision Micro-Typography (`tnum` & `overline`):**
   * **Tabular Numerals (`tnum`):** Pre-configured in all display styles and headings. Metrics, prices, and counters never jitter or shift width when updating.
   * **`CanvasKitTheme.typography.overline`:** `10.sp`, `SemiBold`, `letterSpacing = 1.5.sp` in uppercase for kickers, category tags, and system labels.
   * **`CanvasKitTheme.typography.tabularNumber`:** `16.sp`, `SemiBold` with tabular alignment for data tables and financial amounts.
3. **Tactical Focus Halo (`Modifier.tacticalFocusHalo`):** Dual-layer focus indicator (1.5dp inner isolation gap + 2dp outer focus ring) conforming strictly to **WCAG 2.2**, guaranteeing $\ge 3:1$ contrast against any background surface.
4. **Tactical Radar Status Dot (`CanvasKitStatusDot`):** Dual-ring optical LED indicator with solid core and optional concentric radar pulse wave. Calibrated to prevent visual fatigue (static by default).
5. **Mechanical Haptics ("True Touch"):** Replaces the absence of system ripples with purposeful physical feedback via `CanvasKitTheme.haptics` (`click()`, `tick()`, `success()`, `error()`, `gestureThreshold()`), mapping to modern Android 11–15 physical vibrations while honoring accessibility preferences.

---

## 🖼️ Mandatory Multi-Preview Standard

Every public CanvasKit component is accompanied by a dedicated `ComponentNamePreview.kt` file ensuring complete visual validation across 4 mandatory preview scenarios:
1. **Light Mode** (default baseline)
2. **Dark Mode** (`uiMode = Configuration.UI_MODE_NIGHT_YES`)
3. **Right-To-Left / RTL** (e.g., Arabic locale preview)
4. **Dynamic Font Scale (2.0x)** (`fontScale = 2.0f`)

---

## 🛠️ Usage & Integration for Consuming Apps

### 1. Theme & Palette Configuration
Wrap your application's root in `CanvasKitTheme`, specifying your desired palette:

```kotlin
import es.joshluq.canvaskit.core.tokens.palettes.CanvasPalette
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme

setContent {
    CanvasKitTheme(
        darkTheme = isSystemInDarkTheme(),
        palette = CanvasPalette.Onyx // Or Navy, Emerald, Amber, Amethyst
    ) {
        AppNavigation()
    }
}
```

### 2. Containers with Specular Hairline
```kotlin
import es.joshluq.canvaskit.components.cards.CanvasKitCard
import es.joshluq.canvaskit.components.cards.CanvasKitCardVariant

CanvasKitCard(
    variant = CanvasKitCardVariant.Outlined,
    specularHighlight = true, // Enabled by default
    header = {
        Text(
            text = "METRICS OVERVIEW",
            style = CanvasKitTheme.typography.overline,
            color = CanvasKitTheme.colors.textSecondary
        )
    }
) {
    Text(
        text = "$28,450.00",
        style = CanvasKitTheme.typography.headingLarge,
        color = CanvasKitTheme.colors.textPrimary
    )
}
```

### 3. Precision Status Indicators (Status Dots)
```kotlin
import es.joshluq.canvaskit.components.feedback.CanvasKitStatusDot
import es.joshluq.canvaskit.components.feedback.CanvasKitStatusDotVariant

// Static tactical halo
CanvasKitStatusDot(
    variant = CanvasKitStatusDotVariant.Success,
    label = { Text("Systems Operational", style = CanvasKitTheme.typography.labelSmall) }
)

// Live active radar pulse
CanvasKitStatusDot(
    variant = CanvasKitStatusDotVariant.Brand,
    animatePulse = true,
    label = { Text("Synchronizing Node...", style = CanvasKitTheme.typography.labelSmall) }
)
```

### 4. Tactical Focus Halo for Custom Components
```kotlin
import es.joshluq.canvaskit.foundations.modifiers.tacticalFocusHalo

Box(
    modifier = Modifier
        .tacticalFocusHalo(
            interactionSource = interactionSource,
            shape = CanvasKitTheme.shapes.small
        )
        .clickable(interactionSource = interactionSource, indication = null) { ... }
)
```

### 5. Accessing Expressive Haptics Programmatically
```kotlin
val haptics = CanvasKitTheme.haptics

CanvasKitButton(
    text = "Confirm Transaction",
    onClick = {
        haptics.success()
        viewModel.onConfirm()
    }
)
```

---

## 🏗️ Building & Development

Ensure you have your environment configured, then use the following Gradle tasks:

| Command | Action |
|---|---|
| `./gradlew :canvaskit:assemble` | Compile and assemble the library release bundle |
| `./gradlew :canvaskit:compileDebugKotlin` | Compile Kotlin sources for the library |
| `./gradlew :showcase:assembleDebug` | Build the showcase development application |
| `./gradlew :showcase:compileDebugKotlin` | Verify showcase screens and navigation |

---

## ⚙️ Configuration Properties

The library coordinates are centrally managed in `gradle.properties` at the root of the project:

* `catalogVersion` : Coordinate for the version catalog (e.g., `es.joshluq.kit:catalog:1.5.0`).
* `libraryVersion` : Target version for the `:library` artifact (e.g., `1.0.0`).
* `repositoryUrl` : GitHub packages repository URL for publishing/fetching dependencies.

---

## 🤖 Developer & Agent Tooling

This repository includes specialized Antigravity / AI Pair Programming skills in `.agents/skills/`:
* **`canvaskit-component-craftsman`**: Automated component creation, Slot API compliance, and multi-preview generation.
* **`compose-accessibility-auditor`**: WCAG AA, 48dp touch target, and TalkBack semantics audits.
* **`compose-micro-motion-craftsman`**: Spring physics, tactile compression, and ripple elimination.
* **`android-staff-engineer-compose`**: Staff-level architectural reviews and Compose compiler stability gates.

For deep architectural patterns, API contracts, and guidelines, refer to:
👉 **[AGENTS.md](file:///c:/Users/josh_/AndroidStudioProjects/canvaskit-android/AGENTS.md)** — Core Architecture & Coding Guidelines.
