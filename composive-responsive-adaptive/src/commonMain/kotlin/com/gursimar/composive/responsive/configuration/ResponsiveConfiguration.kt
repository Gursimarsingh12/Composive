package com.gursimar.composive.responsive.configuration

import androidx.compose.runtime.Immutable
import com.gursimar.composive.responsive.foundation.*
import com.gursimar.composive.responsive.core.getCurrentPlatform
import com.slapps.cupertino.adaptive.Theme
import com.slapps.cupertino.theme.Typography as CupertinoTypography
import androidx.compose.material3.Typography as MaterialTypography
import androidx.compose.material3.ColorScheme as MaterialColorScheme
import com.slapps.cupertino.theme.ColorScheme as CupertinoColorScheme

/**
 * Configuration class for customizing the responsive behavior and theming of your app.
 * 
 * This is the main configuration class that allows you to override default responsive behavior
 * and provide custom dimensions, typography, colors, and theme preferences for your application.
 * 
 * **Key Features:**
 * - Customize dimensions for different screen sizes (mobile, tablet, desktop)
 * - Override typography scales for both Material and Cupertino themes
 * - **Set custom font families** for different text styles and screen sizes
 * - Provide custom color schemes for light and dark themes
 * - Control theme selection (Material 3, Cupertino, or platform-adaptive)
 * - All parameters are optional with sensible defaults
 * - **Smart theme defaults**: Android gets Material 3, iOS/Desktop get Cupertino
 * 
 * **Usage:**
 * ```kotlin
 * // Uses platform-appropriate theme automatically
 * ComposiveTheme {
 *     MyApp()
 * }
 * 
 * // Or customize with custom fonts
 * ComposiveTheme(
 *     configuration = responsiveConfiguration {
 *         withMaterialTheme()
 *         withCustomMaterialFonts(
 *             displayFont = myDisplayFont,
 *             bodyFont = myBodyFont
 *         )
 *         withCustomDimensions(
 *             small = myCustomMobileDimensions,
 *             large = myCustomDesktopDimensions
 *         )
 *     }
 * ) {
 *     MyApp()
 * }
 * ```
 * 
 * @param customDimensions Custom dimensions for different screen sizes. If null, default responsive dimensions will be used.
 * @param customFontWeights Custom font weights for different screen sizes. If null, default responsive font weights will be used.
 * @param customMaterialFontResources Custom font families for Material Design typography. If null, default system fonts will be used.
 * @param customCupertinoFontResources Custom font families for Cupertino typography. If null, default system fonts will be used.
 * @param customCupertinoTypography Custom Cupertino typography for different screen sizes. If null, default responsive Cupertino typography will be used.
 * @param customMaterialTypography Custom Material typography for different screen sizes. If null, default responsive Material typography will be used.
 * @param customMaterialColors Custom Material color schemes for light and dark themes. If null, default Material color schemes will be used.
 * @param customCupertinoColors Custom Cupertino color schemes for light and dark themes. If null, default Cupertino color schemes will be used.
 * @param defaultThemeType Default theme type to use when no specific theme is requested. **Defaults to platform-appropriate theme**: Android gets Material 3, others get Cupertino.
 * @param enablePlatformThemePreference Whether to enable automatic theme switching based on platform. When true, iOS will prefer Cupertino theme, others will prefer Material.
 */
