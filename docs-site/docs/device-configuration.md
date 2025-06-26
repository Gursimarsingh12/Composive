# Device Configuration Guide 📱

Learn how to use Composive's `DeviceConfiguration` API to create responsive layouts that adapt to different screen sizes and device types.

## 🎯 Overview

The `DeviceConfiguration` enum is the heart of Composive's responsive system. It automatically detects device types and screen sizes, providing intelligent layout decisions for your app.

## 📋 Device Types

### Available Configurations

```kotlin
enum class DeviceConfiguration {
    MOBILE_PORTRAIT,    // Phone in portrait (< 600dp width)
    MOBILE_LANDSCAPE,   // Phone in landscape (600-840dp width, compact height)
    TABLET_PORTRAIT,    // Tablet in portrait (600-840dp width, expanded height)
    TABLET_LANDSCAPE,   // Tablet in landscape (840-1200dp width)
    DESKTOP            // Desktop/large screens (> 1200dp width)
}
```

### Detection Logic

| Width (dp) | Height Class | Device Configuration |
|------------|--------------|---------------------|
| < 600 | Any | `MOBILE_PORTRAIT` |
| 600-840 | Compact | `MOBILE_LANDSCAPE` |
| 600-840 | Expanded | `TABLET_PORTRAIT` |
| 840-1200 | Any | `TABLET_LANDSCAPE` |
| > 1200 | Expanded | `DESKTOP` |

## 🚀 Basic Usage

### Getting Device Configuration

```kotlin
@Composable
fun ResponsiveScreen() {
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

### Helper Functions

```kotlin
@Composable
fun AdaptiveContent() {
    val deviceConfig = rememberDeviceConfiguration()
    
    // Check device categories
    when {
        deviceConfig.isMobile() -> {
            MobileLayout()
        }
        deviceConfig.isTablet() -> {
            TabletLayout()
        }
        deviceConfig.isDesktop() -> {
            DesktopLayout()
        }
    }
    
    // Check orientation
    if (deviceConfig.isPortrait()) {
        PortraitLayout()
    } else if (deviceConfig.isLandscape()) {
        LandscapeLayout()
    }
    
    // Get recommended columns
    val columns = deviceConfig.getRecommendedColumns()
    LazyVerticalGrid(columns = GridCells.Fixed(columns)) {
        // Grid content
    }
}
```

## 🎨 Layout Patterns

### Single Column Layout (Mobile Portrait)

```kotlin
@Composable
fun MobilePortraitLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.dimensions.screenPaddingHorizontal)
    ) {
        // Header
        TopAppBar(
            title = { Text("Mobile App") }
        )
        
        // Content
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
        ) {
            items(items) { item ->
                MobileCard(item)
            }
        }
        
        // Bottom navigation
        NavigationBar {
            tabs.forEach { tab ->
                NavigationBarItem(/* ... */)
            }
        }
    }
}
```

### Two Column Layout (Mobile Landscape / Tablet Portrait)

```kotlin
@Composable
fun TwoColumnLayout() {
    Row(modifier = Modifier.fillMaxSize()) {
        // Navigation rail
        NavigationRail(
            modifier = Modifier.fillMaxHeight()
        ) {
            tabs.forEach { tab ->
                NavigationRailItem(/* ... */)
            }
        }
        
        // Main content
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
            modifier = Modifier.weight(1f)
        ) {
            items(items) { item ->
                ResponsiveCard(item)
            }
        }
    }
}
```

### Multi-Column Layout (Desktop)

```kotlin
@Composable
fun DesktopLayout() {
    val drawerState = rememberDrawerState(DrawerValue.Open)
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerContent()
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Desktop App") },
                navigationIcon = {
                    IconButton(onClick = { /* toggle drawer */ }) {
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
                items(items) { item ->
                    DesktopCard(item)
                }
            }
        }
    }
}
```

## 🔧 Advanced Patterns

### Adaptive Navigation

```kotlin
@Composable
fun AdaptiveNavigation(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val deviceConfig = rememberDeviceConfiguration()
    
    when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(tab.label) },
                        selected = selectedTab == tab.id,
                        onClick = { onTabSelected(tab.id) }
                    )
                }
            }
        }
        
        DeviceConfiguration.MOBILE_LANDSCAPE,
        DeviceConfiguration.TABLET_PORTRAIT -> {
            NavigationRail {
                tabs.forEach { tab ->
                    NavigationRailItem(
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(tab.label) },
                        selected = selectedTab == tab.id,
                        onClick = { onTabSelected(tab.id) }
                    )
                }
            }
        }
        
        DeviceConfiguration.TABLET_LANDSCAPE,
        DeviceConfiguration.DESKTOP -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet {
                        tabs.forEach { tab ->
                            NavigationDrawerItem(
                                icon = { Icon(tab.icon, contentDescription = null) },
                                label = { Text(tab.label) },
                                selected = selectedTab == tab.id,
                                onClick = { onTabSelected(tab.id) }
                            )
                        }
                    }
                }
            ) {
                content()
            }
        }
    }
}
```

### Content Density Adaptation

```kotlin
@Composable
fun AdaptiveContentDensity() {
    val deviceConfig = rememberDeviceConfiguration()
    
    val itemsPerRow = deviceConfig.getRecommendedColumns()
    val itemSpacing = when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> AppTheme.dimensions.space2
        DeviceConfiguration.MOBILE_LANDSCAPE -> AppTheme.dimensions.space3
        DeviceConfiguration.TABLET_PORTRAIT -> AppTheme.dimensions.space4
        DeviceConfiguration.TABLET_LANDSCAPE -> AppTheme.dimensions.space5
        DeviceConfiguration.DESKTOP -> AppTheme.dimensions.space6
    }
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(itemsPerRow),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing),
        verticalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(items) { item ->
            AdaptiveItem(item, deviceConfig)
        }
    }
}

