# Typography System 📝

Master Composive's responsive typography system that automatically scales text across all screen sizes and platforms.

## 🎯 Overview

Composive provides responsive typography that:
- **Automatically scales** based on screen size
- **Adapts to platforms** (Material 3 on Android, Cupertino on iOS)
- **Supports custom fonts** across all text styles
- **Maintains readability** at all sizes
- **Follows design guidelines** for each platform

## 📱 Typography Scales

### Scale Overview

| Screen Size | Use Case | Typography Focus |
|-------------|----------|------------------|
| **Small** | Mobile portrait | Compact, efficient |
| **Compact** | Mobile landscape | Slightly larger |
| **Medium** | Tablets | Generous spacing |
| **Large** | Desktop | Maximum impact |

### Material Typography Scales

```kotlin
// Small screens (mobile portrait)
AppTheme.materialTypography.headlineLarge  // 30sp
AppTheme.materialTypography.bodyLarge      // 16sp
AppTheme.materialTypography.labelMedium    // 12sp

// Large screens (desktop)
AppTheme.materialTypography.headlineLarge  // 50sp
AppTheme.materialTypography.bodyLarge      // 29sp
AppTheme.materialTypography.labelMedium    // 20sp
```

### Cupertino Typography Scales

```kotlin
// Small screens (mobile)
AppTheme.cupertinoTypography.largeTitle    // 30sp
AppTheme.cupertinoTypography.body          // 16sp
AppTheme.cupertinoTypography.caption1      // 11sp

// Large screens (desktop)
AppTheme.cupertinoTypography.largeTitle    // 50sp
AppTheme.cupertinoTypography.body          // 24sp
AppTheme.cupertinoTypography.caption1      // 16sp
```

## 🎨 Using Typography

### Basic Usage

```kotlin
@Composable
fun TypographyExample() {
    Column {
        Text(
            text = "Responsive Headline",
            style = AppTheme.materialTypography.headlineLarge,
            fontWeight = AppTheme.fontWeights.bold
        )
        
        Text(
            text = "This body text automatically scales based on screen size",
            style = AppTheme.materialTypography.bodyLarge
        )
        
        Text(
            text = "Small label text",
            style = AppTheme.materialTypography.labelMedium,
            color = LocalContentColor.current.copy(alpha = 0.7f)
        )
    }
}
```

### Platform-Adaptive Typography

```kotlin
@Composable
fun AdaptiveText(text: String) {
    val platform = AppTheme.platform
    
    Text(
        text = text,
        style = when {
            platform.isAndroid() -> AppTheme.materialTypography.headlineSmall
            platform.isIOS() -> AppTheme.cupertinoTypography.title1
            else -> AppTheme.materialTypography.headlineSmall
        }
    )
}
```

## 🔤 Font Customization

### Setting Custom Fonts

```kotlin
// Apply custom fonts to Material typography
ComposiveTheme(
    configuration = responsiveConfiguration {
        withCustomMaterialFonts(
            displayFont = FontFamily.Serif,        // Headlines
            bodyFont = FontFamily.SansSerif,       // Body text
            labelFont = FontFamily.Monospace       // Labels
        )
    }
) {
    MyApp()
}
```

### Font Family Configuration

```kotlin
// Define custom font families
val brandFontFamily = FontFamily(
    Font(Res.font.brand_regular, FontWeight.Normal),
    Font(Res.font.brand_medium, FontWeight.Medium),
    Font(Res.font.brand_bold, FontWeight.Bold)
)

val readingFontFamily = FontFamily(
    Font(Res.font.reading_regular, FontWeight.Normal),
    Font(Res.font.reading_medium, FontWeight.Medium)
)

// Use in configuration
responsiveConfiguration {
    withMaterialReadingDisplayFonts(
        readingFont = readingFontFamily,    // Body, titles
        displayFont = brandFontFamily,      // Headlines
        labelFont = FontFamily.Default      // Labels
    )
}
```

### Universal Font Setup

```kotlin
// Same font across all platforms and styles
responsiveConfiguration {
    withUniversalFont(FontFamily.SansSerif)
}

// Or platform-specific fonts
responsiveConfiguration {
    withCustomMaterialFonts(bodyFont = robotoFont)
    withCustomCupertinoFonts(bodyFont = sfProFont)
}
```

## ⚖️ Font Weights

### Responsive Font Weights

Font weights automatically adapt to screen sizes for optimal hierarchy:

```kotlin
@Composable
fun FontWeightExample() {
    Column {
        Text(
            text = "Bold Headline",
            style = AppTheme.materialTypography.headlineMedium,
            fontWeight = AppTheme.fontWeights.bold
        )
        
        Text(
            text = "Semi-bold subtitle",
            style = AppTheme.materialTypography.titleMedium,
            fontWeight = AppTheme.fontWeights.semiBold
        )
        
        Text(
            text = "Regular body text",
            style = AppTheme.materialTypography.bodyLarge,
            fontWeight = AppTheme.fontWeights.normal
        )
        
        Text(
            text = "Light caption",
            style = AppTheme.materialTypography.labelMedium,
            fontWeight = AppTheme.fontWeights.light
        )
    }
}
```

