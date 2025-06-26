package com.gursimar.composive.responsive.foundation

import androidx.compose.ui.text.font.FontWeight

/**
 * Data class that defines responsive font weights for different UI elements and screen sizes.
 * 
 * This class provides both basic font weight levels and semantic font weights for specific
 * UI elements, allowing for consistent typography across different screen sizes while
 * maintaining appropriate visual hierarchy.
 * 
 * @property light Light font weight for subtle text
 * @property normal Normal/regular font weight for standard body text
 * @property medium Medium font weight for emphasized text
 * @property semiBold Semi-bold font weight for important text
 * @property bold Bold font weight for headings and strong emphasis
 * @property extraBold Extra bold font weight for display text and major headings
 * 
 * @property body Font weight for body/paragraph text
 * @property emphasis Font weight for emphasized inline text
 * @property heading Font weight for section headings
 * @property display Font weight for display/hero text
 * @property button Font weight for button text
 * @property caption Font weight for captions and metadata
 */
data class ResponsiveFontWeights(
    val light: FontWeight,
    val normal: FontWeight,
    val medium: FontWeight,
    val semiBold: FontWeight,
    val bold: FontWeight,
    val extraBold: FontWeight,

    val body: FontWeight,
    val emphasis: FontWeight,
    val heading: FontWeight,
    val display: FontWeight,
    val button: FontWeight,
    val caption: FontWeight
)

/**
 * Font weights optimized for small screens (mobile phones in portrait mode).
 * 
 * These weights prioritize readability on smaller screens with subtle weight differences
 * to ensure text remains legible while maintaining visual hierarchy.
 */
val smallFontWeights = ResponsiveFontWeights(
    light = FontWeight.Light,
    normal = FontWeight.Normal,
    medium = FontWeight.Medium,
    semiBold = FontWeight.SemiBold,
    bold = FontWeight.Bold,
    extraBold = FontWeight.ExtraBold,

    body = FontWeight.Normal,
    emphasis = FontWeight.Medium,
    heading = FontWeight.SemiBold,
    display = FontWeight.Bold,
    button = FontWeight.Medium,
    caption = FontWeight.Normal
)

/**
 * Font weights optimized for compact screens (mobile phones in landscape, small tablets).
 * 
 * These weights use slightly stronger contrast to take advantage of the increased
 * screen space while maintaining excellent readability.
 */
val compactFontWeights = ResponsiveFontWeights(
    light = FontWeight.Light,
    normal = FontWeight.Normal,
    medium = FontWeight.Medium,
    semiBold = FontWeight.SemiBold,
    bold = FontWeight.Bold,
    extraBold = FontWeight.ExtraBold,

    body = FontWeight.Normal,
    emphasis = FontWeight.SemiBold,
    heading = FontWeight.Bold,
    display = FontWeight.ExtraBold,
    button = FontWeight.SemiBold,
    caption = FontWeight.Normal
)

/**
 * Font weights optimized for medium screens (tablets in portrait mode, large phones).
 * 
 * These weights provide enhanced visual hierarchy with stronger weight differences,
 * suitable for the increased viewing distance and screen real estate of medium devices.
 */
val mediumFontWeights = ResponsiveFontWeights(
    light = FontWeight.Light,
    normal = FontWeight.Normal,
    medium = FontWeight.Medium,
    semiBold = FontWeight.SemiBold,
    bold = FontWeight.Bold,
    extraBold = FontWeight.ExtraBold,

    body = FontWeight.Normal,
    emphasis = FontWeight.SemiBold,
    heading = FontWeight.Bold,
    display = FontWeight.ExtraBold,
    button = FontWeight.SemiBold,
    caption = FontWeight.Medium
)

/**
 * Font weights optimized for large screens (tablets in landscape mode, desktop).
 * 
 * These weights maximize visual impact and hierarchy with strong weight contrasts,
 * perfect for the increased viewing distances and larger display areas of desktop environments.
 */
val largeFontWeights = ResponsiveFontWeights(
    light = FontWeight.Light,
    normal = FontWeight.Normal,
    medium = FontWeight.Medium,
    semiBold = FontWeight.SemiBold,
    bold = FontWeight.Bold,
    extraBold = FontWeight.ExtraBold,

    body = FontWeight.Normal,
    emphasis = FontWeight.Bold,
    heading = FontWeight.ExtraBold,
    display = FontWeight.ExtraBold,
    button = FontWeight.Bold,
    caption = FontWeight.Medium
) 