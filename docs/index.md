# Composive Documentation 📖

Welcome to the comprehensive documentation for **Composive** - the responsive and adaptive design library for Compose Multiplatform!

## 🎯 What is Composive?

Composive makes it effortless to create **responsive** and **adaptive** UIs that work beautifully across mobile, tablet, and desktop platforms. With platform-smart defaults and extensive customization options, your apps will feel native on every platform.

## ✨ Key Features

🔄 **Smart Responsive Design** - Automatic screen size detection and fluid typography scaling  
🎭 **Cross-Platform Theme Adaptation** - Platform-smart defaults with dual theme system  
📱 **Device-Aware Components** - WindowSizeClass integration and orientation handling  
🛠️ **Developer Experience** - Zero-configuration setup with type-safe builders  
🔥 **Hot Reload Magic** - Test all layouts by resizing desktop window  

## 🚀 Quick Start

```kotlin
@Composable
fun App() {
    ComposiveTheme {
        val deviceConfig = rememberDeviceConfiguration()
        
        when (deviceConfig) {
            DeviceConfiguration.MOBILE_PORTRAIT -> MobileLayout()
            DeviceConfiguration.DESKTOP -> DesktopLayout()
            else -> TabletLayout()
        }
    }
}
```

## 📚 Documentation Structure

### 🏗️ **Getting Started**
- **[Installation](installation.md)** - Add Composive to your project
- **[Getting Started](getting-started.md)** - Your first responsive app
- **[Examples](examples.md)** - Real-world implementation patterns

### 🎨 **Core Concepts**
- **[Device Configuration](device-configuration.md)** - Responsive layout decisions
- **[Configuration](configuration.md)** - Themes, typography & dimensions
- **[Typography](typography.md)** - Responsive text styling

### 📏 **Design System**
- **[Responsive Dimensions](responsive-dimensions.md)** - Adaptive spacing and sizing

### 📖 **Reference**
- **[API Reference](api-reference.md)** - Complete API documentation
- **[Migration Guide](migration-guide.md)** - Upgrade from older versions

### 🤝 **Community**
- **[Contributing](CONTRIBUTING.md)** - Help improve Composive
- **[Credits](CREDITS.md)** - Acknowledgments and licenses

## 🔥 Pro Tip: Desktop Development

Skip emulators and previews! Run your app on desktop and resize the window to instantly test all layouts:

```bash
./gradlew desktopRunHot --mainClass com.example.myApp.MainKt --stacktrace --auto
```

| Window Width | Device Type | Perfect For Testing |
|--------------|-------------|-------------------|
| < 600dp | 📱 Mobile Portrait | Phone apps |
| 600-840dp | 📱 Mobile Landscape | Phone landscape |
| 840-1200dp | 🖥️ Tablet | Tablet apps |
| > 1200dp | 💻 Desktop | Desktop apps |

## 📱 Device Configuration Quick Reference

```kotlin
val deviceConfig = rememberDeviceConfiguration()

// Check device type
deviceConfig.isMobile()    // Phone
deviceConfig.isTablet()    // Tablet
deviceConfig.isDesktop()   // Desktop

// Get layout recommendations
deviceConfig.getRecommendedColumns()  // 1-4 columns
deviceConfig.isPortrait()            // Portrait orientation
deviceConfig.isLandscape()           // Landscape orientation
```

## 🎨 Theme Access Quick Reference

```kotlin
// Access responsive values anywhere
Text(
    text = "Hello World",
    style = AppTheme.materialTypography.headlineSmall,
    fontWeight = AppTheme.fontWeights.heading,
    modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
)

// Platform information
val platform = AppTheme.platform
when {
    platform.isAndroid() -> AndroidSpecificCode()
    platform.isIOS() -> IOSSpecificCode()
    platform.isDesktop() -> DesktopSpecificCode()
}
```

### 📋 Complete AppTheme Properties

| Category | Property | Type | Example Usage |
|----------|----------|------|---------------|
| **📏 Dimensions** | `dimensions` | `Dimensions` | `AppTheme.dimensions.cardPadding` |
| **📐 Orientation** | `orientation` | `Orientation` | `AppTheme.orientation == Orientation.Portrait` |
| **⚖️ Font Weights** | `fontWeights` | `ResponsiveFontWeights` | `AppTheme.fontWeights.semiBold` |
| **📝 Material Typography** | `materialTypography` | `androidx.compose.material3.Typography` | `AppTheme.materialTypography.headlineLarge` |
| **🍎 Cupertino Typography** | `cupertinoTypography` | `com.slapps.cupertino.theme.Typography` | `AppTheme.cupertinoTypography.largeTitle` |
| **🎨 Material Colors** | `materialColors` | `MaterialColorScheme` | `AppTheme.materialColors.primary` |
| **🎨 Cupertino Colors** | `cupertinoColors` | `ColorScheme` | `AppTheme.cupertinoColors.accent` |
| **⚙️ Configuration** | `configuration` | `ResponsiveConfiguration` | `AppTheme.configuration.defaultThemeType` |
| **📱 Platform** | `platform` | `Platform` | `AppTheme.platform.isAndroid()` |

### 🎯 Most Used Properties

