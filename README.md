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
│   └── tokens/         # Tokens: Color, Type, Shape, Spacing, Motion, Opacity, Stroke
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

### 4. Pure Motion (No System Ripple)
Replaces generic circular Android ripples with tactile, artisanal micro-interactions:
* Spring-backed scale compression on touch down (`CanvasKitTheme.motion.pressedScale` ~ `0.97f`).
* Immediate visual feedback (<16ms) and tokenized surface fades.
* Active indicators, pills, and connectors glide elastically using `spring(dampingRatio = LowBouncy, stiffness = MediumLow)`.
* Durations and easing curves derived strictly from `CanvasKitTheme.motion` (`short1`, `medium1`, `standard`, etc.).

### 5. Compose Compiler Stability & Zero Vendor Lock-In
* **Zero DI Lock-In:** Core library components have zero dependency on Hilt or Koin at the public API level, relying entirely on Compose `CompositionLocalProvider` (`LocalCanvasKitColors`, `LocalCanvasKitTypography`, etc.).
* **Compiler Stability:** All data models are annotated with `@Immutable` or `@Stable`. Public APIs avoid unstable parameters to eliminate unnecessary recompositions.

---

## 🧩 Complete Component Library

| Package | Components | Highlights |
|---|---|---|
| **`navigation`** | `CanvasKitSegmentedControl`<br>`CanvasKitPagerIndicator`<br>`CanvasKitTabRow` & `CanvasKitTab`<br>`CanvasKitTopBar`<br>`CanvasKitBottomBar` | Elastic sliding pill indicator, Worm & Dots page transitions, Underline & Pill tab indicators. |
| **`feedback`** | `CanvasKitLinearProgressBar`<br>`CanvasKitCircularProgressBar`<br>`CanvasKitStepIndicator`<br>`CanvasKitBanner`<br>`CanvasKitDialog`<br>`CanvasKitSkeleton` | Spring-backed determinate & continuous sweep indeterminate modes, Horizontal wizard & Vertical timeline steppers. |
| **`inputs`** | `CanvasKitOtpField`<br>`CanvasKitSearchField`<br>`CanvasKitSwitch`<br>`CanvasKitCheckbox`<br>`CanvasKitRadioButton`<br>`CanvasKitSlider`<br>`TextField` | Single-field consolidated OTP (SMS auto-fill compatible, masked PIN support), pill search bar with animated clear & loading states. |
| **`content`** | `CanvasKitBadge`<br>`CanvasKitBadgedBox`<br>`CanvasKitAvatar`<br>`CanvasKitAvatarGroup` | Spring pop-in notification dots and 99+ counters, user initials extraction fallback, presence indicators with cutout rings, overlapping avatar stacks. |
| **`lists`** | `CanvasKitSwipeToDismissBox`<br>`CanvasKitListItem` | Spring rebound dismiss gestures, bidirectional archive/delete reveal, custom TalkBack accessibility actions. |
| **`layout`** | `CanvasKitDivider`<br>`CanvasKitVerticalDivider`<br>`CanvasKitAccordion`<br>`CanvasKitLoadingScaffold` | Tokenized hairline strokes, inset text alignment margins, collapsible animated groups. |
| **`buttons`** | `CanvasKitButton`<br>`CanvasKitIconButton` | Primary, Secondary, Ghost variants, tactile press scaling, slot-based content. |
| **`cards`** | `CanvasKitCard` | Elevated, Outlined, and Flat card containers with slot-based headers and footers. |
| **`chips`** | `CanvasKitChip` | Filter, Suggestion, and Assist chips with active borders and selection states. |

---

## 🖼️ Mandatory Multi-Preview Standard

Every public CanvasKit component is accompanied by a dedicated `ComponentNamePreview.kt` file ensuring complete visual validation across 4 mandatory preview scenarios:
1. **Light Mode** (default baseline)
2. **Dark Mode** (`uiMode = Configuration.UI_MODE_NIGHT_YES`)
3. **Right-To-Left / RTL** (e.g., Arabic locale preview)
4. **Dynamic Font Scale (2.0x)** (`fontScale = 2.0f`)

---

## 🛠️ Usage & Integration

### 1. Theme Configuration
Wrap your application's root in the `CanvasKitTheme` provider to feed custom design tokens through CompositionLocals:

```kotlin
import es.joshluq.canvaskit.foundations.theme.CanvasKitTheme
import es.joshluq.canvaskit.components.buttons.CanvasKitButton

setContent {
    CanvasKitTheme(
        darkTheme = isSystemInDarkTheme()
    ) {
        CanvasKitButton(
            onClick = { /* Handle action */ }
        ) {
            Text("Canvas Button")
        }
    }
}
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
