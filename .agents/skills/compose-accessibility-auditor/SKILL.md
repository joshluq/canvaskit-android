---
name: compose-accessibility-auditor
description: >-
  Audits, enforces, and tests accessibility (A11y) standards in CanvasKit Jetpack Compose components following AGENTS.md Section 5.4 and WCAG AA guidelines. Covers 48dp touch targets, semantic roles and state descriptions, LiveRegion announcements, 2.0x font scale resilience, contrast ratios, and Compose semantics testing.
---

# Compose Accessibility (A11y) Auditor & Specialist

This skill enforces strict accessibility (A11y) compliance across all **CanvasKit** components according to Section 5.4 of [`AGENTS.md`](file:///c:/Users/josh_/AndroidStudioProjects/canvaskit-android/AGENTS.md) and **WCAG AA** standards.

---

## 1. Accessibility Checklist & Rules

### 1.1 Touch Targets (Minimum 48.dp)
- Every interactive element (buttons, icon triggers, switches, chips, list rows) MUST provide a minimum touch target of `48.dp`.
- **Do NOT bloat visual bounds:** Use Compose's `Modifier.minimumInteractiveComponentSize()` or semantics bounds extensions rather than padding that distorts visual alignment.

### 1.2 Content Descriptions & Screen Readers
- **Interactive Elements:** Never leave `contentDescription` as `null`. Ensure localized, descriptive strings are provided or required in API signatures.
- **Decorative Elements:** Static icons or purely decorative visuals must explicitly set `contentDescription = null` to prevent screen reader noise.
- **Semantics Merging:** Compound components (e.g., list items, cards with title and badge) must use `Modifier.semantics(mergeDescendants = true)` so TalkBack reads them as a unified announcement.

### 1.3 Roles and Dynamic State Descriptions
- Always declare the semantic `Role`:
  ```kotlin
  Modifier.semantics {
      role = Role.Button // or Role.Checkbox, Role.Switch, Role.Tab, etc.
      stateDescription = if (isChecked) "checked" else "not checked"
  }
  ```
- Use `stateDescription` to convey dynamic states (e.g., expanded/collapsed, selected/unselected, busy/ready).

### 1.4 Live Regions for Feedback
- Feedback components (banners, toasts, inline alerts, snackbars, error prompts) must notify assistive services using `liveRegion`:
  ```kotlin
  Modifier.semantics {
      liveRegion = LiveRegionMode.Polite // Use Assertive only for critical urgent errors
  }
  ```

### 1.5 Dynamic Font Scaling (2.0x Resilience)
- All typography must use scale-independent pixels (`sp`).
- **Zero Vertical Height Hardcoding:** Containers holding text must not use fixed `Modifier.height(...)`. Use `Modifier.wrapContentHeight()` or min/max constraint boundaries (`defaultMinSize`).
- Verify in preview that text does not truncate, overlap, or clip when `fontScale = 2.0f`.

### 1.6 Color Contrast (WCAG AA Compliance)
- Normal text (< 18sp or < 14sp bold): Minimum contrast ratio of **4.5:1** against the background.
- Large text (>= 18sp or >= 14sp bold) and functional icons: Minimum contrast ratio of **3:1**.
- Verify compliance across both **Light Mode** and **Dark Mode** color tokens.

### 1.7 Focus & Hardware Navigation
- Components supporting keyboard or switch navigation must display a distinct focus indicator (`CanvasTheme.tokens.focusRing` or halo).
- Specify custom focus traversal orders using `FocusRequester` or `focusProperties` when standard layout hierarchy does not match logical user flow.

---

## 2. Automated Compose Semantics Verification

When writing or reviewing tests for CanvasKit components, write semantic tests asserting A11y contracts:

```kotlin
@Test
fun component_announces_correct_role_and_state() {
    composeTestRule.setContent {
        CanvasTheme {
            CanvasCheckbox(
                checked = true,
                onCheckedChange = {},
                contentDescription = "Remember me"
            )
        }
    }

    composeTestRule
        .onNodeWithContentDescription("Remember me")
        .assertHasClickAction()
        .assertIsOn()
}
```

---

## 3. Audit Protocol

When evaluating a CanvasKit component:
1. Check touch targets: Is `minimumInteractiveComponentSize()` respected?
2. Check TalkBack readiness: Is `mergeDescendants = true` applied to compounds? Are decorative icons muted?
3. Check dynamic states: Is `stateDescription` and `Role` declared?
4. Check font scale: Does the preview at `2.0x` render without clipping?
5. Check contrast: Do text and background tokens satisfy WCAG AA (4.5:1)?
