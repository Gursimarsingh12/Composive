# Migration Guide 🔄

Guide for migrating between different versions of Composive.

## 📋 Version Compatibility

### Current Version: 1.0.0
This is the initial stable release of Composive. Future migration guides will be added as new versions are released.

## 🚀 From Preview/Beta to 1.0.0

If you were using early preview or beta versions, here's how to migrate to the stable 1.0.0 release:

### Package Changes

```kotlin
// ❌ Old (Preview)
import com.gursimar.composive.ResponsiveTheme
import com.gursimar.composive.rememberDeviceConfig

// ✅ New (1.0.0)
import com.gursimar.composive.responsive.theme.ComposiveTheme
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
```

### API Renames

#### Theme Setup

```kotlin
// ❌ Old
ResponsiveTheme {
    MainScreen()
}

// ✅ New
ComposiveTheme {
    MainScreen()
}
```

#### Device Configuration

```kotlin
// ❌ Old
val deviceConfig = rememberDeviceConfig()

// ✅ New
val deviceConfig = rememberDeviceConfiguration()
```

#### Configuration Builder

```kotlin
// ❌ Old
ResponsiveTheme(
    config = responsiveConfig {
        materialTheme()
    }
) {
    MainScreen()
}

// ✅ New
ComposiveTheme(
    configuration = responsiveConfiguration {
        withMaterialTheme()
    }
) {
    MainScreen()
}
```

### Updated Function Names

All configuration builder functions now use `with` prefix:

```kotlin
// ❌ Old
responsiveConfiguration {
    materialTheme()
    cupertinoTheme()
    customFonts(bodyFont = myFont)
}

// ✅ New
responsiveConfiguration {
    withMaterialTheme()
    withCupertinoTheme()
    withCustomMaterialFonts(bodyFont = myFont)
}
```

### Theme Access Changes

```kotlin
// ❌ Old
ResponsiveTheme.dimensions.cardPadding
ResponsiveTheme.typography.headlineLarge

// ✅ New
AppTheme.dimensions.cardPadding
AppTheme.materialTypography.headlineLarge
```

## 🔧 Step-by-Step Migration

### 1. Update Dependencies

```kotlin
// build.gradle.kts
dependencies {
    // ❌ Remove old dependency
    // implementation("com.gursimar.composive:composive:0.x.x")
    
    // ✅ Add new dependency
    implementation("io.github.gursimarsingh12.composive:composive-responsive-adaptive:1.0.0")
}
```

### 2. Update Imports

Replace all old imports with new package structure:

```kotlin
// ❌ Old imports
import com.gursimar.composive.ResponsiveTheme
import com.gursimar.composive.rememberDeviceConfig
import com.gursimar.composive.DeviceConfig

// ✅ New imports
import com.gursimar.composive.responsive.theme.ComposiveTheme
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
import com.gursimar.composive.responsive.core.DeviceConfiguration
import com.gursimar.composive.responsive.theme.AppTheme
import com.gursimar.composive.responsive.configuration.responsiveConfiguration
```

### 3. Update Theme Setup

```kotlin
// ❌ Old
@Composable
fun App() {
    ResponsiveWindowProvider {
        ResponsiveTheme(
            config = responsiveConfig {
                materialTheme()
            }
        ) {
            MainScreen()
        }
    }
}

// ✅ New
@Composable
fun App() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            withMaterialTheme()
        }
    ) {
        MainScreen()
    }
}
```

### 4. Update Device Configuration Usage

```kotlin
// ❌ Old
@Composable
fun ResponsiveLayout() {
    val deviceConfig = rememberDeviceConfig()
    
    when (deviceConfig) {
        DeviceConfig.MOBILE_PORTRAIT -> MobileLayout()
        DeviceConfig.DESKTOP -> DesktopLayout()
        else -> TabletLayout()
    }
}

// ✅ New
@Composable
fun ResponsiveLayout() {
    val deviceConfig = rememberDeviceConfiguration()
    
    when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> MobileLayout()
        DeviceConfiguration.DESKTOP -> DesktopLayout()
        else -> TabletLayout()
    }
}
```

### 5. Update Theme Value Access

```kotlin
// ❌ Old
@Composable
fun MyComponent() {
    Card(
        modifier = Modifier.padding(ResponsiveTheme.dimensions.cardSpacing)
    ) {
        Text(
            text = "Hello",
            style = ResponsiveTheme.typography.headlineLarge
        )
    }
}

// ✅ New
@Composable
fun MyComponent() {
    Card(
        modifier = Modifier.padding(AppTheme.dimensions.cardSpacing)
    ) {
        Text(
            text = "Hello",
            style = AppTheme.materialTypography.headlineLarge
        )
    }
}
```

### 6. Update Configuration

```kotlin
// ❌ Old
val config = responsiveConfig {
    materialTheme()
    customFonts(
        titleFont = myTitleFont,
        bodyFont = myBodyFont
    )
    customDimensions(
        mobile = myMobileDimensions
    )
}

// ✅ New
val config = responsiveConfiguration {
    withMaterialTheme()
    withCustomMaterialFonts(
        titleFont = myTitleFont,
        bodyFont = myBodyFont
    )
    withCustomDimensions(
        small = myMobileDimensions
    )
}
```

