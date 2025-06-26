---
title: Getting Started with Composive - Responsive UI Tutorial
description: Learn how to build responsive and adaptive UIs with Composive in Compose Multiplatform. Step-by-step tutorial covering responsive layouts, adaptive components, and cross-platform design patterns.
keywords:
  - Composive Tutorial
  - Responsive UI Tutorial
  - Compose Multiplatform Tutorial
  - Adaptive UI Guide
  - Kotlin UI Tutorial
  - Cross Platform UI
  - Mobile UI Development
  - Responsive Design Guide
author: Gursimar Singh
---

# Getting Started with Composive 🚀

Welcome to Composive! This guide will walk you through setting up responsive and adaptive design in your Compose Multiplatform application.

## 📋 Prerequisites

- **Kotlin Multiplatform project** with Compose Multiplatform
- **Minimum versions**:
  - Kotlin: 1.9.0+
  - Compose Multiplatform: 1.5.0+
  - Android target SDK: 21+

## 🎯 What You'll Learn

By the end of this guide, you'll have:
- ✅ Responsive layouts that adapt to any screen size
- ✅ Platform-appropriate themes (Material 3 on Android, Cupertino on iOS)
- ✅ Typography that scales automatically
- ✅ Device-aware navigation patterns

## 📦 Step 1: Installation

### Installation via Maven Central

Add the following to your build.gradle.kts:

```kotlin
dependencies {
    implementation("io.github.gursimarsingh12:composive-responsive-adaptive:1.0.0")
}
```

## 🏗️ Step 2: Basic Setup

### Set Up Your App Entry Point

```kotlin
// In your App.kt or main composable
import com.gursimar.composive.responsive.theme.ComposiveTheme

@Composable
fun App() {
    ComposiveTheme {
        // Your app content here
        MainScreen()
    }
}
```

### Create Your First Responsive Screen

```kotlin
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
import com.gursimar.composive.responsive.core.DeviceConfiguration
import com.gursimar.composive.responsive.theme.AppTheme

@Composable
fun MainScreen() {
    val deviceConfig = rememberDeviceConfiguration()
    
    when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            MobilePortraitLayout()
        }
        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            MobileLandscapeLayout()
        }
        DeviceConfiguration.TABLET_PORTRAIT -> {
            TabletPortraitLayout()
        }
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            TabletLandscapeLayout()
        }
        DeviceConfiguration.DESKTOP -> {
            DesktopLayout()
        }
    }
}
```

## 📱 Step 3: Create Responsive Layouts

### Mobile Portrait Layout (Single Column)

```kotlin
@Composable
fun MobilePortraitLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.dimensions.screenPaddingHorizontal)
    ) {
        TopAppBar(
            title = { Text("Mobile Portrait") }
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
        ) {
            items(10) { index ->
                ResponsiveCard(
                    title = "Item $index",
                    description = "This is a responsive card that adapts to screen size"
                )
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Bottom navigation for mobile
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = true,
                onClick = { }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Search, contentDescription = null) },
                label = { Text("Search") },
                selected = false,
                onClick = { }
            )
        }
    }
}
```

### Tablet Landscape Layout (Multi-Column with Navigation Rail)

```kotlin
@Composable
fun TabletLandscapeLayout() {
    Row(modifier = Modifier.fillMaxSize()) {
        // Navigation rail on the left
        NavigationRail {
            NavigationRailItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = true,
                onClick = { }
            )
            NavigationRailItem(
                icon = { Icon(Icons.Default.Search, contentDescription = null) },
                label = { Text("Search") },
                selected = false,
                onClick = { }
            )
        }
        
        // Main content area with multiple columns
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
            modifier = Modifier.weight(1f)
        ) {
            items(20) { index ->
                ResponsiveCard(
                    title = "Card $index",
                    description = "Multi-column tablet layout"
                )
            }
        }
    }
}
```

### Desktop Layout (Full Navigation Drawer)

```kotlin
@Composable
fun DesktopLayout() {
    val drawerState = rememberDrawerState(DrawerValue.Open)
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Desktop Navigation",
                    modifier = Modifier.padding(AppTheme.dimensions.contentPaddingLarge),
                    style = AppTheme.materialTypography.headlineSmall
                )
                
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Search") },
                    selected = false,
                    onClick = { }
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Desktop Layout") },
                navigationIcon = {
                    IconButton(onClick = { /* Toggle drawer */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing)
            ) {
                items(30) { index ->
                    ResponsiveCard(
                        title = "Desktop Card $index",
                        description = "Four-column desktop layout with navigation drawer"
                    )
                }
            }
        }
    }
}
```

## 🎨 Step 4: Create Responsive Components

### Responsive Card Component

```kotlin
@Composable
fun ResponsiveCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.dimensions.cardElevation
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = title,
                style = AppTheme.materialTypography.titleMedium,
                fontWeight = AppTheme.fontWeights.heading
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
            
            Text(
                text = description,
                style = AppTheme.materialTypography.bodyMedium,
                color = LocalContentColor.current.copy(alpha = 0.7f)
            )
        }
    }
}
```

### Responsive Avatar Component

```kotlin
@Composable
fun ResponsiveAvatar(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val deviceConfig = rememberDeviceConfiguration()
    
    val avatarSize = when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> AppTheme.dimensions.avatarSmall
        DeviceConfiguration.MOBILE_LANDSCAPE,
        DeviceConfiguration.TABLET_PORTRAIT -> AppTheme.dimensions.avatarMedium
        DeviceConfiguration.TABLET_LANDSCAPE -> AppTheme.dimensions.avatarLarge
        DeviceConfiguration.DESKTOP -> AppTheme.dimensions.avatarXLarge
    }
    
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .size(avatarSize)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}
```

## 🔧 Step 5: Advanced Configuration