@Immutable
data class ResponsiveConfiguration(
    /**
     * Custom dimensions for different screen sizes.
     * If null, default responsive dimensions will be used that automatically
     * scale based on device configuration (mobile portrait, mobile landscape,
     * tablet portrait, tablet landscape, desktop).
     */
    val customDimensions: ResponsiveDimensions? = null,
    
    /**
     * Custom font weights for different screen sizes.
     * If null, default responsive font weights will be used that provide
     * appropriate weight variations for different screen sizes and contexts.
     */
    val customFontWeights: ResponsiveFontWeightConfiguration? = null,
    
    /**
     * Custom font families for Material Design typography.
     * If null, default system fonts will be used. This allows you to set
     * custom fonts that will be applied across all Material typography styles.
     * 
     * Example: Set a custom brand font for headlines and body text.
     */
    val customMaterialFontResources: MaterialFontResources? = null,
    
    /**
     * Custom font families for Cupertino typography.
     * If null, default system fonts will be used. This allows you to set
     * custom fonts that will be applied across all Cupertino typography styles.
     * 
     * Example: Set a custom brand font for titles and body text.
     */
    val customCupertinoFontResources: CupertinoFontResources? = null,
    
    /**
     * Custom Cupertino typography for different screen sizes.
     * If null, default responsive Cupertino typography will be used that
     * follows iOS Human Interface Guidelines for different screen sizes.
     * 
     * Note: If customCupertinoFontResources is also set, the font resources
     * will be applied to these typography configurations.
     */
    val customCupertinoTypography: ResponsiveCupertinoTypography? = null,
    
    /**
     * Custom Material typography for different screen sizes.
     * If null, default responsive Material typography will be used that
     * follows Material Design typography guidelines for different screen sizes.
     * 
     * Note: If customMaterialFontResources is also set, the font resources
     * will be applied to these typography configurations.
     */
    val customMaterialTypography: ResponsiveMaterialTypography? = null,
    
    /**
     * Custom Material color schemes for light and dark themes.
     * If null, default Material color schemes will be used.
     * Supports both light and dark theme variants.
     */
    val customMaterialColors: ResponsiveMaterialColors? = null,
    
    /**
     * Custom Cupertino color schemes for light and dark themes.
     * If null, default Cupertino color schemes will be used.
     * Supports both light and dark theme variants.
     */
    val customCupertinoColors: ResponsiveCupertinoColors? = null,
    
    /**
     * Default theme type to use when no specific theme is requested.
     * Can be overridden when calling ComposiveTheme.
     * 
     * **Smart defaults:**
     * - Android platforms: Theme.Material3
     * - iOS/Desktop platforms: Theme.Cupertino
     * 
     * Options: Theme.Material3, Theme.Cupertino
     */
    val defaultThemeType: Theme = getDefaultThemeForPlatform(),
    
    /**
     * Whether to enable automatic theme switching based on platform.
     * When true:
     * - iOS apps will prefer Cupertino theme
     * - Android and other platforms will prefer Material theme
     * 
     * When false, uses defaultThemeType consistently across all platforms.
     * 
     * **Note:** This is now less relevant since defaultThemeType already adapts to platform.
     * This setting is mainly useful when you want to override the default behavior.
     */
    val enablePlatformThemePreference: Boolean = false
)

/**
 * Responsive dimensions configuration for different screen sizes.
 * 
 * This allows you to customize spacing, padding, and size values for different
 * responsive breakpoints. Each breakpoint corresponds to different device configurations:
 * 
 * - **Small**: Mobile phones in portrait orientation
 * - **Compact**: Mobile phones in landscape, small tablets
 * - **Medium**: Tablets in portrait, large phones
 * - **Large**: Desktop screens, large tablets in landscape
 * 
 * All dimensions automatically scale based on platform density and user preferences.
 * 
 * @param small Dimensions optimized for mobile portrait screens
 * @param compact Dimensions optimized for mobile landscape and compact screens
 * @param medium Dimensions optimized for tablet portrait and medium screens
 * @param large Dimensions optimized for desktop and large screens
 */
@Immutable
data class ResponsiveDimensions(
    val small: Dimensions = smallDimensions,
    val compact: Dimensions = compactDimensions,
    val medium: Dimensions = mediumDimensions,
    val large: Dimensions = largeDimensions
)

