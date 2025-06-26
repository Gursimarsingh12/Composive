# Responsive Dimensions 📏

Master Composive's responsive dimension system that automatically adapts spacing, sizing, and layout values across all screen sizes.

## 🎯 Overview

Composive provides a comprehensive dimension system that:
- **Automatically scales** based on screen size
- **Maintains consistent proportions** across devices
- **Follows platform guidelines** for touch targets and spacing
- **Provides semantic naming** for different use cases
- **Supports customization** for brand requirements

## 📊 Dimension Categories

### Core Spacing System

```kotlin
// Base spacing units (4dp increments)
AppTheme.dimensions.space1   // 4dp  - Smallest gap
AppTheme.dimensions.space2   // 8dp  - Tight spacing
AppTheme.dimensions.space3   // 12dp - Small gap
AppTheme.dimensions.space4   // 16dp - Standard spacing
AppTheme.dimensions.space5   // 20dp - Medium gap
AppTheme.dimensions.space6   // 24dp - Large gap
AppTheme.dimensions.space8   // 32dp - Section spacing
AppTheme.dimensions.space10  // 40dp - Major gap
AppTheme.dimensions.space12  // 48dp - Large section
AppTheme.dimensions.space16  // 64dp - Major separation
```

### Content Padding

```kotlin
// Responsive content padding
AppTheme.dimensions.contentPaddingSmall   // Tight content
AppTheme.dimensions.contentPaddingMedium  // Standard content
AppTheme.dimensions.contentPaddingLarge   // Generous content
```

### Screen Padding

```kotlin
// Screen edge padding
AppTheme.dimensions.screenPaddingHorizontal  // Left/right margins
AppTheme.dimensions.screenPaddingVertical    // Top/bottom margins
```

### Layout Spacing

```kotlin
// Semantic layout spacing
AppTheme.dimensions.sectionSpacing  // Between major sections
AppTheme.dimensions.itemSpacing     // Between list items
```

## 🎨 Using Dimensions

### Basic Spacing

```kotlin
@Composable
fun SpacingExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.dimensions.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
    ) {
        Text(
            text = "Title",
            modifier = Modifier.padding(bottom = AppTheme.dimensions.space2)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
            ) {
                Text("Card content")
            }
        }
        
        Spacer(modifier = Modifier.height(AppTheme.dimensions.sectionSpacing))
        
        Button(
            onClick = { },
            modifier = Modifier.height(AppTheme.dimensions.buttonHeightMedium)
        ) {
            Text("Action")
        }
    }
}
```

### Responsive Component Sizing

```kotlin
@Composable
fun ResponsiveComponents() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar that scales with screen size
        AsyncImage(
            model = userImageUrl,
            contentDescription = "User avatar",
            modifier = Modifier
                .size(AppTheme.dimensions.avatarMedium)
                .clip(CircleShape)
        )
        
        Column {
            Text(
                text = "Username",
                style = AppTheme.materialTypography.titleMedium
            )
            
            Text(
                text = "Online",
                style = AppTheme.materialTypography.bodySmall,
                color = LocalContentColor.current.copy(alpha = 0.7f)
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Icon that scales appropriately
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More options",
            modifier = Modifier.size(AppTheme.dimensions.iconMedium)
        )
    }
}
```

## 📱 Screen Size Scaling

### Scaling Overview

| Screen Size | Space4 | Card Padding | Avatar Medium | Button Height |
|-------------|---------|--------------|---------------|---------------|
| **Small** | 16dp | 12dp | 40dp | 44dp |
| **Compact** | 16dp | 18dp | 52dp | 52dp |
| **Medium** | 16dp | 20dp | 52dp | 52dp |
| **Large** | 16dp | 24dp | 56dp | 56dp |

### Device-Specific Examples

```kotlin
@Composable
fun DeviceSpecificDimensions() {
    val deviceConfig = rememberDeviceConfiguration()
    
    val cardPadding = when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> AppTheme.dimensions.contentPaddingSmall
        DeviceConfiguration.TABLET_PORTRAIT -> AppTheme.dimensions.contentPaddingMedium
        DeviceConfiguration.DESKTOP -> AppTheme.dimensions.contentPaddingLarge
        else -> AppTheme.dimensions.contentPaddingMedium
    }
    
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(cardPadding)
        ) {
            Text("Content adapts to screen size")
        }
    }
}
```

## 🎯 Icon Dimensions

### Icon Sizing System

