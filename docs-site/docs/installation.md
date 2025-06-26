---
title: Install Composive - Setup Guide for Compose Multiplatform Projects
description: Complete installation guide for Composive responsive UI library. Learn how to install Composive in your Kotlin Multiplatform projects for Android, iOS, Desktop & Web. Includes setup instructions, dependencies, and troubleshooting.
keywords:
  - Install Composive
  - Composive Installation
  - Compose Multiplatform Setup
  - Kotlin Multiplatform Installation
  - Android UI Library Install
  - iOS UI Library Setup
  - Gradle Dependencies
  - JitPack Installation
  - Responsive UI Setup
author: Gursimar Singh
---

# Installation Guide 📦

This guide covers installing Composive in different types of Kotlin Multiplatform projects.

## 📋 Requirements

### Minimum Versions
- **Kotlin**: 1.9.0+
- **Compose Multiplatform**: 1.5.0+
- **Android minSdk**: 21+
- **iOS Deployment Target**: 13.0+

### Supported Platforms
- ✅ **Android** (API 21+)
- ✅ **iOS** (iOS 13.0+)
- ✅ **Desktop** (Windows, macOS, Linux)
- ✅ **Web** (Kotlin/Wasm, Kotlin/JS)

## 🚀 Quick Installation

### Repository Setup

First, add JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### For New Projects

If you're starting a new project, use the official Kotlin Multiplatform wizard:

1. Visit [kmp.jetbrains.com](https://kmp.jetbrains.com/?android=true&ios=true&iosui=compose&desktop=true&includeTests=true)
2. Configure your project with:
   - ✅ Android
   - ✅ iOS (with Compose Multiplatform UI)
   - ✅ Desktop
   - ✅ Include tests
3. Download and extract your project
4. Add JitPack repository (see above)
5. Add Composive dependency (see below)

### For Existing Projects

Add Composive to your `build.gradle.kts` files:

#### Shared Module (commonMain)

```kotlin
// shared/build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.gursimarsingh12:composive-responsive-adaptive:1.0.0")
        }
    }
}
```

#### Version Catalog (Recommended)

If using Gradle version catalogs (`libs.versions.toml`):

```toml
[versions]
composive = "1.0.0"

[libraries]
composive-responsive-adaptive = { module = "io.github.gursimarsingh12:composive-responsive-adaptive", version.ref = "composive" }
```

Then in your `build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation(libs.composive.responsive.adaptive)
}
```

## 📱 Platform-Specific Setup

### Android Setup

No additional setup required! Composive works out of the box with Android.

```kotlin
// androidMain/build.gradle.kts - No additional dependencies needed
android {
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
}
```

### iOS Setup

No additional setup required! Composive integrates seamlessly with iOS.

```kotlin
// iosMain - No additional dependencies needed
kotlin {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}
```

### Desktop Setup

```kotlin
// Desktop module or shared module for desktop target
kotlin {
    jvm("desktop") {
        jvmToolchain(11)
    }
    
    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                // Composive dependency already included in commonMain
            }
        }
    }
}
```

## 🔧 Complete Project Structure

Here's a complete example of a multiplatform project with Composive:

### Project Structure
```
MyApp/
├── shared/
│   ├── src/
│   │   ├── commonMain/kotlin/
│   │   ├── androidMain/kotlin/
│   │   ├── iosMain/kotlin/
│   │   └── desktopMain/kotlin/
│   └── build.gradle.kts
├── androidApp/
│   └── build.gradle.kts
├── iosApp/
├── desktopApp/
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml
├── settings.gradle.kts
└── build.gradle.kts
```

### `settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "MyApp"
include(":commonMain")
include(":androidApp")
include(":desktopApp")
```

### Root `build.gradle.kts`

```kotlin
plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
}
```

### `gradle/libs.versions.toml`

```toml
[versions]
kotlin = "1.9.21"
compose = "1.5.11"
composive = "1.0.0"
android-minSdk = "21"
android-compileSdk = "34"

[libraries]
# Composive
composive-responsive-adaptive = { module = "io.github.gursimarsingh12:composive-responsive-adaptive", version.ref = "composive" }

# Compose Multiplatform
compose-ui = { module = "androidx.compose.ui:ui", version.ref = "compose" }
compose-ui-tooling = { module = "androidx.compose.ui:ui-tooling", version.ref = "compose" }
compose-ui-tooling-preview = { module = "androidx.compose.ui:ui-tooling-preview", version.ref = "compose" }
compose-foundation = { module = "androidx.compose.foundation:foundation", version.ref = "compose" }
compose-material3 = { module = "androidx.compose.material3:material3", version.ref = "compose" }

[plugins]
kotlin-multiplatform = { id = "org.jetbrains.kotlin.multiplatform", version.ref = "kotlin" }
compose-multiplatform = { id = "org.jetbrains.compose", version.ref = "compose" }
android-application = { id = "com.android.application", version = "8.2.0" }
android-library = { id = "com.android.library", version = "8.2.0" }
```

### Shared Module `build.gradle.kts`

```kotlin
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    jvm("desktop")
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            
            // Composive - Main dependency
            implementation(libs.composive.responsive.adaptive)
        }
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
        }
        
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "com.yourpackage.myapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
```

### Android App `build.gradle.kts`

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(project(":shared"))
            implementation(libs.compose.ui.tooling)
        }
    }
}

android {
    namespace = "com.yourpackage.myapp.android"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    
    defaultConfig {
        applicationId = "com.yourpackage.myapp.android"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.compileSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
```

