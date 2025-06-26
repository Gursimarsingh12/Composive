package com.gursimar.composive.responsive.foundation

import androidx.compose.material3.Typography as MaterialTypography
import com.slapps.cupertino.theme.Typography as CupertinoTypography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gursimar.composive.responsive.configuration.ResponsiveCupertinoTypography
import com.gursimar.composive.responsive.configuration.ResponsiveMaterialTypography

/**
 * Data class to hold custom font families for Material Design typography.
 * 
 * This allows you to specify different font families for different text styles
 * in Material Design components. If a font family is null, the default system font will be used.
 * 
 * @param displayFont Font family for display text (largest text, headlines)
 * @param headlineFont Font family for headlines
 * @param titleFont Font family for titles
 * @param bodyFont Font family for body text
 * @param labelFont Font family for labels and small UI text
 */
data class MaterialFontResources(
    val displayFont: FontFamily? = null,
    val headlineFont: FontFamily? = null,
    val titleFont: FontFamily? = null,
    val bodyFont: FontFamily? = null,
    val labelFont: FontFamily? = null
)

/**
 * Data class to hold custom font families for Cupertino typography.
 * 
 * This allows you to specify different font families for different text styles
 * in Cupertino (iOS-style) components. If a font family is null, the default system font will be used.
 * 
 * @param largeTitleFont Font family for large titles
 * @param titleFont Font family for titles (title1, title2, title3)
 * @param headlineFont Font family for headlines
 * @param bodyFont Font family for body text
 * @param captionFont Font family for captions and small text
 */
data class CupertinoFontResources(
    val largeTitleFont: FontFamily? = null,
    val titleFont: FontFamily? = null,
    val headlineFont: FontFamily? = null,
    val bodyFont: FontFamily? = null,
    val captionFont: FontFamily? = null
)

/**
 * Material Design typography configuration optimized for small screens.
 * 
 * This typography scale is designed for mobile phones in portrait mode,
 * with font sizes that ensure readability while maximizing content density.
 * All text styles use consistent font weights for visual hierarchy.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun materialTypographySmall(fontResources: MaterialFontResources? = null) = MaterialTypography(
    bodyLarge = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    displayLarge = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

/**
 * Backward compatibility: Material Design typography for small screens without font customization
 */
val materialTypographySmall @Composable get() = materialTypographySmall()

/**
 * Material Design typography configuration optimized for compact screens.
 * 
 * This typography scale is designed for mobile phones in landscape mode and small tablets,
 * with slightly larger font sizes to take advantage of the increased screen real estate
 * while maintaining excellent readability.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun materialTypographyCompact(fontResources: MaterialFontResources? = null) = MaterialTypography(
    bodyLarge = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 29.sp
    ),
    displayLarge = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

/**
 * Backward compatibility: Material Design typography for compact screens without font customization
 */
val materialTypographyCompact @Composable get() = materialTypographyCompact()

/**
 * Material Design typography configuration optimized for medium screens.
 * 
 * This typography scale is designed for tablets in portrait mode and large phones,
 * with increased font sizes that provide better visual hierarchy and improved
 * readability at typical tablet viewing distances.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun materialTypographyMedium(fontResources: MaterialFontResources? = null) = MaterialTypography(
    bodyLarge = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    displayLarge = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

/**
 * Backward compatibility: Material Design typography for medium screens without font customization
 */
val materialTypographyMedium @Composable get() = materialTypographyMedium()

/**
 * Material Design typography configuration optimized for large screens.
 * 
 * This typography scale is designed for tablets in landscape mode and desktop environments,
 * with generous font sizes that maximize readability and visual impact at desktop
 * viewing distances while maintaining elegant proportions.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun materialTypographyBig(fontResources: MaterialFontResources? = null) = MaterialTypography(
    bodyLarge = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 29.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp  
    ),
    titleSmall = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp
    ),
    displayLarge = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 39.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontResources?.displayFont,
        fontWeight = FontWeight.Normal,
        fontSize = 33.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontResources?.labelFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
)

/**
 * Backward compatibility: Material Design typography for large screens without font customization
 */
