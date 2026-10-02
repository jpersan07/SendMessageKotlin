# SendMessage

Small Android app (Kotlin, View-based UI) with a screen to type a message and a second screen that shows a "message received" view.

## Features

- **Compose screen** (`SendMessageActivity`, launcher activity): a text field and an **Enviar** (Send) button. Pressing the button starts `ViewMessageActivity` and passes the typed text in the intent extras under the key `KEY_MESSAGE`.
- **Received screen** (`ViewMessageActivity`): shows a "MENSAJE RECIBIDO" label and an image, with edge-to-edge window insets handling.
- Custom font (Super Waffles) for the title.

> Note: `ViewMessageActivity` does not yet read or display `KEY_MESSAGE`; the text is passed but not rendered.

## Tech stack

- Kotlin, Android Gradle Plugin 9.4.1, Java 11 source/target compatibility
- `minSdk` 24 · `targetSdk` 37 · `compileSdk` 37
- View Binding enabled (layouts currently use `findViewById`)
- AndroidX: AppCompat, ConstraintLayout, Core KTX, Activity KTX, Navigation (fragment/ui KTX), Material Components
- Tests: JUnit 4, AndroidX Test JUnit, Espresso

## Project structure

```
SendMessage/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/example/sendmessage/
│       │   │   ├── SendMessageActivity.kt
│       │   │   ├── ViewMessageActivity.kt
│       │   │   └── SendMessageApplication.kt
│       │   ├── res/layout/   # activity_send_message, activity_view_message
│       │   └── AndroidManifest.xml
│       ├── test/             # local unit tests
│       └── androidTest/      # instrumented tests
├── recursos/                 # source assets (fonts, images, SVGs)
├── gradle/libs.versions.toml
└── settings.gradle.kts
```

## Setup / build

Requirements: Android Studio with a recent JDK and Android SDK 37 installed (AGP 9.4.1).

```bash
./gradlew assembleDebug        # build debug APK
./gradlew installDebug         # install on a connected device/emulator
./gradlew test                 # local unit tests
./gradlew connectedAndroidTest # instrumented tests
```

Or open the project in Android Studio and run the `app` configuration.

## Permissions

The manifest declares no permissions.

<!-- TODO: add screenshots -->
<!-- TODO: add license (no LICENSE file present) -->