### Semantic Font Weights

Use semantic weights for consistent text hierarchy:

```kotlin
@Composable
fun SemanticFontWeightExample() {
    Column {
        Text(
            text = "Display Title",
            style = AppTheme.materialTypography.displayLarge,
            fontWeight = AppTheme.fontWeights.display
        )
        
        Text(
            text = "Section Heading",
            style = AppTheme.materialTypography.headlineMedium,
            fontWeight = AppTheme.fontWeights.heading
        )
        
        Text(
            text = "This is body text with consistent weight",
            style = AppTheme.materialTypography.bodyLarge,
            fontWeight = AppTheme.fontWeights.body
        )
        
        Text(
            text = "Emphasized content",
            style = AppTheme.materialTypography.bodyLarge,
            fontWeight = AppTheme.fontWeights.emphasis
        )
        
        Text(
            text = "Small caption text",
            style = AppTheme.materialTypography.labelSmall,
            fontWeight = AppTheme.fontWeights.caption
        )
    }
}
```

### Custom Font Weights

```kotlin
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
            emphasis = FontWeight.Medium,
            heading = FontWeight.SemiBold,
            display = FontWeight.Bold,
            button = FontWeight.Medium,
            caption = FontWeight.Light
        ),
        large = ResponsiveFontWeights(
            // Different weights for large screens
            light = FontWeight.ExtraLight,
            normal = FontWeight.Light,
            medium = FontWeight.Normal,
            semiBold = FontWeight.Medium,
            bold = FontWeight.SemiBold,
            extraBold = FontWeight.Bold,
            
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

## 📐 Typography Patterns

### Hierarchical Text

```kotlin
@Composable
fun ArticleContent() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3),
        contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal)
    ) {
        item {
            // Main headline
            Text(
                text = "Article Title",
                style = AppTheme.materialTypography.headlineLarge,
                fontWeight = AppTheme.fontWeights.bold
            )
        }
        
        item {
            // Subtitle
            Text(
                text = "Article subtitle with additional context",
                style = AppTheme.materialTypography.headlineSmall,
                fontWeight = AppTheme.fontWeights.medium,
                color = LocalContentColor.current.copy(alpha = 0.8f)
            )
        }
        
        item {
            // Metadata
            Text(
                text = "By Author Name • 5 min read",
                style = AppTheme.materialTypography.labelLarge,
                color = LocalContentColor.current.copy(alpha = 0.6f)
            )
        }
        
        item {
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space4))
        }
        
        item {
            // Section header
            Text(
                text = "Introduction",
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.semiBold
            )
        }
        
        item {
            // Body paragraph
            Text(
                text = "This is a paragraph of body text that demonstrates how responsive typography scales automatically based on screen size...",
                style = AppTheme.materialTypography.bodyLarge,
                lineHeight = AppTheme.materialTypography.bodyLarge.lineHeight!! * 1.4
            )
        }
    }
}
```

### Responsive Card Typography

```kotlin
@Composable
fun ResponsiveCard(
    title: String,
    subtitle: String,
    description: String
) {
    val deviceConfig = rememberDeviceConfiguration()
    
    Card {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            // Title scales based on device
            Text(
                text = title,
                style = when (deviceConfig) {
                    DeviceConfiguration.MOBILE_PORTRAIT -> AppTheme.materialTypography.titleMedium
                    DeviceConfiguration.DESKTOP -> AppTheme.materialTypography.headlineSmall
                    else -> AppTheme.materialTypography.titleLarge
                },
                fontWeight = AppTheme.fontWeights.bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            if (subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space1))
                
                Text(
                    text = subtitle,
                    style = AppTheme.materialTypography.titleSmall,
                    fontWeight = AppTheme.fontWeights.medium,
                    color = LocalContentColor.current.copy(alpha = 0.8f)
                )
            }
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
            
            Text(
                text = description,
                style = AppTheme.materialTypography.bodyMedium,
                maxLines = if (deviceConfig.isMobile()) 3 else 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
```

## 🎨 Advanced Typography

### Custom Typography Scales

```kotlin
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Create completely custom typography for specific screens
val customMobileTypography = Typography(
    headlineLarge = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    )
)

val customDesktopTypography = Typography(
    headlineLarge = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 56.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp
    )
)