```kotlin
// Semantic icon sizes
AppTheme.dimensions.iconTiny    // 14-18dp - Decorative icons
AppTheme.dimensions.iconSmall   // 20-28dp - Navigation icons
AppTheme.dimensions.iconMedium  // 28-36dp - Action icons
AppTheme.dimensions.iconLarge   // 40-56dp - Prominent features
```

### Icon Usage Examples

```kotlin
@Composable
fun IconExamples() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
    ) {
        // Small navigation icon
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(AppTheme.dimensions.iconSmall)
            )
            Spacer(modifier = Modifier.width(AppTheme.dimensions.space2))
            Text("Back")
        }
        
        // Medium action icon
        IconButton(
            onClick = { },
            modifier = Modifier.size(AppTheme.dimensions.iconMedium + AppTheme.dimensions.space4)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Like",
                modifier = Modifier.size(AppTheme.dimensions.iconMedium)
            )
        }
        
        // Large prominent icon
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Featured",
            modifier = Modifier.size(AppTheme.dimensions.iconLarge),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
```

## 👤 Avatar Dimensions

### Avatar Sizing System

```kotlin
// User avatar sizes
AppTheme.dimensions.avatarSmall   // 28-40dp - Compact lists
AppTheme.dimensions.avatarMedium  // 40-56dp - Standard usage
AppTheme.dimensions.avatarLarge   // 56-80dp - Profile headers
AppTheme.dimensions.avatarXLarge  // 80-120dp - Profile pages
```

### Avatar Examples

```kotlin
@Composable
fun AvatarExamples() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
    ) {
        // Small avatar for lists
        ListItem(
            headlineContent = { Text("User Name") },
            leadingContent = {
                AsyncImage(
                    model = userImageUrl,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(AppTheme.dimensions.avatarSmall)
                        .clip(CircleShape)
                )
            }
        )
        
        // Large avatar for profile
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = userImageUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(AppTheme.dimensions.avatarXLarge)
                    .clip(CircleShape)
                    .border(
                        width = AppTheme.dimensions.space1 / 2,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space3))
            
            Text(
                text = "John Doe",
                style = AppTheme.materialTypography.headlineSmall,
                fontWeight = AppTheme.fontWeights.heading
            )
        }
    }
}
```

## 🖼️ Image Dimensions

### Image Sizing System

```kotlin
// Image sizes for different contexts
AppTheme.dimensions.imageThumb   // 64-112dp  - Thumbnails
AppTheme.dimensions.imageSmall   // 96-160dp  - Small previews
AppTheme.dimensions.imageMedium  // 160-280dp - Standard images
AppTheme.dimensions.imageLarge   // 240-420dp - Large previews
AppTheme.dimensions.imageHero    // 280-440dp - Hero images
```

### Image Examples

```kotlin
@Composable
fun ImageExamples() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
    ) {
        // Hero image
        item {
            AsyncImage(
                model = heroImageUrl,
                contentDescription = "Hero image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.dimensions.imageHero),
                contentScale = ContentScale.Crop
            )
        }
        
        // Image grid
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
            ) {
                items(images) { imageUrl ->
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Gallery image",
                        modifier = Modifier
                            .size(AppTheme.dimensions.imageMedium)
                            .clip(RoundedCornerShape(AppTheme.dimensions.space2)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        
        // Thumbnail list
        items(articles) { article ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
            ) {
                AsyncImage(
                    model = article.thumbnailUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(AppTheme.dimensions.imageThumb)
                        .clip(RoundedCornerShape(AppTheme.dimensions.space1)),
                    contentScale = ContentScale.Crop
                )
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = article.title,
                        style = AppTheme.materialTypography.titleMedium,
                        fontWeight = AppTheme.fontWeights.heading,
                        maxLines = 2
                    )
                    Text(
                        text = article.summary,
                        style = AppTheme.materialTypography.bodyMedium,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
```

## 🔲 Button Dimensions

### Button Sizing System

```kotlin
// Button heights for different emphasis
AppTheme.dimensions.buttonHeightSmall   // 36-48dp - Compact buttons
AppTheme.dimensions.buttonHeightMedium  // 44-56dp - Standard buttons
AppTheme.dimensions.buttonHeightLarge   // 52-64dp - Prominent buttons
AppTheme.dimensions.buttonMinWidth      // 88-128dp - Minimum width
```

### Button Examples

