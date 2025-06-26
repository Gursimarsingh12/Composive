# API Reference 📚

Complete reference documentation for all Composive APIs, functions, and classes.

## 🎨 Core Theme APIs

### ComposiveTheme

The main theme composable that provides responsive design capabilities.

```kotlin
@Composable
fun ComposiveTheme(
    configuration: ResponsiveConfiguration = ResponsiveConfiguration(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeType: Theme? = null,
    content: @Composable () -> Unit
)
```

**Parameters:**
- `configuration` - Custom responsive configuration (optional)
- `darkTheme` - Whether to use dark theme (defaults to system preference)
- `themeType` - Force specific theme type (overrides platform defaults)
- `content` - App content that will use responsive theming

**Example:**
```kotlin
ComposiveTheme(
    configuration = responsiveConfiguration {
        withMaterialTheme()
    }
) {
    MainScreen()
}
```

### AppTheme

Object providing access to current responsive theme values.

```kotlin
object AppTheme {
    val dimensions: Dimensions @Composable get()
    val orientation: Orientation @Composable get()
    val fontWeights: ResponsiveFontWeights @Composable get()
    val materialTypography: androidx.compose.material3.Typography @Composable get()
    val cupertinoTypography: com.slapps.cupertino.theme.Typography @Composable get()
    val materialColors: MaterialColorScheme @Composable get()
    val cupertinoColors: ColorScheme @Composable get()
    val configuration: ResponsiveConfiguration @Composable get()
    val platform: Platform @Composable get()
}
```

#### Complete AppTheme Properties Reference

| Property | Type | Description | Usage Example |
|----------|------|-------------|---------------|
| **dimensions** | `Dimensions` | Responsive spacing, sizes, and layout dimensions | `AppTheme.dimensions.cardPadding` |
| **orientation** | `Orientation` | Current device orientation (Portrait/Landscape) | `AppTheme.orientation == Orientation.Portrait` |
| **fontWeights** | `ResponsiveFontWeights` | Responsive font weight system | `AppTheme.fontWeights.semiBold` |
| **materialTypography** | `androidx.compose.material3.Typography` | Material Design typography scales | `AppTheme.materialTypography.headlineLarge` |
| **cupertinoTypography** | `com.slapps.cupertino.theme.Typography` | iOS Cupertino typography scales | `AppTheme.cupertinoTypography.largeTitle` |
| **materialColors** | `MaterialColorScheme` | Material Design color scheme | `AppTheme.materialColors.primary` |
| **cupertinoColors** | `ColorScheme` | Cupertino color scheme | `AppTheme.cupertinoColors.accent` |
| **configuration** | `ResponsiveConfiguration` | Current responsive configuration | `AppTheme.configuration.defaultThemeType` |
| **platform** | `Platform` | Platform information and capabilities | `AppTheme.platform.isAndroid()` |

#### Detailed Property Breakdown

##### 📏 Dimensions
```kotlin
AppTheme.dimensions.space1                    // 4dp - Minimal spacing
AppTheme.dimensions.space4                    // 16dp - Standard spacing
AppTheme.dimensions.cardPadding               // 12-32dp - Card internal padding
AppTheme.dimensions.screenPaddingHorizontal   // 16-48dp - Screen edge margins
AppTheme.dimensions.iconMedium               // 28-36dp - Standard icons
AppTheme.dimensions.avatarMedium             // 40-56dp - User avatars
AppTheme.dimensions.buttonHeightMedium       // 44-56dp - Standard buttons
```

##### ⚖️ Font Weights
```kotlin
AppTheme.fontWeights.light        // FontWeight.Light
AppTheme.fontWeights.normal       // FontWeight.Normal
AppTheme.fontWeights.medium       // FontWeight.Medium
AppTheme.fontWeights.semiBold     // FontWeight.SemiBold
AppTheme.fontWeights.bold         // FontWeight.Bold
AppTheme.fontWeights.extraBold    // FontWeight.ExtraBold
```