### Desktop App `build.gradle.kts`

```kotlin
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.yourpackage.myapp.MainKt"
        
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MyApp"
            packageVersion = "1.0.0"
        }
    }
}
```

## 🏃‍♂️ First Run

### 1. Create Your App Composable

Create `shared/src/commonMain/kotlin/App.kt`:

```kotlin
import androidx.compose.runtime.Composable
import com.gursimar.composive.responsive.theme.ComposiveTheme

@Composable
fun App() {
    ComposiveTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    // Your app content
}
```

### 2. Android MainActivity

```kotlin
// androidApp/src/main/kotlin/MainActivity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
```

### 3. Desktop Main

```kotlin
// desktopApp/src/jvmMain/kotlin/main.kt
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyApp"
    ) {
        App()
    }
}
```

### 4. iOS App

```swift
// iosApp/iosApp/ContentView.swift
import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.keyboard)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
```

```kotlin
// shared/src/iosMain/kotlin/main.ios.kt
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App() }
```

## 🧪 Test Your Installation

### Quick Test Component

Create this test component to verify everything is working:

```kotlin
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
import com.gursimar.composive.responsive.theme.AppTheme

@Composable
fun TestInstallation() {
    val deviceConfig = rememberDeviceConfiguration()
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.cardSpacing)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = "✅ Composive Installation Successful!",
                style = AppTheme.materialTypography.headlineSmall
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
            
            Text(
                text = "Device Configuration: $deviceConfig",
                style = AppTheme.materialTypography.bodyMedium
            )
            
            Text(
                text = "Platform: ${AppTheme.platform.name}",
                style = AppTheme.materialTypography.bodyMedium
            )
        }
    }
}
```

## 🚨 Troubleshooting

### Common Issues

#### Repository Issues

**Problem**: Cannot resolve Composive dependency
```kotlin
// ❌ Wrong - Missing JitPack repository
repositories {
    mavenCentral() // Only Maven Central
}

// ✅ Correct - Include JitPack repository  
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

#### Dependency Name Issues

**Problem**: Wrong dependency name
```kotlin
// ❌ Wrong dependency names
implementation("com.gursimar.composive:composive:1.0.0")
implementation("com.github.gursimar:composive:1.0.0")

// ✅ Correct JitPack dependency
implementation("com.github.Gursimarsingh12.composive:composive-responsive-adaptive:1.0.0")
```

#### Version Conflicts

**Problem**: Compose version conflicts
```kotlin
// ✅ Make sure all Compose dependencies use compatible versions
val composeVersion = "latest version of compose"

implementation("androidx.compose.ui:ui:$composeVersion")
implementation("androidx.compose.material3:material3:$composeVersion")
implementation("com.github.Gursimarsingh12.composive:composive-responsive-adaptive:1.0.0")
```

#### Missing WindowSizeClass Provider

**Problem**: App crashes or layouts don't respond properly
```kotlin
// ✅ Always wrap your app with ComposiveTheme
@Composable
fun App() {
    ComposiveTheme { // ← Required for responsive behavior!
        MainScreen()
    }
}
```

#### JitPack Build Issues

**Problem**: JitPack shows "Build failing" status

**Solutions:**
1. Check that the repository is public
2. Verify the release tag exists: `https://github.com/Gursimarsingh12/Composive/releases`
3. Wait for JitPack to build (may take several minutes)
4. Check JitPack logs: `https://jitpack.io/com/github/Gursimarsingh12/composive/1.0.0/build.log`

#### Network Issues

**Problem**: Cannot download from JitPack
```kotlin
// ✅ Make sure you have internet connection and JitPack is accessible
// You can also try using a VPN if JitPack is blocked in your region
```

### Getting Help

If you're still having issues:

1. **Check existing issues**: [GitHub Issues](https://github.com/Gursimarsingh12/Composive/issues)
2. **Create a new issue**: Include your `build.gradle.kts` files and error messages
3. **Contact directly**: anonymouslike083@gmail.com

### Verification Checklist

✅ JitPack repository added to `settings.gradle.kts`  
✅ Correct dependency name with exact case: `com.github.Gursimarsingh12.composive:composive-responsive-adaptive:1.0.0`  
✅ Version tag exists: [v1.0.0](https://github.com/Gursimarsingh12/Composive/releases/tag/1.0.0)  
✅ App wrapped with `ComposiveTheme`  
✅ Compatible Compose Multiplatform version  
✅ Internet connection available

## ✅ Next Steps

Now that Composive is installed, you're ready to:

1. **[Get Started](getting-started.md)** - Build your first responsive layout
2. **[Configuration](configuration.md)** - Customize themes and behavior
3. **[Examples](examples.md)** - See real-world implementations

Welcome to responsive design made easy! 🎉 