## 🆕 New Features in 1.0.0

### Enhanced Platform Detection

```kotlin
// New platform-aware configuration
val deviceConfig = rememberDeviceConfigurationWithPlatform()
val platform = AppTheme.platform

when {
    platform.isAndroid() -> AndroidSpecificCode()
    platform.isIOS() -> IOSSpecificCode()
    platform.isDesktop() -> DesktopSpecificCode()
}
```

### Separate Typography Access

```kotlin
// Access Material and Cupertino typography separately
Text(
    text = "Material style",
    style = AppTheme.materialTypography.headlineLarge
)

Text(
    text = "Cupertino style", 
    style = AppTheme.cupertinoTypography.largeTitle
)
```

### Enhanced Font Configuration

```kotlin
responsiveConfiguration {
    // New font configuration options
    withMaterialReadingDisplayFonts(
        readingFont = readingFont,
        displayFont = displayFont
    )
    
    withUniversalFont(brandFont) // Same font everywhere
}
```

### Improved Dimension System

```kotlin
// More comprehensive dimension system
AppTheme.dimensions.space1          // 4dp
AppTheme.dimensions.cardPadding     // Responsive card padding
AppTheme.dimensions.avatarMedium    // Responsive avatar size
AppTheme.dimensions.buttonHeightMedium // Responsive button height
```

## 🧪 Testing Your Migration

### 1. Compilation Check

```bash
./gradlew build
```

Fix any compilation errors by updating API usage.

### 2. Visual Verification

Run your app and verify:
- ✅ Layouts render correctly
- ✅ Typography scales properly  
- ✅ Platform themes work
- ✅ Hot reload functions

### 3. Feature Testing

Test key functionality:
- ✅ Window resizing on desktop
- ✅ Device configuration detection
- ✅ Theme switching
- ✅ Custom fonts (if used)

## 🐛 Common Migration Issues

### Issue 1: Compilation Errors

**Problem**: Old API references cause compilation errors

**Solution**: Update all imports and API calls

```kotlin
// ❌ This will cause compilation error
import com.gursimar.composive.ResponsiveTheme

// ✅ Use new import
import com.gursimar.composive.responsive.theme.ComposiveTheme
```

### Issue 2: Theme Values Not Found

**Problem**: `ResponsiveTheme.dimensions` not found

**Solution**: Use `AppTheme` instead

```kotlin
// ❌ Old
ResponsiveTheme.dimensions.cardPadding

// ✅ New  
AppTheme.dimensions.cardPadding
```

### Issue 3: Configuration Not Working

**Problem**: Old configuration syntax doesn't work

**Solution**: Update to new configuration builder

```kotlin
// ❌ Old
responsiveConfig {
    materialTheme()
}

// ✅ New
responsiveConfiguration {
    withMaterialTheme()
}
```

### Issue 4: Window Provider Missing

**Problem**: Layouts don't respond to screen size changes

**Solution**: Make sure you're using ComposiveTheme

```kotlin
// ❌ Old approach that may not work
SomeOtherTheme {
    ResponsiveTheme { ... }
}

// ✅ New - Direct usage
ComposiveTheme {
    MainScreen()
}
```

## 🔮 Future Migrations

### Version 1.x.x Updates

Future minor versions will maintain backward compatibility. Only additive changes are planned:
- New dimension values
- Additional configuration options  
- New helper functions
- Platform-specific enhancements

### Version 2.0.0 (Future)

Major version updates may include:
- Breaking API changes (with migration guide)
- New architecture improvements
- Enhanced platform support
- Performance optimizations

**Migration guides will be provided for all major version updates.**

## ✅ Migration Checklist

Use this checklist to ensure complete migration:

### Dependencies
- [ ] Updated to `composive-responsive-adaptive:1.0.0`
- [ ] Removed old dependency references
- [ ] Synced project successfully

### Imports  
- [ ] Updated `ResponsiveTheme` → `ComposiveTheme`
- [ ] Updated `rememberDeviceConfig` → `rememberDeviceConfiguration`
- [ ] Updated `DeviceConfig` → `DeviceConfiguration`
- [ ] Added `AppTheme` import

### Theme Setup
- [ ] Updated theme composable name
- [ ] Updated configuration builder syntax
- [ ] Updated window provider name

### API Usage
- [ ] Updated theme value access
- [ ] Updated device configuration usage
- [ ] Updated configuration options

### Testing
- [ ] Project compiles successfully
- [ ] App runs on all platforms
- [ ] Visual appearance matches expectations
- [ ] Hot reload works correctly

## 💬 Getting Help

If you encounter issues during migration:

1. **Check this guide** for common solutions
2. **Search GitHub issues** for similar problems  
3. **Open a new issue** with migration details
4. **Ask in discussions** for community help

Include this information when asking for help:
- Old version number
- New version number  
- Specific error messages
- Code samples showing the issue

---

**Successfully migrated to Composive 1.0.0!** 🎉  
*Enjoy the improved API and new features!* 