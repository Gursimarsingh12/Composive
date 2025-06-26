# Composive - Compose Responsive & Adaptive Design 🎨

[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform-orange.svg)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/compose-multiplatform-blue.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![compose-mp-version](https://img.shields.io/badge/compose--multiplatform-1.8.2-blue)](https://github.com/JetBrains/compose-multiplatform)
[![kotlin-version](https://img.shields.io/badge/kotlin-2.1.21-blue)](https://github.com/JetBrains/compose-jb)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/Gursimarsingh12/Composive)
[![JitPack](https://jitpack.io/v/Gursimarsingh12/Composive.svg)](https://jitpack.io/#Gursimarsingh12/Composive)
[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](https://github.com/Gursimarsingh12/Composive/blob/main/LICENSE)

![badge-Android](https://img.shields.io/badge/Platform-Android-brightgreen)
![badge-iOS](https://img.shields.io/badge/Platform-iOS-lightgray)
![badge-JVM](https://img.shields.io/badge/Platform-JVM-orange)
![badge-macOS](https://img.shields.io/badge/Platform-macOS-purple)
![badge-web](https://img.shields.io/badge/Platform-Web-blue)

**Composive** is a powerful library that enables you to create **responsive** and **adaptive** UIs effortlessly in your Compose Multiplatform applications, with automatic theme adaptation across all platforms.

> 💡 **Developer Tip:** No emulators needed! Run on desktop and resize the window to instantly test mobile portrait, landscape, tablet, and desktop layouts with hot reload. Perfect for rapid development! 🔥

![Composive Banner](assets/composive-banner.png)

**Composive** stands for **Compose** **Responsive** & **Adaptive** design

## 🎥 See Composive in Action

Watch how Composive automatically adapts your UI across different screen sizes and platforms:

https://github.com/user-attachments/assets/e95f7754-0336-485e-a421-a9277a5597b7

*Desktop window resizing demonstrates real-time responsive layout changes - from mobile portrait to desktop layouts!*

## ✨ Key Features

### 🔄 **Smart Responsive Design**
- **Automatic screen size detection** across all devices (mobile, tablet, desktop)
- **Fluid typography scaling** that adapts to screen dimensions
- **Responsive dimensions** for padding, margins, and component sizing
- **Orientation-aware layouts** (Portrait, Landscape, Square)

### 🎭 **Cross-Platform Theme Adaptation**
- **Platform-smart defaults**: Android gets Material 3, iOS/Desktop get Cupertino
- **Dual theme system** supporting both Material 3 and Cupertino designs
- **Automatic dark/light theme detection**
- **Custom theme configurations** with fine-grained control

### 📱 **Device-Aware Components**
- **DeviceConfiguration** API for layout decisions
- **WindowSizeClass integration** for precise responsive behavior
- **Platform detection** with capability-based design decisions
- **Orientation handling** for optimal layout adaptation

### 🛠 **Developer Experience**
- **Zero-configuration setup** - works out of the box
- **Compose-first API** with familiar patterns
- **Type-safe configuration builders**
- **Comprehensive documentation** with practical examples

## 📱 Platform Support

| Platform | Status | Features |
|----------|---------|----------|
| 🤖 **Android** | ✅ Full Support | Material 3 theme, responsive dimensions, device detection |
| 🍎 **iOS** | ✅ Full Support | Cupertino theme, responsive dimensions, device detection |
| 🖥️ **Desktop** | ✅ Full Support | Platform-adaptive themes, window size handling |
| 🌐 **Web** | 🚧 Coming Soon | Browser-optimized responsive behavior |

### Platform-Specific Features

- **Android**: Material 3 design system with adaptive layouts
- **iOS**: Native Cupertino components and iOS design patterns  
- **Desktop**: Mouse/keyboard optimized interactions with window resizing
- **Web**: Progressive enhancement for browser environments *(planned)*

## 🔥 Hot Reload Magic

**Skip emulators and previews!** Run your app on desktop and resize the window to instantly test different layouts:

| Window Width | Device Type | Layout |
|--------------|-------------|---------|
| < 600dp | 📱 Mobile Portrait | Single column, bottom nav |
| 600-840dp (landscape) | 📱 Mobile Landscape | Two columns, horizontal nav |
| 600-840dp (portrait) | 📱 Tablet Portrait | Side nav rail, two columns |
| 840-1200dp | 🖥️ Tablet Landscape | Navigation drawer, 3 columns |
| > 1200dp | 💻 Desktop | Full nav drawer, 4+ columns |

**Development just got 10x faster!** 🚀

### Quick Test Command
```bash
./gradlew desktopRunHot --mainClass com.example.yourApp.Main.kt --stacktrace --auto
```

## 🚀 Quick Start

### 1. Repository Setup

Add JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Installation

Add the dependency to your `build.gradle.kts` (Module: shared or commonMain):

```kotlin
dependencies {
    implementation("com.github.Gursimarsingh12.composive:composive-responsive-adaptive:1.0.0")
}
```

### 3. Basic Setup

```kotlin
@Composable
fun App() {
    ComposiveTheme {
        // Your app content - automatically responsive!
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    val deviceConfig = rememberDeviceConfiguration()
    
    when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            SingleColumnLayout()
        }
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> {
            MultiColumnLayout(columns = deviceConfig.getRecommendedColumns())
        }
        else -> {
            TwoColumnLayout()
        }
    }
}
```

### 4. Access Theme Values

```kotlin
@Composable
fun ResponsiveCard() {
    Card(
        modifier = Modifier
            .padding(AppTheme.dimensions.cardSpacing)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.dimensions.cardElevation
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = "Responsive Typography",
                style = AppTheme.materialTypography.headlineSmall
            )
            Text(
                text = "Automatically scales with screen size!",
                style = AppTheme.materialTypography.bodyMedium
            )
        }
    }
}
```

## 🎨 Advanced Configuration

### Custom Theme Configuration

```kotlin
@Composable
fun App() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            // Force Material 3 on all platforms
            withMaterialTheme()
            
            // Or force Cupertino on all platforms
            // withCupertinoTheme()
            
            // Set custom fonts
            withCustomMaterialFonts(
                displayFont = myBrandFont,
                bodyFont = myReadingFont
            )
            
            // Customize dimensions
            withCustomDimensions(
                small = myCustomMobileDimensions,
                large = myCustomDesktopDimensions
            )
        }
    ) {
        MainScreen()
    }
}
```

### Platform-Specific Behavior

```kotlin
@Composable
fun PlatformAdaptiveContent() {
    val platform = AppTheme.platform
    val deviceConfig = rememberDeviceConfiguration()
    
    when {
        platform.isAndroid() && deviceConfig.isMobile() -> {
            // Android mobile specific layout
            AndroidMobileLayout()
        }
        platform.isIOS() && deviceConfig.isTablet() -> {
            // iOS tablet specific layout
            IOSTabletLayout()
        }
        platform.isDesktop() -> {
            // Desktop specific layout with mouse interactions
            DesktopLayout()
        }
        else -> {
            // Default responsive layout
            DefaultLayout()
        }
    }
}
```

## 📋 Core APIs

| API | Description | Usage |
|-----|-------------|-------|
| `ComposiveTheme` | Main theme wrapper | Wrap your app content |
| `responsiveConfiguration` | Configuration DSL | Customize responsive behavior |
| `rememberDeviceConfiguration()` | Get current device type | Make layout decisions |
| `AppTheme.dimensions` | Responsive dimensions | Access spacing, sizes |
| `AppTheme.materialTypography` | Material typography | Text styles |
| `AppTheme.cupertinoTypography` | Cupertino typography | iOS-style text |
| `AppTheme.platform` | Platform information | Platform-specific logic |
| `DeviceConfiguration` | Device type enum | Layout decision making |

### 🎨 Complete AppTheme Properties

| Property | Type | Example | Responsive Range |
|----------|------|---------|------------------|
| **`dimensions`** | `Dimensions` | `AppTheme.dimensions.cardPadding` | 12-32dp |
| **`materialTypography`** | `androidx.compose.material3.Typography` | `AppTheme.materialTypography.headlineLarge` | 30-50sp |
| **`cupertinoTypography`** | `com.slapps.cupertino.theme.Typography` | `AppTheme.cupertinoTypography.largeTitle` | 30-50sp |
| **`fontWeights`** | `ResponsiveFontWeights` | `AppTheme.fontWeights.heading` | Semantic weights |
| **`materialColors`** | `MaterialColorScheme` | `AppTheme.materialColors.primary` | Material 3 colors |
| **`cupertinoColors`** | `ColorScheme` | `AppTheme.cupertinoColors.accent` | iOS colors |
| **`platform`** | `Platform` | `AppTheme.platform.isAndroid()` | Platform detection |
| **`orientation`** | `Orientation` | `AppTheme.orientation` | Portrait/Landscape |
| **`configuration`** | `ResponsiveConfiguration` | `AppTheme.configuration` | Current config |

> 📖 **[See complete API reference →](docs/api-reference.md)**

## 📚 Documentation

📖 **[Complete Documentation →](docs/index.md)** - Full documentation with interactive examples and comprehensive guides

### Core Concepts
- **[Getting Started](docs/getting-started.md)** - Quick setup guide
- **[Device Configuration](docs/device-configuration.md)** - Responsive breakpoints
- **[Configuration](docs/configuration.md)** - Theme & responsive customization
- **[Typography](docs/typography.md)** - Responsive text system
- **[Responsive Dimensions](docs/responsive-dimensions.md)** - Adaptive spacing

### Advanced Usage  
- **[Examples](docs/examples.md)** - Real-world implementations
- **[API Reference](docs/api-reference.md)** - Complete API documentation
- **[Migration Guide](docs/migration-guide.md)** - Version upgrade guide

### 🍎 iOS & Adaptive Widgets
- **[iOS Cupertino Widgets →](https://github.com/alexzhirkevich/compose-cupertino/blob/master/docs/Cupertino.md)** - Complete iOS-style component library
- **[Adaptive Widgets →](https://github.com/alexzhirkevich/compose-cupertino/blob/master/docs/Adaptive.md)** - Platform-adaptive component patterns

**See it in action:** 🎥

https://github.com/user-attachments/assets/982485e8-c581-4c0e-8302-0cb257acd892

*Composive builds upon the excellent [compose-cupertino](https://github.com/alexzhirkevich/compose-cupertino) library for iOS theming and adaptive components.*

## 🤝 Community & Support

### 🐛 Found a Bug?
- **[Report Issues →](https://github.com/Gursimarsingh12/Composive/issues/new?template=bug_report.md)** - Help us improve Composive
- Include device details, code snippets, and expected vs actual behavior
- Check [existing issues](https://github.com/Gursimarsingh12/Composive/issues) first

### 💡 Have an Idea?
- **[Request Features →](https://github.com/Gursimarsingh12/Composive/issues/new?template=feature_request.md)** - Shape Composive's future


### 🚀 Want to Contribute?
- **[Submit Pull Requests →](https://github.com/Gursimarsingh12/Composive/pulls)** - Code contributions welcome!
- **[Contributing Guide →](docs/CONTRIBUTING.md)** - Development setup and guidelines
- **[Good First Issues →](https://github.com/Gursimarsingh12/Composive/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22)** - Perfect for new contributors

### 💬 General Feedback
- **[Contact →](mailto:anonymouslike083@gmail.com)** - Direct feedback to the maintainer
- **[Linkedin →](https://www.linkedin.com/in/gursimar-singh-ba4196204/)** - Direct feedback to the maintainer

## 🤝 Contributing

**Contributions are welcome!** 🎉 Feel free to fork this repository and submit a pull request. Please open an issue first to discuss changes.

### 🚀 How to Contribute

1. **🍴 Fork the repository** - Click the "Fork" button at the top of this page
2. **🌟 Create your feature branch** - `git checkout -b feature/amazing-feature`
3. **💡 Open an issue first** - Discuss your proposed changes with the community
4. **✨ Make your changes** - Follow our coding standards and best practices
5. **✅ Test thoroughly** - Ensure your changes work across platforms
6. **📝 Commit your changes** - `git commit -m 'Add some amazing feature'`
7. **🚀 Push to the branch** - `git push origin feature/amazing-feature`
8. **🔄 Open a Pull Request** - Submit your changes for review

### 📋 Contribution Guidelines

- **Quality First**: Ensure your code is well-tested and follows Kotlin/Compose best practices
- **Documentation**: Update relevant documentation for any new features
- **Platform Support**: Test on Android, iOS, and Desktop platforms when possible
- **Responsive Design**: Keep responsive behavior in mind for all contributions
- **Code Style**: Follow the existing code style and conventions

### 🎯 Areas We Welcome Contributions

- 🐛 **Bug fixes** - Help make Composive more stable
- ✨ **New features** - Enhance responsive capabilities
- 📚 **Documentation** - Improve guides and examples
- 🌐 **Web platform support** - Help us add Web platform support
- 🎨 **New themes** - Create additional theme variations
- 📱 **Platform enhancements** - Improve iOS, Android, or Desktop-specific features

**See our [Contributing Guide](docs/CONTRIBUTING.md) for detailed development setup instructions.**

## 👨‍💻 About the Author

**Gursimar Singh**  
📧 Email: anonymouslike083@gmail.com  
💼 LinkedIn: [Connect with me](https://www.linkedin.com/in/gursimar-singh-ba4196204/)  
🐙 GitHub: [@Gursimarsingh12](https://github.com/Gursimarsingh12)

*Passionate about creating beautiful, responsive user interfaces and making development tools more accessible to everyone.*

## 🙏 Credits

Composive is built on the shoulders of giants. See [CREDITS.md](docs/CREDITS.md) for acknowledgments.

## 📄 License

```
Copyright 2024 Gursimar Singh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

**Made with ❤️ for the Compose Multiplatform community** by **Gursimar Singh**