### Custom Theme Configuration

```kotlin
@Composable
fun App() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            // Force Material 3 on all platforms
            withMaterialTheme()
            
            // Custom fonts
            withCustomMaterialFonts(
                displayFont = FontFamily.Serif,
                bodyFont = FontFamily.SansSerif
            )
            
            // Custom dimensions for different screen sizes
            withCustomDimensions(
                small = customMobileDimensions,
                large = customDesktopDimensions
            )
        }
    ) {
        MainScreen()
    }
}

private val customMobileDimensions = Dimensions(
    // Use base dimensions as reference and modify as needed
    space1 = smallDimensions.space1,
    space2 = smallDimensions.space2,
    space4 = smallDimensions.space4,
    // Customize specific values
    cardPadding = smallDimensions.cardPadding + 4.dp,
    screenPaddingHorizontal = smallDimensions.screenPaddingHorizontal + 4.dp,
    // ... other dimensions
)
```

### Platform-Specific Behavior

```kotlin
@Composable
fun PlatformAdaptiveButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val platform = AppTheme.platform
    
    when {
        platform.isAndroid() -> {
            // Material 3 button for Android
            Button(
                onClick = onClick,
                modifier = modifier.height(AppTheme.dimensions.buttonHeightMedium)
            ) {
                Text(text, style = AppTheme.materialTypography.labelLarge)
            }
        }
        platform.isIOS() -> {
            // Cupertino button for iOS
            CupertinoButton(
                onClick = onClick,
                modifier = modifier.height(AppTheme.dimensions.buttonHeightMedium)
            ) {
                Text(text, style = AppTheme.cupertinoTypography.body)
            }
        }
        else -> {
            // Default button for other platforms
            Button(onClick = onClick, modifier = modifier) {
                Text(text)
            }
        }
    }
}
```

## 🔥 Step 6: Testing with Hot Reload

### Desktop Development Setup

1. **Run your app on desktop**:
```bash
./gradlew desktopRunHot --mainClass com.example.myApp.MainKt --stacktrace --auto
```

2. **Test different screen sizes** by resizing the window:
   - **Mobile Portrait**: < 600dp width
   - **Mobile Landscape**: 600-840dp width (wide window)
   - **Tablet Portrait**: 600-840dp width (tall window)
   - **Tablet Landscape**: 840-1200dp width
   - **Desktop**: > 1200dp width

3. **Observe layout changes** in real-time without recompilation!

### Debugging Device Configuration

```kotlin
@Composable
fun DeviceDebugInfo() {
    val deviceConfig = rememberDeviceConfiguration()
    val windowSizeClass = rememberWindowSizeClass()
    val platform = AppTheme.platform
    
    LazyColumn {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimensions.cardSpacing)
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
                ) {
                    Text("Debug Info", style = AppTheme.materialTypography.titleLarge)
                    Text("Device Config: $deviceConfig")
                    Text("Width Class: ${windowSizeClass.widthSizeClass}")
                    Text("Height Class: ${windowSizeClass.heightSizeClass}")
                    Text("Platform: ${platform.name}")
                    Text("Recommended Columns: ${deviceConfig.getRecommendedColumns()}")
                    Text("Is Mobile: ${deviceConfig.isMobile()}")
                    Text("Is Tablet: ${deviceConfig.isTablet()}")
                    Text("Is Desktop: ${deviceConfig.isDesktop()}")
                }
            }
        }
    }
}
```

## ✅ Next Steps

Congratulations! You now have a fully responsive Compose Multiplatform app. Here's what to explore next:

1. **[Device Configuration](device-configuration.md)** - Learn advanced responsive patterns
2. **[Typography](typography.md)** - Master responsive text styling
3. **[Configuration](configuration.md)** - Themes, colors & responsive customization
4. **[Examples](examples.md)** - See real-world implementation patterns
5. **[API Reference](api-reference.md)** - Complete API documentation

## 🐛 Troubleshooting

### Common Issues

**Issue**: Cannot resolve Composive dependency
```kotlin
// ❌ Wrong - Missing JitPack repository
repositories {
    mavenCentral()
}

// ✅ Correct - Include JitPack repository
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

// ✅ Correct dependency
implementation("com.github.Gursimarsingh12.composive:composive-responsive-adaptive:1.0.0")
```

**Issue**: Layout doesn't change when resizing desktop window
```kotlin
// ✅ Solution: Make sure you're using responsive device configuration
@Composable
fun App() {
    ComposiveTheme { // ← Make sure this wraps your app
        MainScreen()
    }
}
```

**Issue**: Theme doesn't match platform expectations
```kotlin
// ✅ Solution: Use default platform-aware configuration
ComposiveTheme { // ← Uses platform defaults automatically
    // Android gets Material 3, iOS gets Cupertino
}

// Or explicitly set theme:
ComposiveTheme(
    configuration = responsiveConfiguration {
        withMaterialTheme() // Force Material 3
        // or withCupertinoTheme() // Force Cupertino
    }
) {
    MainScreen()
}
```

**Issue**: Typography not scaling properly
```kotlin
// ✅ Solution: Use AppTheme typography instead of hardcoded values
Text(
    text = "Responsive Text",
    style = AppTheme.materialTypography.bodyLarge // ← Scales automatically
)
```

### Getting Help

If you encounter issues:

1. **Check existing issues**: [GitHub Issues](https://github.com/Gursimarsingh12/Composive/issues)
2. **Create a new issue**: Include your `build.gradle.kts` files and error messages
4. **Contact directly**: anonymouslike083@gmail.com

## 🎉 You're Ready!

You now have all the tools to create beautiful, responsive UIs that work perfectly across mobile, tablet, and desktop platforms. The Composive library handles the complexity while giving you full control over the design.

Happy coding! 🚀 