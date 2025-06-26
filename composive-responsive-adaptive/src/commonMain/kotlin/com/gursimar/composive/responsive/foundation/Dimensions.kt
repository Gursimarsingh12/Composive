package com.gursimar.composive.responsive.foundation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Data class that defines responsive dimensions for different UI elements across all screen sizes.
 * 
 * This class provides a comprehensive set of dimensions that scale appropriately for different
 * device configurations, ensuring consistent and accessible UI across mobile, tablet, and desktop platforms.
 * 
 * @property space1 Base spacing unit (4dp) - smallest spacing increment
 * @property space2 Double base spacing (8dp) - for tight spacing
 * @property space3 Triple base spacing (12dp) - for small gaps
 * @property space4 Quadruple base spacing (16dp) - standard spacing unit
 * @property space5 5x base spacing (20dp) - for medium gaps
 * @property space6 6x base spacing (24dp) - for larger gaps
 * @property space8 8x base spacing (32dp) - for section separation
 * @property space10 10x base spacing (40dp) - for major section gaps
 * @property space12 12x base spacing (48dp) - for large section separation
 * @property space16 16x base spacing (64dp) - for major layout separation
 * 
 * @property contentPaddingSmall Small padding for content areas
 * @property contentPaddingMedium Medium padding for content areas
 * @property contentPaddingLarge Large padding for content areas
 * @property screenPaddingHorizontal Horizontal padding for screen edges
 * @property screenPaddingVertical Vertical padding for screen edges
 * @property sectionSpacing Spacing between major sections
 * @property itemSpacing Spacing between individual items
 * 
 * @property iconTiny Size for tiny icons (decorative elements)
 * @property iconSmall Size for small icons (navigation, actions)
 * @property iconMedium Size for medium icons (primary actions)
 * @property iconLarge Size for large icons (prominent features)
 * 
 * @property avatarSmall Size for small user avatars
 * @property avatarMedium Size for medium user avatars
 * @property avatarLarge Size for large user avatars
 * @property avatarXLarge Size for extra large user avatars
 * 
 * @property imageThumb Size for thumbnail images
 * @property imageSmall Size for small images
 * @property imageMedium Size for medium images
 * @property imageLarge Size for large images
 * @property imageHero Size for hero/banner images
 * 
 * @property buttonHeightSmall Height for small buttons
 * @property buttonHeightMedium Height for medium buttons
 * @property buttonHeightLarge Height for large buttons
 * @property buttonMinWidth Minimum width for buttons
 * 
 * @property inputHeight Height for input fields
 * @property inputMinWidth Minimum width for input fields
 * 
 * @property cardPadding Internal padding for cards
 * @property cardSpacing Spacing between cards
 * @property cardElevation Elevation for cards
 * 
 * @property bottomSheetPeekHeight Peek height for bottom sheets
 * @property dialogMaxWidth Maximum width for dialogs
 * @property dialogPadding Internal padding for dialogs
 */
data class Dimensions(
    val space1: Dp,  // 4dp base
    val space2: Dp,  // 8dp
    val space3: Dp,  // 12dp
    val space4: Dp,  // 16dp
    val space5: Dp,  // 20dp
    val space6: Dp,  // 24dp
    val space8: Dp,  // 32dp
    val space10: Dp, // 40dp
    val space12: Dp, // 48dp
    val space16: Dp, // 64dp

    val contentPaddingSmall: Dp,
    val contentPaddingMedium: Dp,
    val contentPaddingLarge: Dp,

    val screenPaddingHorizontal: Dp,
    val screenPaddingVertical: Dp,

    val sectionSpacing: Dp,
    val itemSpacing: Dp,

    val iconTiny: Dp,
    val iconSmall: Dp,
    val iconMedium: Dp,
    val iconLarge: Dp,

    val avatarSmall: Dp,
    val avatarMedium: Dp,
    val avatarLarge: Dp,
    val avatarXLarge: Dp,

    val imageThumb: Dp,
    val imageSmall: Dp,
    val imageMedium: Dp,
    val imageLarge: Dp,
    val imageHero: Dp,

    val buttonHeightSmall: Dp,
    val buttonHeightMedium: Dp,
    val buttonHeightLarge: Dp,
    val buttonMinWidth: Dp,

    val inputHeight: Dp,
    val inputMinWidth: Dp,

    val cardPadding: Dp,
    val cardSpacing: Dp,
    val cardElevation: Dp,

    val bottomSheetPeekHeight: Dp,
    val dialogMaxWidth: Dp,
    val dialogPadding: Dp
)