val materialTypographyBig @Composable get() = materialTypographyBig()

/**
 * Cupertino typography configuration optimized for small screens.
 * 
 * This typography scale follows Apple's Human Interface Guidelines for iOS,
 * optimized for mobile phones with font sizes that ensure excellent readability
 * while maintaining the clean, minimal aesthetic of iOS applications.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun cupertinoTypographySmall(fontResources: CupertinoFontResources? = null) = CupertinoTypography(
    largeTitle = TextStyle(
        fontFamily = fontResources?.largeTitleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    title1 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    title2 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    title3 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    headline = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    body = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    callout = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    subhead = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    footnote = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    caption1 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,  
        fontSize = 11.sp
    ),
    caption2 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

/**
 * Backward compatibility: Cupertino typography for small screens without font customization
 */
val cupertinoTypographySmall @Composable get() = cupertinoTypographySmall()

/**
 * Cupertino typography configuration optimized for compact screens.
 * 
 * This typography scale follows Apple's Human Interface Guidelines,
 * adapted for mobile phones in landscape mode and small tablets with
 * slightly increased font sizes for improved readability.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun cupertinoTypographyCompact(fontResources: CupertinoFontResources? = null) = CupertinoTypography(
    largeTitle = TextStyle(
        fontFamily = fontResources?.largeTitleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp
    ),
    title1 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    title2 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp
    ),
    title3 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    headline = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    body = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    callout = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    subhead = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    footnote = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption1 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    caption2 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

/**
 * Backward compatibility: Cupertino typography for compact screens without font customization
 */
val cupertinoTypographyCompact @Composable get() = cupertinoTypographyCompact()

/**
 * Cupertino typography configuration optimized for medium screens.
 * 
 * This typography scale follows Apple's Human Interface Guidelines,
 * adapted for tablets in portrait mode with enhanced font sizes that
 * provide better visual hierarchy and readability at tablet viewing distances.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun cupertinoTypographyMedium(fontResources: CupertinoFontResources? = null) = CupertinoTypography(
    largeTitle = TextStyle(
        fontFamily = fontResources?.largeTitleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    title1 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp
    ),
    title2 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 27.sp
    ),
    title3 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    headline = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    body = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    callout = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp
    ),
    subhead = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    footnote = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    caption1 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    caption2 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

/**
 * Backward compatibility: Cupertino typography for medium screens without font customization
 */
val cupertinoTypographyMedium @Composable get() = cupertinoTypographyMedium()

/**
 * Cupertino typography configuration optimized for large screens.
 * 
 * This typography scale follows Apple's Human Interface Guidelines,
 * adapted for tablets in landscape mode and desktop environments with
 * generous font sizes that maximize visual impact and readability at
 * desktop viewing distances.
 * 
 * @param fontResources Optional custom font families to use instead of system fonts
 */
@Composable
fun cupertinoTypographyBig(fontResources: CupertinoFontResources? = null) = CupertinoTypography(
    largeTitle = TextStyle(
        fontFamily = fontResources?.largeTitleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    title1 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 42.sp
    ),
    title2 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    title3 = TextStyle(
        fontFamily = fontResources?.titleFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headline = TextStyle(
        fontFamily = fontResources?.headlineFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    ),
    body = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp
    ),
    callout = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    subhead = TextStyle(
        fontFamily = fontResources?.bodyFont,
        fontWeight = FontWeight.Normal,
        fontSize = 23.sp
    ),
    footnote = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    caption1 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    caption2 = TextStyle(
        fontFamily = fontResources?.captionFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    )
)

/**
 * Backward compatibility: Cupertino typography for large screens without font customization
 */
val cupertinoTypographyBig @Composable get() = cupertinoTypographyBig()

