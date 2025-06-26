# Configuration Guide ⚙️

Learn how to customize Composive's responsive behavior using the `responsiveConfiguration` builder and configuration options.

## 🎯 Overview

Composive provides extensive customization through the `responsiveConfiguration` DSL. You can customize themes, fonts, dimensions, and more while maintaining responsive behavior.

## 🚀 Basic Configuration

### Default Platform-Aware Setup

```kotlin
@Composable
fun App() {
    ComposiveTheme {
        // Uses platform defaults:
        // Android → Material 3
        // iOS/Desktop → Cupertino
        MainScreen()
    }
}
```

### Custom Configuration

```kotlin
@Composable
fun App() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            // Your customizations here
        }
    ) {
        MainScreen()
    }
}
```

## 🎨 Theme Configuration

### Force Specific Theme

```kotlin
responsiveConfiguration {
    // Force Material 3 on all platforms
    withMaterialTheme()
}

responsiveConfiguration {
    // Force Cupertino on all platforms
    withCupertinoTheme()
}

responsiveConfiguration {
    // Use platform defaults explicitly
    withPlatformDefaultTheme()
}
```

### Enhanced Platform Adaptation

```kotlin
responsiveConfiguration {
    // Enable enhanced platform-specific behavior
    withPlatformThemeAdaptation(enabled = true)
}
```

## 🔤 Font Configuration

### Material Design Fonts

```kotlin
import androidx.compose.ui.text.font.FontFamily

responsiveConfiguration {
    withCustomMaterialFonts(
        displayFont = FontFamily.Serif,          // Headlines, display text
        headlineFont = FontFamily.SansSerif,     // Section headlines
        titleFont = FontFamily.Default,          // Titles
        bodyFont = FontFamily.Monospace,         // Body text
        labelFont = FontFamily.Cursive           // Labels, captions
    )
}
```

### Cupertino Fonts

```kotlin
import androidx.compose.ui.text.font.FontFamily

responsiveConfiguration {
    withCustomCupertinoFonts(
        largeTitleFont = myBrandFont,        // Large titles
        titleFont = myHeadingFont,           // Regular titles
        headlineFont = myDisplayFont,        // Headlines
        bodyFont = myReadingFont,            // Body text
        captionFont = myCaptionFont          // Captions
    )
}
```

### Universal Font Setup

```kotlin
import androidx.compose.ui.text.font.FontFamily

responsiveConfiguration {
    // Same font for all themes and styles
    withUniversalFont(FontFamily.SansSerif)
}

responsiveConfiguration {
    // Same font for all Material styles
    withUniformMaterialFont(FontFamily.Serif)
}

responsiveConfiguration {
    // Same font for all Cupertino styles
    withUniformCupertinoFont(FontFamily.Default)
}
```

### Reading & Display Font Pattern

```kotlin
import androidx.compose.ui.text.font.FontFamily

responsiveConfiguration {
    // Material: Different fonts for reading vs display
    withMaterialReadingDisplayFonts(
        readingFont = FontFamily.SansSerif,     // Body, titles
        displayFont = FontFamily.Serif,         // Headlines, display
        labelFont = FontFamily.Default          // Labels
    )
    
    // Cupertino: Different fonts for reading vs display
    withCupertinoReadingDisplayFonts(
        readingFont = FontFamily.Default,       // Body, regular titles
        displayFont = FontFamily.Serif,         // Large titles, headlines
        captionFont = FontFamily.SansSerif      // Captions
    )
}
```

## 📏 Dimensions Configuration

### Custom Dimensions for Screen Sizes

```kotlin
import androidx.compose.ui.unit.dp
import com.gursimar.composive.responsive.foundation.Dimensions

responsiveConfiguration {
    withCustomDimensions(
        small = customMobileDimensions,      // Mobile portrait
        compact = customLandscapeDimensions, // Mobile landscape
        medium = customTabletDimensions,     // Tablet
        large = customDesktopDimensions      // Desktop
    )
}

private val customMobileDimensions = Dimensions(
    space1 = 4.dp,
    space2 = 8.dp,
    space4 = 16.dp,
    space8 = 32.dp,
    cardPadding = 12.dp,
    screenPaddingHorizontal = 16.dp,
    iconSmall = 20.dp,
    iconMedium = 28.dp,
    buttonHeightMedium = 44.dp,
    // ... other dimensions
)
```