/**
 * Dimension values optimized for small screens (mobile phones in portrait mode).
 * 
 * These dimensions prioritize space efficiency and accessibility on smaller screens
 * while maintaining usability and visual hierarchy.
 */
val smallDimensions = Dimensions(
    space1 = 4.dp,
    space2 = 8.dp,
    space3 = 12.dp,
    space4 = 16.dp,
    space5 = 20.dp,
    space6 = 24.dp,
    space8 = 32.dp,
    space10 = 40.dp,
    space12 = 48.dp,
    space16 = 64.dp,

    contentPaddingSmall = 8.dp,
    contentPaddingMedium = 12.dp,
    contentPaddingLarge = 16.dp,
    screenPaddingHorizontal = 16.dp,
    screenPaddingVertical = 8.dp,
    sectionSpacing = 24.dp,
    itemSpacing = 8.dp,

    iconTiny = 14.dp,
    iconSmall = 20.dp,
    iconMedium = 28.dp,
    iconLarge = 40.dp,

    avatarSmall = 28.dp,
    avatarMedium = 40.dp,
    avatarLarge = 56.dp,
    avatarXLarge = 80.dp,

    imageThumb = 64.dp,
    imageSmall = 96.dp,
    imageMedium = 160.dp,
    imageLarge = 240.dp,
    imageHero = 280.dp,

    buttonHeightSmall = 36.dp,
    buttonHeightMedium = 44.dp,
    buttonHeightLarge = 52.dp,
    buttonMinWidth = 88.dp,

    inputHeight = 44.dp,
    inputMinWidth = 120.dp,

    cardPadding = 12.dp,
    cardSpacing = 8.dp,
    cardElevation = 2.dp,

    bottomSheetPeekHeight = 56.dp,
    dialogMaxWidth = 280.dp,
    dialogPadding = 16.dp
)

/**
 * Dimension values optimized for compact screens (mobile phones in landscape mode, small tablets).
 * 
 * These dimensions provide a balance between space efficiency and comfort,
 * suitable for devices with limited screen real estate but more width than small screens.
 */
val compactDimensions = Dimensions(
    space1 = 4.dp,
    space2 = 8.dp,
    space3 = 12.dp,
    space4 = 16.dp,
    space5 = 20.dp,
    space6 = 24.dp,
    space8 = 32.dp,
    space10 = 40.dp,
    space12 = 48.dp,
    space16 = 64.dp,

    contentPaddingSmall = 16.dp,
    contentPaddingMedium = 20.dp,
    contentPaddingLarge = 24.dp,
    screenPaddingHorizontal = 24.dp,
    screenPaddingVertical = 16.dp,
    sectionSpacing = 32.dp,
    itemSpacing = 16.dp,

    iconTiny = 18.dp,
    iconSmall = 26.dp,
    iconMedium = 34.dp,
    iconLarge = 50.dp,

    avatarSmall = 36.dp,
    avatarMedium = 52.dp,
    avatarLarge = 68.dp,
    avatarXLarge = 100.dp,

    imageThumb = 88.dp,
    imageSmall = 130.dp,
    imageMedium = 220.dp,
    imageLarge = 320.dp,
    imageHero = 350.dp,

    buttonHeightSmall = 44.dp,
    buttonHeightMedium = 52.dp,
    buttonHeightLarge = 60.dp,
    buttonMinWidth = 104.dp,

    inputHeight = 52.dp,
    inputMinWidth = 150.dp,

    cardPadding = 18.dp,
    cardSpacing = 14.dp,
    cardElevation = 4.dp,

    bottomSheetPeekHeight = 68.dp,
    dialogMaxWidth = 340.dp,
    dialogPadding = 22.dp
)