| Property | Small Screens | Large Screens | Use Case |
|----------|---------------|---------------|----------|
| `AppTheme.dimensions.space4` | 16dp | 16dp | Standard spacing |
| `AppTheme.dimensions.cardPadding` | 12dp | 24dp | Card internal padding |
| `AppTheme.dimensions.iconMedium` | 28dp | 36dp | Action icons |
| `AppTheme.dimensions.avatarMedium` | 40dp | 56dp | User avatars |
| `AppTheme.materialTypography.headlineLarge` | 30sp | 50sp | Major headlines |
| `AppTheme.materialTypography.bodyLarge` | 16sp | 29sp | Body text |
| `AppTheme.fontWeights.heading` | SemiBold | SemiBold | Headings |
| `AppTheme.fontWeights.body` | Normal | Normal | Body text |
| `AppTheme.fontWeights.emphasis` | Medium | Medium | Emphasized content |

### 🎨 Font Weight Examples

```kotlin
// Semantic weights (recommended)
Text("Headline", fontWeight = AppTheme.fontWeights.heading)
Text("Body text", fontWeight = AppTheme.fontWeights.body)
Text("Emphasized", fontWeight = AppTheme.fontWeights.emphasis)
Button(onClick = {}) {
    Text("Action", fontWeight = AppTheme.fontWeights.button)
}

// Basic weights
Text("Bold text", fontWeight = AppTheme.fontWeights.bold)
Text("Normal text", fontWeight = AppTheme.fontWeights.normal)
Text("Light text", fontWeight = AppTheme.fontWeights.light)
```

📖 **[Complete API Reference →](api-reference.md)**

## ⚙️ Configuration Quick Reference

```kotlin
ComposiveTheme(
    configuration = responsiveConfiguration {
        // Theme selection
        withMaterialTheme()        // Force Material 3
        withCupertinoTheme()       // Force Cupertino
        withPlatformDefaultTheme() // Platform-appropriate
        
        // Custom fonts
        withCustomMaterialFonts(
            bodyFont = myFont
        )
        
        // Custom dimensions
        withCustomDimensions(
            small = myMobileDimensions,
            large = myDesktopDimensions
        )
    }
) {
    MyApp()
}
```

## 🎯 Common Patterns

### Adaptive Navigation

```kotlin
@Composable
fun AdaptiveNav() {
    val deviceConfig = rememberDeviceConfiguration()
    
    when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            NavigationBar { /* bottom nav */ }
        }
        DeviceConfiguration.TABLET_PORTRAIT -> {
            NavigationRail { /* side nav */ }
        }
        else -> {
            NavigationDrawer { /* drawer nav */ }
        }
    }
}
```

### Responsive Grid

```kotlin
@Composable
fun ResponsiveGrid() {
    val columns = rememberDeviceConfiguration().getRecommendedColumns()
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing)
    ) {
        items(data) { item ->
            GridItem(item)
        }
    }
}
```

### Platform-Specific Code

```kotlin
@Composable
fun PlatformButton() {
    val platform = AppTheme.platform
    
    when {
        platform.isAndroid() -> {
            Button(onClick = {}) { Text("Material Button") }
        }
        platform.isIOS() -> {
            CupertinoButton(onClick = {}) { Text("Cupertino Button") }
        }
        else -> {
            OutlinedButton(onClick = {}) { Text("Default Button") }
        }
    }
}
```

## 📊 Responsive Breakpoints

| Breakpoint | Width Range | Height | Device Examples |
|------------|-------------|--------|-----------------|
| **Mobile Portrait** | < 600dp | Any | iPhone, Android phones |
| **Mobile Landscape** | 600-840dp | Compact | Phones in landscape |
| **Tablet Portrait** | 600-840dp | Expanded | iPad, Android tablets |
| **Tablet Landscape** | 840-1200dp | Any | Tablets in landscape |
| **Desktop** | > 1200dp | Expanded | Laptops, desktops |

## 🎨 Design Philosophy

### Platform-Smart Defaults
- **Android** → Material 3 theme automatically
- **iOS/Desktop** → Cupertino theme automatically
- **All Platforms** → Responsive dimensions and typography

### Zero Configuration
```kotlin
// This just works! 🎉
ComposiveTheme {
    MyApp() // Automatically responsive & platform-appropriate
}
```

### Extensive Customization
```kotlin
// Full control when you need it
ComposiveTheme(
    configuration = responsiveConfiguration {
        // Customize everything
    }
) {
    MyApp()
}
```

## 🔗 Useful Links

- **[GitHub Repository](https://github.com/gursimar/composive-responsive-adaptive)** 
- **[Maven Central](https://search.maven.org/search?q=g:%22com.gursimar.composive%22)**
- **[Issue Tracker](https://github.com/gursimar/composive-responsive-adaptive/issues)**
- **[Discussions](https://github.com/gursimar/composive-responsive-adaptive/discussions)**

## 🤝 Getting Help

1. **Check the documentation** - Most questions are answered here
2. **Browse examples** - See real-world implementation patterns  
3. **Search issues** - Someone might have asked already
4. **Ask in discussions** - Community support and questions
5. **Report bugs** - Help us improve Composive

## 🎉 Ready to Build?

Choose your starting point:

- **New to Composive?** → [Installation](installation.md)
- **Want to see it in action?** → [Examples](examples.md)
- **Need specific API info?** → [API Reference](api-reference.md)
- **Migrating from older version?** → [Migration Guide](migration-guide.md)

---

**Welcome to the future of responsive Compose Multiplatform development!** 🚀 