### Partial Dimension Overrides

```kotlin
import com.gursimar.composive.responsive.foundation.smallDimensions
import com.gursimar.composive.responsive.foundation.largeDimensions
import androidx.compose.ui.unit.dp

responsiveConfiguration {
    withCustomDimensions(
        // Only override specific screen sizes
        small = smallDimensions.copy(
            cardPadding = smallDimensions.cardPadding + 8.dp,
            screenPaddingHorizontal = smallDimensions.screenPaddingHorizontal + 8.dp
        ),
        large = largeDimensions.copy(
            cardPadding = largeDimensions.cardPadding + 8.dp,
            buttonHeightMedium = largeDimensions.buttonHeightMedium + 4.dp
        )
        // medium and compact use defaults
    )
}
```

## ⚖️ Font Weight Configuration

```kotlin
import androidx.compose.ui.text.font.FontWeight
import com.gursimar.composive.responsive.foundation.ResponsiveFontWeights

responsiveConfiguration {
    withCustomFontWeights(
        small = ResponsiveFontWeights(
            // Basic weights
            light = FontWeight.Light,
            normal = FontWeight.Normal,
            medium = FontWeight.Medium,
            semiBold = FontWeight.SemiBold,
            bold = FontWeight.Bold,
            extraBold = FontWeight.ExtraBold,
            
            // Semantic weights for mobile
            body = FontWeight.Normal,
            emphasis = FontWeight.SemiBold,  // More emphasis on small screens
            heading = FontWeight.SemiBold,
            display = FontWeight.Bold,
            button = FontWeight.Medium,
            caption = FontWeight.Light
        ),
        large = ResponsiveFontWeights(
            // Basic weights
            light = FontWeight.ExtraLight,
            normal = FontWeight.Light,
            medium = FontWeight.Normal,
            semiBold = FontWeight.Medium,
            bold = FontWeight.SemiBold,
            extraBold = FontWeight.Black,     // Stronger display weight for large screens
            
            // Semantic weights for desktop
            body = FontWeight.Light,
            emphasis = FontWeight.Normal,
            heading = FontWeight.Medium,
            display = FontWeight.SemiBold,
            button = FontWeight.Normal,
            caption = FontWeight.ExtraLight
        )
    )
}
```

## 🎨 Color Configuration

### Material Colors

```kotlin
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

responsiveConfiguration {
    withCustomMaterialColors(
        light = lightColorScheme(
            primary = Color(0xFF6750A4),
            secondary = Color(0xFF625B71),
            background = Color(0xFFFFFBFE)
        ),
        dark = darkColorScheme(
            primary = Color(0xFFD0BCFF),
            secondary = Color(0xFFCCC2DC),
            background = Color(0xFF1C1B1F)
        )
    )
}
```

### Cupertino Colors

```kotlin
import com.slapps.cupertino.theme.lightColorScheme
import com.slapps.cupertino.theme.darkColorScheme
import androidx.compose.ui.graphics.Color

responsiveConfiguration {
    withCustomCupertinoColors(
        light = lightColorScheme(
            accent = Color(0xFF007AFF),
            systemBackground = Color.White
        ),
        dark = darkColorScheme(
            accent = Color(0xFF0A84FF),
            systemBackground = Color.Black
        )
    )
}
```

## 📝 Typography Configuration

### Custom Material Typography

```kotlin
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

responsiveConfiguration {
    withCustomMaterialTypography(
        small = Typography(
            headlineLarge = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            ),
            bodyLarge = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ),
        large = Typography(
            headlineLarge = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            ),
            bodyLarge = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
        )
    )
}
```

### Custom Cupertino Typography

```kotlin
import com.slapps.cupertino.theme.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

responsiveConfiguration {
    withCustomCupertinoTypography(
        small = Typography(
            largeTitle = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            body = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        ),
        large = Typography(
            largeTitle = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            ),
            body = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
        )
    )
}
```