/**
 * Dimension values optimized for medium screens (tablets in portrait mode, large phones).
 * 
 * These dimensions take advantage of increased screen space to provide better visual
 * hierarchy and improved user experience with larger touch targets and more breathing room.
 */
val mediumDimensions = Dimensions(
    space1 = 4.dp,
    space2 = 8.dp,
    space3 = 12.dp,
    space4 = 16.dp,
    space5 = 20.dp,
    space6 = 24.dp,
    space8 = 32.dp,
    space10 = 40.dp,
    space12 = 48.dp,
    space16 = 64.dp,

    contentPaddingSmall = 16.dp,
    contentPaddingMedium = 20.dp,
    contentPaddingLarge = 24.dp,
    screenPaddingHorizontal = 24.dp,
    screenPaddingVertical = 16.dp,
    sectionSpacing = 40.dp,
    itemSpacing = 16.dp,

    iconTiny = 16.dp,
    iconSmall = 24.dp,
    iconMedium = 32.dp,
    iconLarge = 48.dp,

    avatarSmall = 36.dp,
    avatarMedium = 52.dp,
    avatarLarge = 72.dp,
    avatarXLarge = 108.dp,

    imageThumb = 96.dp,
    imageSmall = 140.dp,
    imageMedium = 240.dp,
    imageLarge = 360.dp,
    imageHero = 380.dp,

    buttonHeightSmall = 44.dp,
    buttonHeightMedium = 52.dp,
    buttonHeightLarge = 60.dp,
    buttonMinWidth = 112.dp,

    inputHeight = 52.dp,
    inputMinWidth = 160.dp,

    cardPadding = 20.dp,
    cardSpacing = 16.dp,
    cardElevation = 4.dp,

    bottomSheetPeekHeight = 72.dp,
    dialogMaxWidth = 400.dp,
    dialogPadding = 24.dp
)

/**
 * Dimension values optimized for large screens (tablets in landscape mode, desktop).
 * 
 * These dimensions maximize the use of available screen space with generous padding,
 * larger elements, and improved spacing for optimal desktop and large tablet experiences.
 */
val largeDimensions = Dimensions(
    space1 = 4.dp,
    space2 = 8.dp,
    space3 = 12.dp,
    space4 = 16.dp,
    space5 = 20.dp,
    space6 = 24.dp,
    space8 = 32.dp,
    space10 = 40.dp,
    space12 = 48.dp,
    space16 = 64.dp,

    contentPaddingSmall = 20.dp,
    contentPaddingMedium = 24.dp,
    contentPaddingLarge = 32.dp,
    screenPaddingHorizontal = 32.dp,
    screenPaddingVertical = 20.dp,
    sectionSpacing = 48.dp,
    itemSpacing = 20.dp,

    iconTiny = 18.dp,
    iconSmall = 28.dp,
    iconMedium = 36.dp,
    iconLarge = 56.dp,

    avatarSmall = 40.dp,
    avatarMedium = 56.dp,
    avatarLarge = 80.dp,
    avatarXLarge = 120.dp,

    imageThumb = 112.dp,
    imageSmall = 160.dp,
    imageMedium = 280.dp,
    imageLarge = 420.dp,
    imageHero = 440.dp,

    buttonHeightSmall = 48.dp,
    buttonHeightMedium = 56.dp,
    buttonHeightLarge = 64.dp,
    buttonMinWidth = 128.dp,

    inputHeight = 56.dp,
    inputMinWidth = 180.dp,

    cardPadding = 24.dp,
    cardSpacing = 20.dp,
    cardElevation = 6.dp,

    bottomSheetPeekHeight = 80.dp,
    dialogMaxWidth = 480.dp,
    dialogPadding = 32.dp
)