##### 📝 Material Typography
```kotlin
AppTheme.materialTypography.displayLarge      // 23-39sp - Largest display text
AppTheme.materialTypography.headlineLarge     // 30-50sp - Major headlines
AppTheme.materialTypography.titleLarge        // 20-36sp - Section titles
AppTheme.materialTypography.bodyLarge         // 16-29sp - Main body text
AppTheme.materialTypography.labelMedium       // 12-20sp - UI labels
```

##### 🍎 Cupertino Typography
```kotlin
AppTheme.cupertinoTypography.largeTitle       // 30-50sp - iOS large titles
AppTheme.cupertinoTypography.title1           // 26-42sp - Primary titles
AppTheme.cupertinoTypography.headline         // 15-25sp - Emphasized text
AppTheme.cupertinoTypography.body             // 16-24sp - Body content
AppTheme.cupertinoTypography.caption1         // 11-16sp - Small text
```

##### 🎨 Material Colors
```kotlin
AppTheme.materialColors.primary               // Primary brand color
AppTheme.materialColors.secondary             // Secondary accent color
AppTheme.materialColors.background            // Background color
AppTheme.materialColors.surface               // Surface color
AppTheme.materialColors.onPrimary             // Text on primary
```

##### 🎨 Cupertino Colors
```kotlin
AppTheme.cupertinoColors.accent               // iOS accent color
AppTheme.cupertinoColors.background           // Background color
AppTheme.cupertinoColors.systemBackground     // System background
AppTheme.cupertinoColors.label                // Label color
```

##### 📱 Platform Information
```kotlin
AppTheme.platform.name                        // "Android", "iOS", etc.
AppTheme.platform.type                        // PlatformType enum
AppTheme.platform.isAndroid()                 // Boolean
AppTheme.platform.isIOS()                     // Boolean
AppTheme.platform.isDesktop()                 // Boolean
AppTheme.platform.preferredTheme              // Theme preference
```

##### ⚙️ Configuration
```kotlin
AppTheme.configuration.defaultThemeType       // Current theme type
AppTheme.configuration.customDimensions       // Custom dimension overrides
AppTheme.configuration.customMaterialFonts    // Custom Material fonts
AppTheme.configuration.customCupertinoFonts   // Custom Cupertino fonts
```

##### 📐 Orientation
```kotlin
AppTheme.orientation                          // Orientation.Portrait or Orientation.Landscape
```

**Usage:**
```kotlin
@Composable
fun MyComponent() {
    Card(
        modifier = Modifier.padding(AppTheme.dimensions.cardSpacing),
        elevation = CardDefaults.cardElevation(AppTheme.dimensions.cardElevation)
    ) {
        Text(
            text = "Responsive Text",
            style = AppTheme.materialTypography.headlineSmall,
            fontWeight = AppTheme.fontWeights.semiBold
        )
    }
}
```

## 📱 Device Configuration APIs

### DeviceConfiguration

Enum representing different device configurations based on screen size.

```kotlin
enum class DeviceConfiguration {
    MOBILE_PORTRAIT,    // < 600dp width
    MOBILE_LANDSCAPE,   // 600-840dp width, compact height
    TABLET_PORTRAIT,    // 600-840dp width, expanded height
    TABLET_LANDSCAPE,   // 840-1200dp width
    DESKTOP            // > 1200dp width
}
```

**Helper Functions:**
```kotlin
fun DeviceConfiguration.isMobile(): Boolean
fun DeviceConfiguration.isTablet(): Boolean
fun DeviceConfiguration.isDesktop(): Boolean
fun DeviceConfiguration.isPortrait(): Boolean
fun DeviceConfiguration.isLandscape(): Boolean
fun DeviceConfiguration.getRecommendedColumns(): Int
```