@Composable
fun AdaptiveItem(
    item: Item,
    deviceConfig: DeviceConfiguration
) {
    val showDetailedInfo = when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> false
        DeviceConfiguration.MOBILE_LANDSCAPE -> true
        else -> true
    }
    
    Card {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = item.title,
                style = AppTheme.materialTypography.titleMedium
            )
            
            if (showDetailedInfo) {
                Text(
                    text = item.description,
                    style = AppTheme.materialTypography.bodyMedium
                )
            }
        }
    }
}
```

## 🔄 Orientation Handling

```kotlin
@Composable
fun OrientationAwareLayout() {
    val deviceConfig = rememberDeviceConfiguration()
    val orientation = AppTheme.orientation
    
    when {
        deviceConfig.isMobile() && orientation == Orientation.Portrait -> {
            VerticalLayout()
        }
        deviceConfig.isMobile() && orientation == Orientation.Landscape -> {
            HorizontalLayout()
        }
        deviceConfig.isTablet() -> {
            TabletOptimizedLayout()
        }
        else -> {
            DesktopLayout()
        }
    }
}
```

## 🧪 Testing & Debugging

### Debug Information Component

```kotlin
@Composable
fun DeviceConfigDebug() {
    val deviceConfig = rememberDeviceConfiguration()
    val windowSizeClass = rememberWindowSizeClass()
    val platform = AppTheme.platform
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.cardSpacing)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                "Device Configuration Debug",
                style = AppTheme.materialTypography.titleLarge
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
            
            Text("Device Config: $deviceConfig")
            Text("Width Class: ${windowSizeClass.widthSizeClass}")
            Text("Height Class: ${windowSizeClass.heightSizeClass}")
            Text("Platform: ${platform.name}")
            Text("Is Mobile: ${deviceConfig.isMobile()}")
            Text("Is Tablet: ${deviceConfig.isTablet()}")
            Text("Is Desktop: ${deviceConfig.isDesktop()}")
            Text("Is Portrait: ${deviceConfig.isPortrait()}")
            Text("Is Landscape: ${deviceConfig.isLandscape()}")
            Text("Recommended Columns: ${deviceConfig.getRecommendedColumns()}")
        }
    }
}
```

### Hot Reload Testing

Test your layouts by resizing the desktop window:

```bash
./gradlew desktopRunHot --mainClass com.example.yourApp.MainKt --stacktrace --auto
```

| Window Size | Expected Configuration |
|-------------|----------------------|
| 400×800 | `MOBILE_PORTRAIT` |
| 700×400 | `MOBILE_LANDSCAPE` |
| 700×900 | `TABLET_PORTRAIT` |
| 1000×600 | `TABLET_LANDSCAPE` |
| 1400×900 | `DESKTOP` |

## 🎯 Best Practices

### 1. Use Helper Functions

```kotlin
// ✅ Good - Use helper functions for readability
when {
    deviceConfig.isMobile() -> MobileLayout()
    deviceConfig.isTablet() -> TabletLayout()
    deviceConfig.isDesktop() -> DesktopLayout()
}

// ❌ Avoid - Checking each enum value
when (deviceConfig) {
    DeviceConfiguration.MOBILE_PORTRAIT,
    DeviceConfiguration.MOBILE_LANDSCAPE -> MobileLayout()
    // ... rest
}
```

### 2. Recommended Columns

```kotlin
// ✅ Good - Use recommended columns
val columns = deviceConfig.getRecommendedColumns()

// ❌ Avoid - Hardcoded values
val columns = when (deviceConfig) {
    DeviceConfiguration.MOBILE_PORTRAIT -> 1
    // ... manual mapping
}
```

### 3. Combine with Platform Detection

```kotlin
@Composable
fun PlatformAwareLayout() {
    val deviceConfig = rememberDeviceConfiguration()
    val platform = AppTheme.platform
    
    when {
        platform.isIOS() && deviceConfig.isMobile() -> {
            IOSMobileLayout()
        }
        platform.isAndroid() && deviceConfig.isTablet() -> {
            AndroidTabletLayout()
        }
        platform.isDesktop() -> {
            DesktopLayout()
        }
        else -> {
            DefaultLayout()
        }
    }
}
```

## 🔗 Related APIs

- **[Configuration](configuration.md)** - Theme & responsive customization
- **[Typography](typography.md)** - Responsive text scaling
- **[Responsive Dimensions](responsive-dimensions.md)** - Adaptive spacing
- **[API Reference](api-reference.md)** - Complete device configuration API

---

**Master responsive layouts with DeviceConfiguration!** 📱➡️🖥️ 