/**
 * Builder function to easily create MaterialFontResources with common font configurations.
 * 
 * @sample
 * ```kotlin
 * val myFonts = materialFontResources {
 *     setAllFonts(MyCustomFontFamily) // Use same font for all text styles
 * }
 * 
 * // Or configure individual fonts
 * val myFonts = materialFontResources {
 *     displayFont = MyDisplayFont
 *     headlineFont = MyHeadlineFont  
 *     bodyFont = MyBodyFont
 * }
 * ```
 */
fun materialFontResources(builder: MaterialFontResourcesBuilder.() -> Unit): MaterialFontResources {
    return MaterialFontResourcesBuilder().apply(builder).build()
}

/**
 * Builder class for MaterialFontResources
 */
class MaterialFontResourcesBuilder {
    var displayFont: FontFamily? = null
    var headlineFont: FontFamily? = null
    var titleFont: FontFamily? = null
    var bodyFont: FontFamily? = null
    var labelFont: FontFamily? = null
    
    /**
     * Set all font families to the same font
     */
    fun setAllFonts(fontFamily: FontFamily) {
        displayFont = fontFamily
        headlineFont = fontFamily
        titleFont = fontFamily
        bodyFont = fontFamily
        labelFont = fontFamily
    }
    
    /**
     * Set reading fonts (body and label) to one font, and display fonts to another
     */
    fun setReadingFonts(readingFont: FontFamily, displayFont: FontFamily) {
        this.bodyFont = readingFont
        this.labelFont = readingFont
        this.displayFont = displayFont
        this.headlineFont = displayFont
        this.titleFont = displayFont
    }
    
    internal fun build() = MaterialFontResources(
        displayFont = displayFont,
        headlineFont = headlineFont,
        titleFont = titleFont,
        bodyFont = bodyFont,
        labelFont = labelFont
    )
}

/**
 * Builder function to easily create CupertinoFontResources with common font configurations.
 * 
 * @sample
 * ```kotlin
 * val myFonts = cupertinoFontResources {
 *     setAllFonts(MyCustomFontFamily) // Use same font for all text styles
 * }
 * 
 * // Or configure individual fonts
 * val myFonts = cupertinoFontResources {
 *     largeTitleFont = MyLargeTitleFont
 *     titleFont = MyTitleFont
 *     bodyFont = MyBodyFont
 * }
 * ```
 */
fun cupertinoFontResources(builder: CupertinoFontResourcesBuilder.() -> Unit): CupertinoFontResources {
    return CupertinoFontResourcesBuilder().apply(builder).build()
}

/**
 * Builder class for CupertinoFontResources
 */
class CupertinoFontResourcesBuilder {
    var largeTitleFont: FontFamily? = null
    var titleFont: FontFamily? = null
    var headlineFont: FontFamily? = null
    var bodyFont: FontFamily? = null
    var captionFont: FontFamily? = null
    
    /**
     * Set all font families to the same font
     */
    fun setAllFonts(fontFamily: FontFamily) {
        largeTitleFont = fontFamily
        titleFont = fontFamily
        headlineFont = fontFamily
        bodyFont = fontFamily
        captionFont = fontFamily
    }
    
    /**
     * Set reading fonts (body and caption) to one font, and display fonts to another
     */
    fun setReadingFonts(readingFont: FontFamily, displayFont: FontFamily) {
        this.bodyFont = readingFont
        this.captionFont = readingFont
        this.largeTitleFont = displayFont
        this.titleFont = displayFont
        this.headlineFont = displayFont
    }
    
    internal fun build() = CupertinoFontResources(
        largeTitleFont = largeTitleFont,
        titleFont = titleFont,
        headlineFont = headlineFont,
        bodyFont = bodyFont,
        captionFont = captionFont
    )
}