**Static Methods:**
```kotlin
companion object {
    fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration
    fun fromPlatformAndWindowSize(platform: Platform, windowSizeClass: WindowSizeClass): DeviceConfiguration
}
```

### rememberDeviceConfiguration

Composable function to get current device configuration.

```kotlin
@Composable
fun rememberDeviceConfiguration(): DeviceConfiguration
```

**Usage:**
```kotlin
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

### rememberDeviceConfigurationWithPlatform

Enhanced device configuration that considers platform information.

```kotlin
@Composable
fun rememberDeviceConfigurationWithPlatform(): DeviceConfiguration
```

## 🔧 Configuration APIs

### responsiveConfiguration

DSL function for building responsive configurations.

```kotlin
fun responsiveConfiguration(
    builder: ResponsiveConfigurationBuilder.() -> Unit
): ResponsiveConfiguration
```

**Usage:**
```kotlin
val config = responsiveConfiguration {
    withMaterialTheme()
    withCustomMaterialFonts(
        bodyFont = myFont
    )
}
```

### ResponsiveConfigurationBuilder

Builder class for creating custom responsive configurations.

#### Theme Configuration

```kotlin
fun withMaterialTheme(): ResponsiveConfigurationBuilder
fun withCupertinoTheme(): ResponsiveConfigurationBuilder
fun withPlatformDefaultTheme(): ResponsiveConfigurationBuilder
fun withPlatformThemeAdaptation(enabled: Boolean = true): ResponsiveConfigurationBuilder
```

#### Font Configuration

```kotlin
fun withCustomMaterialFonts(
    displayFont: FontFamily? = null,
    headlineFont: FontFamily? = null,
    titleFont: FontFamily? = null,
    bodyFont: FontFamily? = null,
    labelFont: FontFamily? = null
): ResponsiveConfigurationBuilder

fun withCustomCupertinoFonts(
    largeTitleFont: FontFamily? = null,
    titleFont: FontFamily? = null,
    headlineFont: FontFamily? = null,
    bodyFont: FontFamily? = null,
    captionFont: FontFamily? = null
): ResponsiveConfigurationBuilder

fun withUniformMaterialFont(fontFamily: FontFamily): ResponsiveConfigurationBuilder
fun withUniformCupertinoFont(fontFamily: FontFamily): ResponsiveConfigurationBuilder
fun withUniversalFont(fontFamily: FontFamily): ResponsiveConfigurationBuilder

fun withMaterialReadingDisplayFonts(
    readingFont: FontFamily,
    displayFont: FontFamily,
    labelFont: FontFamily = readingFont
): ResponsiveConfigurationBuilder

fun withCupertinoReadingDisplayFonts(
    readingFont: FontFamily,
    displayFont: FontFamily,
    captionFont: FontFamily = readingFont
): ResponsiveConfigurationBuilder
```

#### Dimension Configuration

```kotlin
fun withCustomDimensions(
    small: Dimensions = smallDimensions,
    compact: Dimensions = compactDimensions,
    medium: Dimensions = mediumDimensions,
    large: Dimensions = largeDimensions
): ResponsiveConfigurationBuilder
```

#### Typography Configuration

```kotlin
fun withCustomMaterialTypography(
    small: androidx.compose.material3.Typography,
    compact: androidx.compose.material3.Typography,
    medium: androidx.compose.material3.Typography,
    large: androidx.compose.material3.Typography
): ResponsiveConfigurationBuilder

fun withCustomCupertinoTypography(
    small: com.slapps.cupertino.theme.Typography,
    compact: com.slapps.cupertino.theme.Typography,
    medium: com.slapps.cupertino.theme.Typography,
    large: com.slapps.cupertino.theme.Typography
): ResponsiveConfigurationBuilder
```

#### Color Configuration

```kotlin
fun withCustomMaterialColors(
    light: MaterialColorScheme,
    dark: MaterialColorScheme
): ResponsiveConfigurationBuilder