responsiveConfiguration {
    withCustomMaterialTypography(
        small = customMobileTypography,
        large = customDesktopTypography
    )
}
```

### Dynamic Typography

```kotlin
@Composable
fun DynamicText(
    text: String,
    emphasize: Boolean = false
) {
    val deviceConfig = rememberDeviceConfiguration()
    
    val style = when {
        emphasize && deviceConfig.isDesktop() -> AppTheme.materialTypography.headlineMedium
        emphasize -> AppTheme.materialTypography.titleLarge
        deviceConfig.isMobile() -> AppTheme.materialTypography.bodyMedium
        else -> AppTheme.materialTypography.bodyLarge
    }
    
    val fontWeight = when {
        emphasize -> AppTheme.fontWeights.bold
        deviceConfig.isDesktop() -> AppTheme.fontWeights.medium
        else -> AppTheme.fontWeights.normal
    }
    
    Text(
        text = text,
        style = style,
        fontWeight = fontWeight
    )
}
```

## 🔤 Typography Guidelines

### Material Design Typography

Follow Material Design principles:

```kotlin
@Composable
fun MaterialTypographyGuide() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
    ) {
        // Display - Largest text, short and impactful
        Text(
            text = "Display Large",
            style = AppTheme.materialTypography.displayLarge,
            fontWeight = AppTheme.fontWeights.bold
        )
        
        // Headline - High-emphasis text
        Text(
            text = "Headline Medium",
            style = AppTheme.materialTypography.headlineMedium,
            fontWeight = AppTheme.fontWeights.semiBold
        )
        
        // Title - Medium-emphasis text
        Text(
            text = "Title Large",
            style = AppTheme.materialTypography.titleLarge,
            fontWeight = AppTheme.fontWeights.medium
        )
        
        // Body - Main text content
        Text(
            text = "Body Large - This is the main text content that users will read most frequently.",
            style = AppTheme.materialTypography.bodyLarge
        )
        
        // Label - UI elements, buttons
        Text(
            text = "Label Medium",
            style = AppTheme.materialTypography.labelMedium,
            fontWeight = AppTheme.fontWeights.medium
        )
    }
}
```

### Cupertino Typography

Follow iOS Human Interface Guidelines:

```kotlin
@Composable
fun CupertinoTypographyGuide() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
    ) {
        // Large Title - Navigation bars, large text
        Text(
            text = "Large Title",
            style = AppTheme.cupertinoTypography.largeTitle,
            fontWeight = AppTheme.fontWeights.bold
        )
        
        // Title 1 - Primary titles
        Text(
            text = "Title 1",
            style = AppTheme.cupertinoTypography.title1,
            fontWeight = AppTheme.fontWeights.semiBold
        )
        
        // Headline - Emphasized text
        Text(
            text = "Headline",
            style = AppTheme.cupertinoTypography.headline,
            fontWeight = AppTheme.fontWeights.semiBold
        )
        
        // Body - Main content
        Text(
            text = "Body text that provides the main content and information to users.",
            style = AppTheme.cupertinoTypography.body
        )
        
        // Caption - Supplementary content
        Text(
            text = "Caption 1",
            style = AppTheme.cupertinoTypography.caption1,
            color = LocalContentColor.current.copy(alpha = 0.6f)
        )
    }
}
```

## 🧪 Testing Typography

### Typography Debug Component

```kotlin
@Composable
fun TypographyDebug() {
    val deviceConfig = rememberDeviceConfiguration()
    
    LazyColumn {
        item {
            Text(
                "Typography Debug - ${deviceConfig}",
                style = AppTheme.materialTypography.headlineSmall,
                fontWeight = AppTheme.fontWeights.bold
            )
        }
        
        // Material Typography
        item {
            TypographySection(
                title = "Material Typography",
                styles = listOf(
                    "Display Large" to AppTheme.materialTypography.displayLarge,
                    "Headline Large" to AppTheme.materialTypography.headlineLarge,
                    "Title Large" to AppTheme.materialTypography.titleLarge,
                    "Body Large" to AppTheme.materialTypography.bodyLarge,
                    "Label Large" to AppTheme.materialTypography.labelLarge
                )
            )
        }
        
        // Cupertino Typography
        item {
            TypographySection(
                title = "Cupertino Typography",
                styles = listOf(
                    "Large Title" to AppTheme.cupertinoTypography.largeTitle,
                    "Title 1" to AppTheme.cupertinoTypography.title1,
                    "Headline" to AppTheme.cupertinoTypography.headline,
                    "Body" to AppTheme.cupertinoTypography.body,
                    "Caption 1" to AppTheme.cupertinoTypography.caption1
                )
            )
        }
    }
}