/**
 * Extension function to create Material typography with font resources for all screen sizes.
 * 
 * @param fontResources The font resources to apply
 * @return Responsive typography configurations for all screen sizes
 * 
 * @sample
 * ```kotlin
 * val myFonts = materialFontResources {
 *     setAllFonts(MyCustomFont)
 * }
 * 
 * val responsiveTypography = myFonts.createMaterialTypography()
 * // Use in configuration: customMaterialTypography = responsiveTypography
 * ```
 */
@Composable
fun MaterialFontResources.createMaterialTypography() = ResponsiveMaterialTypography(
    small = materialTypographySmall(this),
    compact = materialTypographyCompact(this),
    medium = materialTypographyMedium(this),
    large = materialTypographyBig(this)
)

/**
 * Extension function to create Cupertino typography with font resources for all screen sizes.
 * 
 * @param fontResources The font resources to apply
 * @return Responsive typography configurations for all screen sizes
 * 
 * @sample
 * ```kotlin
 * val myFonts = cupertinoFontResources {
 *     setAllFonts(MyCustomFont)
 * }
 * 
 * val responsiveTypography = myFonts.createCupertinoTypography()
 * // Use in configuration: customCupertinoTypography = responsiveTypography
 * ```
 */
@Composable
fun CupertinoFontResources.createCupertinoTypography() = ResponsiveCupertinoTypography(
    small = cupertinoTypographySmall(this),
    compact = cupertinoTypographyCompact(this),
    medium = cupertinoTypographyMedium(this),
    large = cupertinoTypographyBig(this)
)

/**
 * Creates Material font resources with reading and display font separation.
 * 
 * This is a common pattern where you want one font for reading (body, title) 
 * and another for display/branding (display, headline).
 * 
 * @param readingFont Font family for body and title text
 * @param displayFont Font family for display and headline text
 * @param labelFont Font family for labels (defaults to reading font)
 * @return MaterialFontResources configured with the specified fonts
 */
fun materialFonts(
    readingFont: FontFamily,
    displayFont: FontFamily = readingFont,
    labelFont: FontFamily = readingFont
): MaterialFontResources {
    return MaterialFontResources(
        displayFont = displayFont,
        headlineFont = displayFont,
        titleFont = readingFont,
        bodyFont = readingFont,
        labelFont = labelFont
    )
}

/**
 * Creates Cupertino font resources with reading and display font separation.
 * 
 * This is a common pattern where you want one font for reading (body, title) 
 * and another for display/branding (large title, headline).
 * 
 * @param readingFont Font family for body and regular title text
 * @param displayFont Font family for large titles and headlines
 * @param captionFont Font family for captions (defaults to reading font)
 * @return CupertinoFontResources configured with the specified fonts
 */
fun cupertinoFonts(
    readingFont: FontFamily,
    displayFont: FontFamily = readingFont,
    captionFont: FontFamily = readingFont
): CupertinoFontResources {
    return CupertinoFontResources(
        largeTitleFont = displayFont,
        titleFont = readingFont,
        headlineFont = displayFont,
        bodyFont = readingFont,
        captionFont = captionFont
    )
}

/**
 * Creates Material font resources using a single font family for all text styles.
 * 
 * @param fontFamily The font family to use for all text styles
 * @return MaterialFontResources with the same font for all styles
 */
fun materialUniformFonts(fontFamily: FontFamily): MaterialFontResources {
    return MaterialFontResources(
        displayFont = fontFamily,
        headlineFont = fontFamily,
        titleFont = fontFamily,
        bodyFont = fontFamily,
        labelFont = fontFamily
    )
}

/**
 * Creates Cupertino font resources using a single font family for all text styles.
 * 
 * @param fontFamily The font family to use for all text styles
 * @return CupertinoFontResources with the same font for all styles
 */
fun cupertinoUniformFonts(fontFamily: FontFamily): CupertinoFontResources {
    return CupertinoFontResources(
        largeTitleFont = fontFamily,
        titleFont = fontFamily,
        headlineFont = fontFamily,
        bodyFont = fontFamily,
        captionFont = fontFamily
    )
} 