fun withCustomCupertinoColors(
    light: CupertinoColorScheme,
    dark: CupertinoColorScheme
): ResponsiveConfigurationBuilder
```

#### Font Weight Configuration

```kotlin
fun withCustomFontWeights(
    small: ResponsiveFontWeights = smallFontWeights,
    compact: ResponsiveFontWeights = compactFontWeights,
    medium: ResponsiveFontWeights = mediumFontWeights,
    large: ResponsiveFontWeights = largeFontWeights
): ResponsiveConfigurationBuilder
```

#### Builder Methods

```kotlin
fun build(): ResponsiveConfiguration
```

## 📏 Dimension APIs

### Dimensions

Data class containing all responsive dimension values.

```kotlin
data class Dimensions(
    // Spacing
    val space1: Dp,     // 4dp
    val space2: Dp,     // 8dp
    val space3: Dp,     // 12dp
    val space4: Dp,     // 16dp
    val space5: Dp,     // 20dp
    val space6: Dp,     // 24dp
    val space8: Dp,     // 32dp
    val space10: Dp,    // 40dp
    val space12: Dp,    // 48dp
    val space16: Dp,    // 64dp
    
    // Content Padding
    val contentPaddingSmall: Dp,
    val contentPaddingMedium: Dp,
    val contentPaddingLarge: Dp,
    
    // Screen Padding
    val screenPaddingHorizontal: Dp,
    val screenPaddingVertical: Dp,
    
    // Layout Spacing
    val sectionSpacing: Dp,
    val itemSpacing: Dp,
    
    // Icons
    val iconTiny: Dp,
    val iconSmall: Dp,
    val iconMedium: Dp,
    val iconLarge: Dp,
    
    // Avatars
    val avatarSmall: Dp,
    val avatarMedium: Dp,
    val avatarLarge: Dp,
    val avatarXLarge: Dp,
    
    // Images
    val imageThumb: Dp,
    val imageSmall: Dp,
    val imageMedium: Dp,
    val imageLarge: Dp,
    val imageHero: Dp,
    
    // Buttons
    val buttonHeightSmall: Dp,
    val buttonHeightMedium: Dp,
    val buttonHeightLarge: Dp,
    val buttonMinWidth: Dp,
    
    // Inputs
    val inputHeight: Dp,
    val inputMinWidth: Dp,
    
    // Cards
    val cardPadding: Dp,
    val cardSpacing: Dp,
    val cardElevation: Dp,
    
    // Components
    val bottomSheetPeekHeight: Dp,
    val dialogMaxWidth: Dp,
    val dialogPadding: Dp
)
```

### Predefined Dimensions

```kotlin
val smallDimensions: Dimensions      // Mobile portrait
val compactDimensions: Dimensions    // Mobile landscape
val mediumDimensions: Dimensions     // Tablets
val largeDimensions: Dimensions      // Desktop
```

## ⚖️ Font Weight APIs

### ResponsiveFontWeights

Data class containing responsive font weight values.

```kotlin
data class ResponsiveFontWeights(
    // Basic font weights
    val light: FontWeight,
    val normal: FontWeight,
    val medium: FontWeight,
    val semiBold: FontWeight,
    val bold: FontWeight,
    val extraBold: FontWeight,

    // Semantic font weights
    val body: FontWeight,
    val emphasis: FontWeight,
    val heading: FontWeight,
    val display: FontWeight,
    val button: FontWeight,
    val caption: FontWeight
)
```

#### Basic Font Weights
```kotlin
AppTheme.fontWeights.light        // FontWeight.Light
AppTheme.fontWeights.normal       // FontWeight.Normal
AppTheme.fontWeights.medium       // FontWeight.Medium
AppTheme.fontWeights.semiBold     // FontWeight.SemiBold
AppTheme.fontWeights.bold         // FontWeight.Bold
AppTheme.fontWeights.extraBold    // FontWeight.ExtraBold
```

#### Semantic Font Weights
```kotlin
AppTheme.fontWeights.body         // Body text weight
AppTheme.fontWeights.emphasis     // Emphasized text weight
AppTheme.fontWeights.heading      // Heading text weight
AppTheme.fontWeights.display      // Display text weight
AppTheme.fontWeights.button       // Button text weight
AppTheme.fontWeights.caption      // Caption text weight
```

**Usage Examples:**
```kotlin
// Using basic weights
Text(
    text = "Title",
    style = AppTheme.materialTypography.titleLarge,
    fontWeight = AppTheme.fontWeights.semiBold
)

