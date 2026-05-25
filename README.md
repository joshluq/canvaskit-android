# 🎨 CanvasKit — Atelier Design System

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84.svg?style=flat-square&logo=android)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.3%2B-7F52FF.svg?style=flat-square&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-1.7%2B-4285F4.svg?style=flat-square&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Ecosystem](https://img.shields.io/badge/Ecosystem-Kit-FF5722.svg?style=flat-square)](https://github.com/joshluq)

**CanvasKit** is the foundational Design System and visual backbone of the **"Kit" ecosystem** (alongside `authKit`, `encryptionKit`, and `analyticsKit`). Inspired by high-end design languages like *Shopify Polaris* and *IBM Carbon*, CanvasKit treats UI components as artisanal craft—combining extreme aesthetic refinement with technical rigor and native accessibility.

---

## 🏛️ Module Taxonomy

CanvasKit is organized under atomic modular design principles to guarantee separation of concerns:

```
/library/src/main/java/es/joshluq/canvaskit/
│
├── core/
│   └── tokens/         # Design Tokens: Colors, Typography, Shapes, Spacing, and Motion
│
├── components/         # Reusable Components (Buttons, Cards, Inputs, Feedback Banners)
│
├── foundations/        # Lower-level layouts, custom modifiers, and the Theme Engine
│
└── showcase/           # Showcase/Catalog Application for testing and previews
```

*   **`:library`** (`canvaskit`): The main reusable library module containing all tokens, modifiers, and custom UI components.
*   **`:showcase`**: An isolated demonstration application used to catalog, sandbox, and test CanvasKit components in real-time.

---

## ⚡ Key Pillars

### 1. Strict Tokenization
No hardcoded colors, spacing, or typography. All components derive styling from raw design tokens mapped to a semantic layer (e.g., `#FF2E2E` ➔ `Red-50` ➔ `CanvasTheme.colors.border.destructive`).

### 2. Composition over Configuration
CanvasKit leverages Jetpack Compose Slot APIs (`content: @Composable () -> Unit`) to create flexible building blocks instead of bloated, monolithic composables with endless parameters.

### 3. Native Accessibility (A11y)
*   **Touch Targets:** Guaranteed minimum `48.dp` touch targets utilizing `minimumInteractiveComponentSize()`.
*   **Screen Readers:** Merged semantics for complex components, custom state descriptions, and live regions.
*   **Dynamic Scaling:** Fully scale-independent layouts (using `sp` and flexible heights) that scale beautifully at `2.0x` font scaling.
*   **WCAG AA Compliance:** Strict text-to-background contrast enforcement.

### 4. Performance Optimized
Fully optimized for Jetpack Compose. All models use `@Stable`/`@Immutable` annotations, and APIs avoid exposing standard unstable collections (like `List`/`Set`), replacing them with immutable structures or stable wrappers.

---

## 🛠️ Usage & Integration

### 1. Theme Configuration
Wrap your application's root in the `CanvasTheme` provider to feed custom design tokens through CompositionLocals:

```kotlin
import es.joshluq.canvaskit.foundations.theme.CanvasTheme

setContent {
    CanvasTheme(
        darkTheme = isSystemInDarkTheme()
    ) {
        // Your application components here
        CanvasButton(onClick = { /* ... */ }) {
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
| `./gradlew :library:assemble` | Compile and assemble the library release bundle |
| `./gradlew :library:test` | Run local unit tests (JVM) for the design system |
| `./gradlew :showcase:assembleDebug` | Build the showcase development application |
| `./gradlew :showcase:connectedAndroidTest` | Run instrumented tests on an emulator/device |

---

## ⚙️ Configuration Properties

The library coordinates are centrally managed in `gradle.properties` at the root of the project:

*   `catalogVersion` : Coordinate for the version catalog (e.g., `es.joshluq.kit:catalog:1.5.0`).
*   `libraryVersion` : Target version for the `:library` artifact (e.g., `1.0.0`).
*   `repositoryUrl` : GitHub packages repository URL for publishing/fetching dependencies.

---

## 📖 Guidelines for Contributors
For deep architectural patterns, API contracts, Jetpack Compose performance standards, and accessibility details, please refer to:

👉 **[AGENTS.md](file:///c:/Users/josh_/AndroidStudioProjects/canvaskit-android/AGENTS.md)** — Core Architecture & Coding Guidelines.