```kotlin
@Composable
fun ButtonExamples() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
    ) {
        // Small button for compact spaces
        Button(
            onClick = { },
            modifier = Modifier.height(AppTheme.dimensions.buttonHeightSmall)
        ) {
            Text("Small")
        }
        
        // Standard button
        Button(
            onClick = { },
            modifier = Modifier
                .height(AppTheme.dimensions.buttonHeightMedium)
                .widthIn(min = AppTheme.dimensions.buttonMinWidth)
        ) {
            Text("Standard Button")
        }
        
        // Large prominent button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimensions.buttonHeightLarge)
        ) {
            Text("Large Action")
        }
        
        // Responsive button row
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(AppTheme.dimensions.buttonHeightMedium)
            ) {
                Text("Cancel")
            }
            
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .height(AppTheme.dimensions.buttonHeightMedium)
            ) {
                Text("Confirm")
            }
        }
    }
}
```

## 📝 Input Dimensions

### Input Field Sizing

```kotlin
// Input field dimensions
AppTheme.dimensions.inputHeight    // 44-56dp - Touch-friendly height
AppTheme.dimensions.inputMinWidth  // 120-180dp - Minimum readable width
```

### Input Examples

```kotlin
@Composable
fun InputExamples() {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
    ) {
        // Standard text field
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimensions.inputHeight)
        )
        
        // Compact input row
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("First") },
                modifier = Modifier
                    .weight(1f)
                    .height(AppTheme.dimensions.inputHeight)
            )
            
            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Last") },
                modifier = Modifier
                    .weight(1f)
                    .height(AppTheme.dimensions.inputHeight)
            )
        }
    }
}
```

## 🃏 Card Dimensions

### Card Sizing System

```kotlin
// Card-related dimensions
AppTheme.dimensions.cardPadding   // 12-32dp - Internal padding
AppTheme.dimensions.cardSpacing   // 8-20dp  - Space between cards
AppTheme.dimensions.cardElevation // 2-6dp   - Card elevation
```

### Card Examples

```kotlin
@Composable
fun CardExamples() {
    LazyColumn(
        contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = AppTheme.dimensions.cardElevation
                )
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
                ) {
                    Text(
                        text = item.title,
                        style = AppTheme.materialTypography.titleMedium,
                        fontWeight = AppTheme.fontWeights.heading
                    )
                    
                    Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
                    
                    Text(
                        text = item.description,
                        style = AppTheme.materialTypography.bodyMedium
                    )
                }
            }
        }
    }
}
```

## 📱 Component Dimensions

### Bottom Sheet & Dialog

```kotlin
// Component-specific dimensions
AppTheme.dimensions.bottomSheetPeekHeight  // 56-80dp  - Peek height
AppTheme.dimensions.dialogMaxWidth         // 280-480dp - Max dialog width
AppTheme.dimensions.dialogPadding          // 16-32dp  - Dialog padding
```

### Component Examples

```kotlin
@Composable
fun ComponentExamples() {
    // Dialog with responsive dimensions
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Confirm Action") },
        text = { Text("Are you sure you want to proceed?") },
        confirmButton = {
            TextButton(onClick = { }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { }) {
                Text("Cancel")
            }
        },
        modifier = Modifier
            .widthIn(max = AppTheme.dimensions.dialogMaxWidth)
            .padding(AppTheme.dimensions.dialogPadding)
    )
}
```

## 🎨 Custom Dimensions

### Creating Custom Dimension Sets

```kotlin
// Define custom dimensions for specific use cases
val customMobileDimensions = Dimensions(
    // Base spacing (keep standard)
    space1 = 4.dp,
    space2 = 8.dp,
    space4 = 16.dp,
    space8 = 32.dp,
    
    // Custom card padding for mobile
    cardPadding = 20.dp,
    
    // Larger buttons for better touch targets
    buttonHeightMedium = 48.dp,
    
    // Tighter screen padding for more content
    screenPaddingHorizontal = 20.dp,
    
    // Standard avatar sizes
    avatarMedium = 44.dp,
    
    // ... other dimensions
)

// Use in configuration
responsiveConfiguration {
    withCustomDimensions(
        small = customMobileDimensions
    )
}
```

### Brand-Specific Dimensions