// Using semantic weights
Text(
    text = "Emphasized content",
    style = AppTheme.materialTypography.bodyLarge,
    fontWeight = AppTheme.fontWeights.emphasis
)

Button(onClick = {}) {
    Text(
        text = "Action",
        fontWeight = AppTheme.fontWeights.button
    )
}
```

## 🔤 Typography APIs

### MaterialFontResources

Font family configuration for Material Design typography.

```kotlin
data class MaterialFontResources(
    val displayFont: FontFamily? = null,
    val headlineFont: FontFamily? = null,
    val titleFont: FontFamily? = null,
    val bodyFont: FontFamily? = null,
    val labelFont: FontFamily? = null
)
```

### CupertinoFontResources

Font family configuration for Cupertino typography.

```kotlin
data class CupertinoFontResources(
    val largeTitleFont: FontFamily? = null,
    val titleFont: FontFamily? = null,
    val headlineFont: FontFamily? = null,
    val bodyFont: FontFamily? = null,
    val captionFont: FontFamily? = null
)
```

### Typography Factory Functions

```kotlin
// Material Typography
@Composable
fun materialTypographySmall(fontResources: MaterialFontResources? = null): androidx.compose.material3.Typography

@Composable
fun materialTypographyCompact(fontResources: MaterialFontResources? = null): androidx.compose.material3.Typography

@Composable
fun materialTypographyMedium(fontResources: MaterialFontResources? = null): androidx.compose.material3.Typography

@Composable
fun materialTypographyBig(fontResources: MaterialFontResources? = null): androidx.compose.material3.Typography

// Cupertino Typography
@Composable
fun cupertinoTypographySmall(fontResources: CupertinoFontResources? = null): com.slapps.cupertino.theme.Typography

@Composable
fun cupertinoTypographyCompact(fontResources: CupertinoFontResources? = null): com.slapps.cupertino.theme.Typography

@Composable
fun cupertinoTypographyMedium(fontResources: CupertinoFontResources? = null): com.slapps.cupertino.theme.Typography

@Composable
fun cupertinoTypographyBig(fontResources: CupertinoFontResources? = null): com.slapps.cupertino.theme.Typography
```

## 🖥️ Platform APIs

### Platform

Interface providing platform-specific information.

```kotlin
interface Platform {
    val name: String
    val type: PlatformType
    val supportsDarkModeDetection: Boolean
    val preferredTheme: Theme
    val hasSystemBackButton: Boolean
    val supportsEdgeToEdge: Boolean
    val densityScale: Float
    val prefersCompactLayouts: Boolean
}
```

### PlatformType

Enum representing different platform types.

```kotlin
enum class PlatformType {
    ANDROID,
    IOS,
    DESKTOP,
    WEB
}
```

### Platform Functions

```kotlin
expect fun getCurrentPlatform(): Platform