## 🔧 Complex Configuration Example

```kotlin
@Composable
fun App() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            // Theme: Material 3 for brand consistency
            withMaterialTheme()
            
            // Fonts: Brand fonts with reading/display separation
            withMaterialReadingDisplayFonts(
                readingFont = poppinsFont,
                displayFont = playfairFont,
                labelFont = robotoFont
            )
            
            // Custom dimensions for better mobile experience
            withCustomDimensions(
                small = smallDimensions.copy(
                    cardPadding = smallDimensions.cardPadding + 8.dp,
                    screenPaddingHorizontal = smallDimensions.screenPaddingHorizontal + 8.dp,
                    buttonHeightMedium = smallDimensions.buttonHeightMedium + 4.dp
                ),
                large = largeDimensions.copy(
                    cardPadding = largeDimensions.cardPadding + 8.dp,
                    screenPaddingHorizontal = largeDimensions.screenPaddingHorizontal + 24.dp
                )
            )
            
            // Brand colors
            withCustomMaterialColors(
                light = brandLightColors,
                dark = brandDarkColors
            )
            
            // Enhanced font weights for better hierarchy
            withCustomFontWeights(
                small = ResponsiveFontWeights(
                    // Basic weights
                    light = FontWeight.Light,
                    normal = FontWeight.Normal,
                    medium = FontWeight.Medium,
                    semiBold = FontWeight.SemiBold,
                    bold = FontWeight.Bold,
                    extraBold = FontWeight.ExtraBold,
                    
                    // Semantic weights for mobile
                    body = FontWeight.Normal,
                    emphasis = FontWeight.SemiBold,  // More emphasis on small screens
                    heading = FontWeight.SemiBold,
                    display = FontWeight.Bold,
                    button = FontWeight.Medium,
                    caption = FontWeight.Light
                ),
                large = ResponsiveFontWeights(
                    // Basic weights
                    light = FontWeight.ExtraLight,
                    normal = FontWeight.Light,
                    medium = FontWeight.Normal,
                    semiBold = FontWeight.Medium,
                    bold = FontWeight.SemiBold,
                    extraBold = FontWeight.Black,     // Stronger display weight for large screens
                    
                    // Semantic weights for desktop
                    body = FontWeight.Light,
                    emphasis = FontWeight.Normal,
                    heading = FontWeight.Medium,
                    display = FontWeight.SemiBold,
                    button = FontWeight.Normal,
                    caption = FontWeight.ExtraLight
                )
            )
        }
    ) {
        MainScreen()
    }
}
```

## 🎯 Configuration Patterns

### Brand-Consistent Configuration

```kotlin
object BrandConfiguration {
    val brandColors = responsiveConfiguration {
        withCustomMaterialColors(
            light = brandLightColorScheme,
            dark = brandDarkColorScheme
        )
        withCustomCupertinoColors(
            light = brandCupertinoLight,
            dark = brandCupertinoDark
        )
    }
    
    val brandFonts = responsiveConfiguration {
        withUniversalFont(brandFontFamily)
    }
    
    val complete = responsiveConfiguration {
        withMaterialTheme()
        withUniversalFont(brandFontFamily)
        withCustomMaterialColors(
            light = brandLightColorScheme,
            dark = brandDarkColorScheme
        )
        withCustomDimensions(
            small = brandMobileDimensions,
            large = brandDesktopDimensions
        )
    }
}

@Composable
fun App() {
    ComposiveTheme(configuration = BrandConfiguration.complete) {
        MainScreen()
    }
}
```

### Platform-Specific Configuration

```kotlin
@Composable
fun App() {
    val platform = remember { getCurrentPlatform() }
    
    val config = when {
        platform.isAndroid() -> responsiveConfiguration {
            withMaterialTheme()
            withCustomMaterialFonts(bodyFont = robotoFont)
        }
        platform.isIOS() -> responsiveConfiguration {
            withCupertinoTheme()
            withCustomCupertinoFonts(bodyFont = sfProFont)
        }
        else -> responsiveConfiguration {
            withPlatformDefaultTheme()
        }
    }
    
    ComposiveTheme(configuration = config) {
        MainScreen()
    }
}
```