/**
 * Responsive font weights configuration for different screen sizes.
 * 
 * This allows you to customize font weights for different responsive breakpoints.
 * Different screen sizes may benefit from different font weight strategies for
 * optimal readability and visual hierarchy.
 * 
 * For example:
 * - Small screens might use lighter weights to maximize legibility
 * - Large screens can support heavier weights for better visual impact
 * - Desktop screens might use different weight relationships for better hierarchy
 * 
 * @param small Font weights optimized for small screens (mobile portrait)
 * @param compact Font weights optimized for compact screens (mobile landscape)
 * @param medium Font weights optimized for medium screens (tablets)
 * @param large Font weights optimized for large screens (desktop)
 */
@Immutable
data class ResponsiveFontWeightConfiguration(
    val small: ResponsiveFontWeights = smallFontWeights,
    val compact: ResponsiveFontWeights = compactFontWeights,
    val medium: ResponsiveFontWeights = mediumFontWeights,
    val large: ResponsiveFontWeights = largeFontWeights
)

/**
 * Responsive Cupertino typography configuration for different screen sizes.
 * 
 * This allows you to provide custom Cupertino (iOS-style) typography scales for different
 * responsive breakpoints. Each screen size can have its own optimized typography
 * scale following iOS Human Interface Guidelines.
 * 
 * **Benefits:**
 * - Consistent iOS look and feel across all screen sizes
 * - Optimized text sizes for different viewing distances
 * - Maintains Apple's design language and accessibility standards
 * 
 * @param small Cupertino typography optimized for mobile screens
 * @param compact Cupertino typography optimized for compact screens
 * @param medium Cupertino typography optimized for tablet screens
 * @param large Cupertino typography optimized for large screens
 */
@Immutable
data class ResponsiveCupertinoTypography(
    val small: CupertinoTypography,
    val compact: CupertinoTypography,
    val medium: CupertinoTypography,
    val large: CupertinoTypography
)

/**
 * Responsive Material typography configuration for different screen sizes.
 * 
 * This allows you to provide custom Material Design typography scales for different
 * responsive breakpoints. Each screen size can have its own optimized typography
 * scale following Material Design principles.
 * 
 * **Benefits:**
 * - Consistent Material Design look and feel across all screen sizes
 * - Follows Material Design typography guidelines and accessibility standards
 * - Optimized for different reading contexts and screen densities
 * 
 * @param small Material typography optimized for mobile screens
 * @param compact Material typography optimized for compact screens
 * @param medium Material typography optimized for tablet screens
 * @param large Material typography optimized for large screens
 */
@Immutable
data class ResponsiveMaterialTypography(
    val small: MaterialTypography,
    val compact: MaterialTypography,
    val medium: MaterialTypography,
    val large: MaterialTypography
)

/**
 * Responsive Material colors configuration for light and dark themes.
 * 
 * This allows you to provide custom Material Design color schemes that will be
 * used across all responsive breakpoints. You can define both light and dark
 * variants that will be automatically selected based on system theme preferences.
 * 
 * **Features:**
 * - Automatic light/dark theme switching
 * - Consistent colors across all screen sizes
 * - Follows Material Design color system
 * - Supports dynamic color on supported platforms
 * 
 * @param light Material color scheme for light theme
 * @param dark Material color scheme for dark theme
 */
@Immutable
data class ResponsiveMaterialColors(
    val light: MaterialColorScheme,
    val dark: MaterialColorScheme
)

/**
 * Responsive Cupertino colors configuration for light and dark themes.
 * 
 * This allows you to provide custom Cupertino (iOS-style) color schemes that will be
 * used across all responsive breakpoints. You can define both light and dark
 * variants that will be automatically selected based on system theme preferences.
 * 
 * **Features:**
 * - Automatic light/dark theme switching
 * - Consistent with iOS design language
 * - Supports iOS dynamic colors
 * - Follows iOS accessibility guidelines
 * 
 * @param light Cupertino color scheme for light theme
 * @param dark Cupertino color scheme for dark theme
 */
@Immutable
data class ResponsiveCupertinoColors(
    val light: CupertinoColorScheme,
    val dark: CupertinoColorScheme
) 