fun Platform.isMobile(): Boolean
fun Platform.isDesktop(): Boolean
fun Platform.isIOS(): Boolean
fun Platform.isAndroid(): Boolean
fun Platform.isWeb(): Boolean
fun Platform.getSpacingMultiplier(): Float
fun Platform.getMinTouchTargetSize(): Float
fun Platform.getRecommendedTheme(): Theme
fun Platform.shouldUseMaterial3(): Boolean
fun Platform.shouldUseCupertino(): Boolean
```

## 🎨 Window Size APIs

The window size detection is now handled internally by ComposiveTheme. You don't need to wrap your app with any additional providers.

### Window Size Functions

```kotlin
@Composable
fun rememberWindowSizeClass(): WindowSizeClass
```

This function is available for advanced use cases where you need direct access to window size classes, but most users should rely on `rememberDeviceConfiguration()` instead.

## 🎨 Configuration Data Classes

### ResponsiveConfiguration

Main configuration class for customizing responsive behavior.

```kotlin
@Immutable
data class ResponsiveConfiguration(
    val customDimensions: ResponsiveDimensions? = null,
    val customFontWeights: ResponsiveFontWeightConfiguration? = null,
    val customMaterialFontResources: MaterialFontResources? = null,
    val customCupertinoFontResources: CupertinoFontResources? = null,
    val customCupertinoTypography: ResponsiveCupertinoTypography? = null,
    val customMaterialTypography: ResponsiveMaterialTypography? = null,
    val customMaterialColors: ResponsiveMaterialColors? = null,
    val customCupertinoColors: ResponsiveCupertinoColors? = null,
    val defaultThemeType: Theme = getDefaultThemeForPlatform(),
    val enablePlatformThemePreference: Boolean = false
)
```

### Responsive Data Classes

```kotlin
@Immutable
data class ResponsiveDimensions(
    val small: Dimensions = smallDimensions,
    val compact: Dimensions = compactDimensions,
    val medium: Dimensions = mediumDimensions,
    val large: Dimensions = largeDimensions
)

@Immutable
data class ResponsiveFontWeightConfiguration(
    val small: ResponsiveFontWeights = smallFontWeights,
    val compact: ResponsiveFontWeights = compactFontWeights,
    val medium: ResponsiveFontWeights = mediumFontWeights,
    val large: ResponsiveFontWeights = largeFontWeights
)

@Immutable
data class ResponsiveCupertinoTypography(
    val small: com.slapps.cupertino.theme.Typography,
    val compact: com.slapps.cupertino.theme.Typography,
    val medium: com.slapps.cupertino.theme.Typography,
    val large: com.slapps.cupertino.theme.Typography
)

@Immutable
data class ResponsiveMaterialTypography(
    val small: androidx.compose.material3.Typography,
    val compact: androidx.compose.material3.Typography,
    val medium: androidx.compose.material3.Typography,
    val large: androidx.compose.material3.Typography
)

@Immutable
data class ResponsiveMaterialColors(
    val light: MaterialColorScheme,
    val dark: MaterialColorScheme
)

@Immutable
data class ResponsiveCupertinoColors(
    val light: CupertinoColorScheme,
    val dark: CupertinoColorScheme
)
```

## 🔄 Orientation API

### Orientation

Enum representing device orientation.

```kotlin
enum class Orientation {
    Portrait,
    Landscape
}
```

**Access via:**
```kotlin
val orientation = AppTheme.orientation
```

## 🧪 Helper Functions

### Configuration Helpers

```kotlin
fun getDefaultThemeForPlatform(): Theme
fun defaultResponsiveConfiguration(): ResponsiveConfiguration
fun materialResponsiveConfiguration(): ResponsiveConfiguration
fun cupertinoResponsiveConfiguration(): ResponsiveConfiguration
fun platformAdaptiveResponsiveConfiguration(): ResponsiveConfiguration
```

### Font Helpers

```kotlin
fun materialUniformFonts(fontFamily: FontFamily): MaterialFontResources
fun cupertinoUniformFonts(fontFamily: FontFamily): CupertinoFontResources
fun materialFonts(reading: FontFamily, display: FontFamily, label: FontFamily): MaterialFontResources
fun cupertinoFonts(reading: FontFamily, display: FontFamily, caption: FontFamily): CupertinoFontResources
```

---

**Complete API reference for building responsive Compose Multiplatform apps!** 📚