@Composable
fun TypographySection(
    title: String,
    styles: List<Pair<String, TextStyle>>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.cardSpacing)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = title,
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.bold
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space3))
            
            styles.forEach { (name, style) ->
                Text(
                    text = "$name (${style.fontSize})",
                    style = style
                )
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
            }
        }
    }
}
```

## 📊 Typography Reference

### Font Size Scaling

| Screen | Display | Headline | Title | Body | Label |
|--------|---------|----------|--------|------|-------|
| Small | 23sp | 30sp | 20sp | 16sp | 14sp |
| Compact | 26sp | 35sp | 23sp | 18sp | 16sp |
| Medium | 30sp | 40sp | 27sp | 20sp | 18sp |
| Large | 39sp | 50sp | 36sp | 29sp | 24sp |

### Line Height Recommendations

```kotlin
@Composable
fun OptimalLineHeightExamples() {
    Column {
        // Body text with optimal line height for readability
        Text(
            text = "This is body text with optimized line height for better readability...",
            style = AppTheme.materialTypography.bodyLarge.copy(
                lineHeight = AppTheme.materialTypography.bodyLarge.lineHeight!! * 1.4
            ),
            fontWeight = AppTheme.fontWeights.body
        )
        
        Spacer(modifier = Modifier.height(AppTheme.dimensions.space4))
        
        // Headline with tighter line height for impact
        Text(
            text = "Impactful Headline Text",
            style = AppTheme.materialTypography.headlineLarge.copy(
                lineHeight = AppTheme.materialTypography.headlineLarge.lineHeight!! * 1.2
            ),
            fontWeight = AppTheme.fontWeights.heading
        )
    }
}

// For reusable text styles, create them inside @Composable functions
@Composable
fun getBodyTextStyle() = AppTheme.materialTypography.bodyLarge.copy(
    lineHeight = AppTheme.materialTypography.bodyLarge.lineHeight!! * 1.4,
    fontWeight = AppTheme.fontWeights.body
)

@Composable
fun getHeadlineStyle() = AppTheme.materialTypography.headlineLarge.copy(
    lineHeight = AppTheme.materialTypography.headlineLarge.lineHeight!! * 1.2,
    fontWeight = AppTheme.fontWeights.heading
)
```

## 🚨 Important: AppTheme Typography Context

**❌ Cannot use AppTheme typography outside @Composable functions:**
```kotlin
// ❌ This will cause compilation error
val customTextStyle = AppTheme.materialTypography.bodyLarge.copy(
    fontWeight = AppTheme.fontWeights.bold  // Error: AppTheme not available here
)

// ❌ This will also fail
object TextStyles {
    val heading = AppTheme.materialTypography.headlineLarge  // Error!
}
```

**✅ Can use AppTheme typography inside @Composable functions:**
```kotlin
@Composable
fun MyText() {
    // ✅ This works - inside @Composable context
    Text(
        text = "Hello World",
        style = AppTheme.materialTypography.bodyLarge,
        fontWeight = AppTheme.fontWeights.body
    )
}

@Composable
fun getCustomTextStyle() = AppTheme.materialTypography.bodyLarge.copy(
    fontWeight = AppTheme.fontWeights.bold,
    lineHeight = AppTheme.materialTypography.bodyLarge.lineHeight!! * 1.4
)
```

**✅ Use hardcoded values in configuration context:**
```kotlin
// ✅ This works - using hardcoded FontWeight values
responsiveConfiguration {
    withCustomFontWeights(
        small = ResponsiveFontWeights(
            body = FontWeight.Normal,      // Hardcoded FontWeight
            heading = FontWeight.SemiBold
        )
    )
}
```

## 🎯 Best Practices

### 1. Use Semantic Typography

```kotlin
// ✅ Good - Semantic usage
Text(
    text = "Article Title", 
    style = AppTheme.materialTypography.headlineLarge
)

// ❌ Avoid - Size-based naming
Text(
    text = "Article Title", 
    style = TextStyle(fontSize = 30.sp)
)
```

### 2. Maintain Hierarchy

```kotlin
// ✅ Good - Clear hierarchy
Column {
    Text("Main Title", style = AppTheme.materialTypography.headlineLarge)
    Text("Subtitle", style = AppTheme.materialTypography.titleMedium)
    Text("Body text", style = AppTheme.materialTypography.bodyLarge)
}
```

### 3. Consider Reading Distance

```kotlin
// ✅ Good - Adapt to screen size
val textStyle = when (rememberDeviceConfiguration()) {
    DeviceConfiguration.MOBILE_PORTRAIT -> AppTheme.materialTypography.bodyMedium
    DeviceConfiguration.DESKTOP -> AppTheme.materialTypography.bodyLarge
    else -> AppTheme.materialTypography.bodyMedium
}
```

## 🔗 Related APIs

- **[API Reference](api-reference.md)** - Complete typography API
- **[Configuration](configuration.md)** - Font customization & theming
- **[Responsive Dimensions](responsive-dimensions.md)** - Spacing system

---

**Master responsive typography for perfect readability on every screen!** 📝✨ 