## 🧪 Testing Configurations

### Configuration Debugging

```kotlin
@Composable
fun ConfigurationDebug() {
    val config = AppTheme.configuration
    val platform = AppTheme.platform
    
    LazyColumn {
        item {
            Card {
                Column(modifier = Modifier.padding(AppTheme.dimensions.cardPadding)) {
                    Text("Configuration Debug", style = MaterialTheme.typography.headlineSmall)
                    Text("Default Theme: ${config.defaultThemeType}")
                    Text("Platform Preference: ${config.enablePlatformThemePreference}")
                    Text("Platform: ${platform.name}")
                    Text("Has Custom Material Fonts: ${config.customMaterialFontResources != null}")
                    Text("Has Custom Dimensions: ${config.customDimensions != null}")
                    Text("Has Custom Colors: ${config.customMaterialColors != null}")
                }
            }
        }
    }
}
```

### A/B Testing Configurations

```kotlin
@Composable
fun App() {
    val useExperimentalConfig = remember { Random.nextBoolean() }
    
    val config = if (useExperimentalConfig) {
        responsiveConfiguration {
            withCustomDimensions(
                small = experimentalMobileDimensions
            )
        }
    } else {
        responsiveConfiguration {
            // Control configuration
        }
    }
    
    ComposiveTheme(configuration = config) {
        MainScreen()
    }
}
```

## 🎯 Best Practices

### 1. Start Simple, Iterate

```kotlin
// ✅ Start with defaults
ComposiveTheme {
    MainScreen()
}

// ✅ Add customizations gradually
ComposiveTheme(
    configuration = responsiveConfiguration {
        withMaterialTheme()
    }
) {
    MainScreen()
}
```

### 2. Separate Configuration Logic

```kotlin
// ✅ Good - Separate configuration objects
object AppConfiguration {
    val production = responsiveConfiguration {
        withMaterialTheme()
        withCustomMaterialFonts(bodyFont = productionFont)
    }
    
    val development = responsiveConfiguration {
        withMaterialTheme()
        // Use default fonts for development
    }
}
```

### 3. Test Platform Behaviors

```kotlin
// ✅ Test on different platforms
val config = responsiveConfiguration {
    withPlatformDefaultTheme() // Test platform-specific behavior
}
```

## 🚨 Important: AppTheme Usage Context

**❌ Cannot use AppTheme in configuration context:**
```kotlin
// ❌ This will cause compilation error
private val customDimensions = Dimensions(
    cardPadding = AppTheme.dimensions.cardPadding  // Error: AppTheme not available here
)

// ❌ This will also fail
responsiveConfiguration {
    withCustomFontWeights(
        small = ResponsiveFontWeights(
            body = AppTheme.fontWeights.body  // Error: AppTheme not available here
        )
    )
}
```

**✅ Can use AppTheme inside @Composable functions:**
```kotlin
@Composable
fun MyComponent() {
    // ✅ This works - inside @Composable context
    Card(
        modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
    ) {
        Text(
            text = "Hello",
            style = AppTheme.materialTypography.bodyLarge,
            fontWeight = AppTheme.fontWeights.body
        )
    }
}
```

**✅ Use base dimensions and hardcoded values in configuration:**
```kotlin
// ✅ This works - using base dimensions and hardcoded values
responsiveConfiguration {
    withCustomDimensions(
        small = smallDimensions.copy(
            cardPadding = smallDimensions.cardPadding + 8.dp  // Relative to base
        )
    )
    withCustomFontWeights(
        small = ResponsiveFontWeights(
            body = FontWeight.Normal,  // Hardcoded FontWeight enum
            heading = FontWeight.SemiBold
        )
    )
}
```

## 🔗 Related APIs

- **[Typography](typography.md)** - Font and text customization
- **[Responsive Dimensions](responsive-dimensions.md)** - Spacing and sizing
- **[API Reference](api-reference.md)** - Complete configuration API

---

**Configure Composive to perfectly match your app's needs!** ⚙️✨ 