```kotlin
// Create brand-specific dimension scales
val brandMobileDimensions = smallDimensions.copy(
    // Base dimensions from small, with brand-specific adjustments
    cardPadding = smallDimensions.cardPadding * 1.5f,
    screenPaddingHorizontal = smallDimensions.screenPaddingHorizontal,
    buttonHeightMedium = smallDimensions.buttonHeightMedium + AppTheme.dimensions.space1,
    avatarMedium = smallDimensions.avatarMedium + AppTheme.dimensions.space1,
    // ... other dimensions
)

val brandDesktopDimensions = largeDimensions.copy(
    // Base dimensions from large, with brand-specific adjustments  
    cardPadding = largeDimensions.cardPadding + AppTheme.dimensions.space2,
    buttonHeightMedium = largeDimensions.buttonHeightMedium + AppTheme.dimensions.space1,
    sectionSpacing = largeDimensions.sectionSpacing + AppTheme.dimensions.space2,
    avatarMedium = largeDimensions.avatarMedium + AppTheme.dimensions.space1,
    avatarLarge = largeDimensions.avatarLarge + AppTheme.dimensions.space2
)
```

## 📊 Dimension Reference Table

### Complete Scaling Table

| Dimension | Small | Compact | Medium | Large | Use Case |
|-----------|--------|---------|---------|--------|-----------|
| space1 | 4dp | 4dp | 4dp | 4dp | Minimal gap |
| space4 | 16dp | 16dp | 16dp | 16dp | Standard spacing |
| cardPadding | 12dp | 18dp | 20dp | 24dp | Card internal padding |
| iconMedium | 28dp | 34dp | 32dp | 36dp | Action icons |
| avatarMedium | 40dp | 52dp | 52dp | 56dp | User avatars |
| buttonHeightMedium | 44dp | 52dp | 52dp | 56dp | Standard buttons |
| imageMedium | 160dp | 220dp | 240dp | 280dp | Content images |

## 🧪 Testing Dimensions

### Dimension Debug Component

```kotlin
@Composable
fun DimensionDebug() {
    val deviceConfig = rememberDeviceConfiguration()
    
    LazyColumn {
        item {
            Text(
                "Device Configuration Debug",
                style = AppTheme.materialTypography.titleLarge
            )
        }
        
        item {
            DimensionCard(
                title = "Spacing",
                dimensions = listOf(
                    "space1" to AppTheme.dimensions.space1,
                    "space2" to AppTheme.dimensions.space2,
                    "space4" to AppTheme.dimensions.space4,
                    "space8" to AppTheme.dimensions.space8
                )
            )
        }
        
        item {
            DimensionCard(
                title = "Icons",
                dimensions = listOf(
                    "iconSmall" to AppTheme.dimensions.iconSmall,
                    "iconMedium" to AppTheme.dimensions.iconMedium,
                    "iconLarge" to AppTheme.dimensions.iconLarge
                )
            )
        }
        
        item {
            DimensionCard(
                title = "Avatars",
                dimensions = listOf(
                    "avatarSmall" to AppTheme.dimensions.avatarSmall,
                    "avatarMedium" to AppTheme.dimensions.avatarMedium,
                    "avatarLarge" to AppTheme.dimensions.avatarLarge
                )
            )
        }
    }
}

@Composable
fun DimensionCard(
    title: String,
    dimensions: List<Pair<String, Dp>>
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
                fontWeight = AppTheme.fontWeights.heading
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space3))
            
            dimensions.forEach { (name, value) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(name)
                    Text("${value}")
                }
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space1))
            }
        }
    }
}
```

## 🎯 Best Practices

### 1. Use Semantic Dimensions

```kotlin
// ✅ Good - Semantic usage
modifier = Modifier.padding(AppTheme.dimensions.cardPadding)

// ❌ Avoid - Magic numbers
modifier = Modifier.padding(16.dp)
```

### 2. Consistent Spacing

```kotlin
// ✅ Good - Consistent spacing system
Column(
    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
) {
    // Items
}

// ❌ Avoid - Inconsistent spacing
Column {
    Item1()
    Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
    Item2()
    Spacer(modifier = Modifier.height(AppTheme.dimensions.space3))
    Item3()
}
```

### 3. Touch Target Consideration

```kotlin
// ✅ Good - Proper touch targets
IconButton(
    onClick = { },
    modifier = Modifier.size(AppTheme.dimensions.iconMedium + AppTheme.dimensions.space4)
) {
    Icon(
        imageVector = icon,
        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
    )
}
```

## 🔗 Related APIs

- **[API Reference](api-reference.md)** - Complete dimensions API
- **[Configuration](configuration.md)** - Custom dimension setup
- **[Device Configuration](device-configuration.md)** - Screen size detection
- **[Typography](typography.md)** - Text sizing system

---

**Master responsive dimensions for perfect spacing on every screen!** 📏✨ 