package com.gursimar.composive.responsive.configuration

import androidx.compose.runtime.Composable
import com.gursimar.composive.responsive.foundation.*
import com.gursimar.composive.responsive.core.getCurrentPlatform
import com.slapps.cupertino.adaptive.Theme
import androidx.compose.ui.text.font.FontFamily
import com.slapps.cupertino.theme.Typography as CupertinoTypography
import androidx.compose.material3.Typography as MaterialTypography
import androidx.compose.material3.ColorScheme as MaterialColorScheme
import com.slapps.cupertino.theme.ColorScheme as CupertinoColorScheme

/**
 * Gets the platform-appropriate default theme.
 * 
 * @return Theme.Material3 for Android platforms, Theme.Cupertino for others (iOS, Desktop)
 */
fun getDefaultThemeForPlatform(): Theme {
    val platform = getCurrentPlatform()
    return if (platform.name.contains("Android", ignoreCase = true)) {
        Theme.Material3
    } else {
        Theme.Cupertino
    }
}

/**
 * Creates a default responsive configuration with platform-appropriate theme.
 * 
 * **Smart defaults:**
 * - Android platforms: Uses Material 3 theme
 * - iOS/Desktop platforms: Uses Cupertino theme
 * 
 * This is the most common configuration that provides native-feeling apps
 * on each platform automatically.
 * 
 * @return ResponsiveConfiguration with platform-appropriate default theme
 */
fun defaultResponsiveConfiguration(): ResponsiveConfiguration {
    return ResponsiveConfiguration()
}


/**
 * Creates a responsive configuration that always uses Material 3 theme.
 * 
 * Use this when you want consistent Material Design across all platforms,
 * regardless of the platform (e.g., for brand consistency).
 * 
 * @return ResponsiveConfiguration with Material 3 as default theme
 */
fun materialResponsiveConfiguration(): ResponsiveConfiguration {
    return ResponsiveConfiguration(
        defaultThemeType = Theme.Material3,
        enablePlatformThemePreference = false
    )
}

/**
 * Creates a responsive configuration that always uses Cupertino theme.
 * 
 * Use this when you want consistent iOS-style design across all platforms,
 * regardless of the platform.
 * 
 * @return ResponsiveConfiguration with Cupertino as default theme
 */
fun cupertinoResponsiveConfiguration(): ResponsiveConfiguration {
    return ResponsiveConfiguration(
        defaultThemeType = Theme.Cupertino,
        enablePlatformThemePreference = false
    )
}

/**
 * Creates a responsive configuration with enhanced platform theme adaptation.
 * 
 * This enables dynamic theme switching that can override the default behavior:
 * - Useful for apps that want even more platform-specific behavior
 * - Can be combined with custom theme selection logic
 * 
 * **Note:** Since default themes are already platform-aware, this is primarily
 * useful for advanced customization scenarios.
 * 
 * @return ResponsiveConfiguration with platform-adaptive theme selection
 */
fun platformAdaptiveResponsiveConfiguration(): ResponsiveConfiguration {
    return ResponsiveConfiguration(
        enablePlatformThemePreference = true
    )
}

/**
 * @deprecated Use materialResponsiveConfiguration() instead for better clarity
 */
@Deprecated("Use materialResponsiveConfiguration() instead", ReplaceWith("materialResponsiveConfiguration()"))
internal fun defaultMaterialConfiguration(): ResponsiveConfiguration {
    return materialResponsiveConfiguration()
}

/**
 * @deprecated Use cupertinoResponsiveConfiguration() instead for better clarity
 */
@Deprecated("Use cupertinoResponsiveConfiguration() instead", ReplaceWith("cupertinoResponsiveConfiguration()"))
internal fun defaultCupertinoConfiguration(): ResponsiveConfiguration {
    return cupertinoResponsiveConfiguration()
}

/**
 * @deprecated Use platformAdaptiveResponsiveConfiguration() instead for better clarity
 */
@Deprecated("Use platformAdaptiveResponsiveConfiguration() instead", ReplaceWith("platformAdaptiveResponsiveConfiguration()"))
internal fun platformAdaptiveConfiguration(): ResponsiveConfiguration {
    return platformAdaptiveResponsiveConfiguration()
}

/**
 * Builder class for creating custom responsive configurations with a fluent API.
 * 
 * This builder allows you to customize every aspect of the responsive system,
 * from dimensions and typography to colors and theme preferences.
 * 
 * **Default behavior:** The configuration starts with platform-appropriate defaults
 * (Material 3 on Android, Cupertino on iOS/Desktop) and you can override any aspect.
 * 
 * @sample
 * ```kotlin
 * val config = ResponsiveConfigurationBuilder()
 *     .withMaterialTheme() // Override platform default
 *     .withCustomMaterialFonts(
 *         displayFont = myDisplayFont,
 *         bodyFont = myBodyFont
 *     )
 *     .withCustomDimensions(
 *         small = myCustomSmallDimensions,
 *         large = myCustomLargeDimensions
 *     )
 *     .build()
 * ```
 */
class ResponsiveConfigurationBuilder {
    private var customDimensions: ResponsiveDimensions? = null
    private var customFontWeights: ResponsiveFontWeightConfiguration? = null
    private var customMaterialFontResources: MaterialFontResources? = null
    private var customCupertinoFontResources: CupertinoFontResources? = null
    private var customCupertinoTypography: ResponsiveCupertinoTypography? = null
    private var customMaterialTypography: ResponsiveMaterialTypography? = null
    private var customMaterialColors: ResponsiveMaterialColors? = null
    private var customCupertinoColors: ResponsiveCupertinoColors? = null
    private var defaultThemeType: Theme? = null // null means use platform default
    private var enablePlatformThemePreference: Boolean = false

    /**
     * Force Material 3 theme on all platforms.
     * This overrides the platform-aware default behavior.
     * 
     * @return This builder for method chaining
     */
    fun withMaterialTheme() = apply {
        defaultThemeType = Theme.Material3
    }

    /**
     * Force Cupertino theme on all platforms.
     * This overrides the platform-aware default behavior.
     * 
     * @return This builder for method chaining
     */
    fun withCupertinoTheme() = apply {
        defaultThemeType = Theme.Cupertino
    }

    /**
     * Use platform-appropriate default theme.
     * This explicitly sets the behavior to use platform defaults:
     * - Android: Material 3
     * - iOS/Desktop: Cupertino
     * 
     * @return This builder for method chaining
     */
    fun withPlatformDefaultTheme() = apply {
        defaultThemeType = getDefaultThemeForPlatform()
    }

    /**
     * Enable enhanced platform-adaptive theme selection.
     * 
     * @param enabled Whether to enable enhanced platform adaptation (default: true)
     * @return This builder for method chaining
     */
    fun withPlatformThemeAdaptation(enabled: Boolean = true) = apply {
        enablePlatformThemePreference = enabled
    }

    /**
     * Set custom Material Design font families.
     * 
     * This allows you to specify custom fonts for different text styles in Material Design.
     * Any font that is null will use the default system font.
     * 
     * @param displayFont Font family for display text (largest text, headlines)
     * @param headlineFont Font family for headlines
     * @param titleFont Font family for titles
     * @param bodyFont Font family for body text
     * @param labelFont Font family for labels and small UI text
     * @return This builder for method chaining
     * 
     * @sample
     * ```kotlin
     * responsiveConfiguration {
     *     withCustomMaterialFonts(
     *         displayFont = myBrandFont,
     *         bodyFont = myReadingFont
     *     )
     * }
     * ```
     */
    fun withCustomMaterialFonts(
        displayFont: FontFamily? = null,
        headlineFont: FontFamily? = null,
        titleFont: FontFamily? = null,
        bodyFont: FontFamily? = null,
        labelFont: FontFamily? = null
    ) = apply {
        customMaterialFontResources = MaterialFontResources(
            displayFont = displayFont,
            headlineFont = headlineFont,
            titleFont = titleFont,
            bodyFont = bodyFont,
            labelFont = labelFont
        )
    }

    /**
     * Set custom Cupertino (iOS-style) font families.
     * 
     * This allows you to specify custom fonts for different text styles in Cupertino design.
     * Any font that is null will use the default system font.
     * 
     * @param largeTitleFont Font family for large titles
     * @param titleFont Font family for titles (title1, title2, title3)
     * @param headlineFont Font family for headlines
     * @param bodyFont Font family for body text
     * @param captionFont Font family for captions and small text
     * @return This builder for method chaining
     * 
     * @sample
     * ```kotlin
     * responsiveConfiguration {
     *     withCustomCupertinoFonts(
     *         largeTitleFont = myBrandFont,
     *         bodyFont = myReadingFont
     *     )
     * }
     * ```
     */
    fun withCustomCupertinoFonts(
        largeTitleFont: FontFamily? = null,
        titleFont: FontFamily? = null,
        headlineFont: FontFamily? = null,
        bodyFont: FontFamily? = null,
        captionFont: FontFamily? = null
    ) = apply {
        customCupertinoFontResources = CupertinoFontResources(
            largeTitleFont = largeTitleFont,
            titleFont = titleFont,
            headlineFont = headlineFont,
            bodyFont = bodyFont,
            captionFont = captionFont
        )
    }

    /**
     * Set the same font family for all Material Design text styles.
     * 
     * This is a convenience method when you want to use the same font
     * across all Material Design typography styles.
     * 
     * @param fontFamily The font family to use for all text styles
     * @return This builder for method chaining
     */
    fun withUniformMaterialFont(fontFamily: FontFamily) = apply {
        customMaterialFontResources = MaterialFontResources(
            displayFont = fontFamily,
            headlineFont = fontFamily,
            titleFont = fontFamily,
            bodyFont = fontFamily,
            labelFont = fontFamily
        )
    }

    /**
     * Set the same font family for all Cupertino text styles.
     * 
     * This is a convenience method when you want to use the same font
     * across all Cupertino typography styles.
     * 
     * @param fontFamily The font family to use for all text styles
     * @return This builder for method chaining
     */
    fun withUniformCupertinoFont(fontFamily: FontFamily) = apply {
        customCupertinoFontResources = cupertinoUniformFonts(fontFamily)
    }

    /**
     * Set fonts with reading and display separation for Material Design.
     * 
     * This is a common pattern where you use one font for reading content
     * and another for display/branding purposes.
     * 
     * @param readingFont Font for body and title text
     * @param displayFont Font for display and headline text
     * @param labelFont Font for labels (defaults to reading font)
     * @return This builder for method chaining
     */
    fun withMaterialReadingDisplayFonts(
        readingFont: FontFamily,
        displayFont: FontFamily,
        labelFont: FontFamily = readingFont
    ) = apply {
        customMaterialFontResources = materialFonts(readingFont, displayFont, labelFont)
    }

    /**
     * Set fonts with reading and display separation for Cupertino.
     * 
     * This is a common pattern where you use one font for reading content
     * and another for display/branding purposes.
     * 
     * @param readingFont Font for body and regular title text
     * @param displayFont Font for large titles and headlines
     * @param captionFont Font for captions (defaults to reading font)
     * @return This builder for method chaining
     */
    fun withCupertinoReadingDisplayFonts(
        readingFont: FontFamily,
        displayFont: FontFamily,
        captionFont: FontFamily = readingFont
    ) = apply {
        customCupertinoFontResources = cupertinoFonts(readingFont, displayFont, captionFont)
    }

    /**
     * Set the same font for both Material and Cupertino themes.
     * 
     * This is useful when you want consistent branding across all platforms
     * regardless of the theme being used.
     * 
     * @param fontFamily The font family to use for all themes and text styles
     * @return This builder for method chaining
     */
    fun withUniversalFont(fontFamily: FontFamily) = apply {
        customMaterialFontResources = materialUniformFonts(fontFamily)
        customCupertinoFontResources = cupertinoUniformFonts(fontFamily)
    }

    /**
     * Set custom dimensions for different screen sizes.
     * 
     * @param small Dimensions for small screens (mobile portrait)
     * @param compact Dimensions for compact screens (mobile landscape)
     * @param medium Dimensions for medium screens (tablets)
     * @param large Dimensions for large screens (desktop)
     * @return This builder for method chaining
     */
    fun withCustomDimensions(
        small: Dimensions = smallDimensions,
        compact: Dimensions = compactDimensions,
        medium: Dimensions = mediumDimensions,
        large: Dimensions = largeDimensions
    ) = apply {
        customDimensions = ResponsiveDimensions(small, compact, medium, large)
    }

    /**
     * Set custom font weights for different screen sizes.
     * 
     * @param small Font weights for small screens
     * @param compact Font weights for compact screens
     * @param medium Font weights for medium screens
     * @param large Font weights for large screens
     * @return This builder for method chaining
     */
    fun withCustomFontWeights(
        small: ResponsiveFontWeights = smallFontWeights,
        compact: ResponsiveFontWeights = compactFontWeights,
        medium: ResponsiveFontWeights = mediumFontWeights,
        large: ResponsiveFontWeights = largeFontWeights
    ) = apply {
        customFontWeights = ResponsiveFontWeightConfiguration(small, compact, medium, large)
    }

    /**
     * Set custom Cupertino typography for different screen sizes.
     * 
     * @param small Cupertino typography for small screens
     * @param compact Cupertino typography for compact screens
     * @param medium Cupertino typography for medium screens
     * @param large Cupertino typography for large screens
     * @return This builder for method chaining
     */
    fun withCustomCupertinoTypography(
        small: CupertinoTypography,
        compact: CupertinoTypography,
        medium: CupertinoTypography,
        large: CupertinoTypography
    ) = apply {
        customCupertinoTypography = ResponsiveCupertinoTypography(small, compact, medium, large)
    }

    /**
     * Set custom Material typography for different screen sizes.
     * 
     * @param small Material typography for small screens
     * @param compact Material typography for compact screens
     * @param medium Material typography for medium screens
     * @param large Material typography for large screens
     * @return This builder for method chaining
     */
    fun withCustomMaterialTypography(
        small: MaterialTypography,
        compact: MaterialTypography,
        medium: MaterialTypography,
        large: MaterialTypography
    ) = apply {
        customMaterialTypography = ResponsiveMaterialTypography(small, compact, medium, large)
    }

    /**
     * Set custom Material color schemes for light and dark themes.
     * 
     * @param light Material color scheme for light theme
     * @param dark Material color scheme for dark theme
     * @return This builder for method chaining
     */
    fun withCustomMaterialColors(
        light: MaterialColorScheme,
        dark: MaterialColorScheme
    ) = apply {
        customMaterialColors = ResponsiveMaterialColors(light, dark)
    }

    /**
     * Set custom Cupertino color schemes for light and dark themes.
     * 
     * @param light Cupertino color scheme for light theme
     * @param dark Cupertino color scheme for dark theme
     * @return This builder for method chaining
     */
    fun withCustomCupertinoColors(
        light: CupertinoColorScheme,
        dark: CupertinoColorScheme
    ) = apply {
        customCupertinoColors = ResponsiveCupertinoColors(light, dark)
    }

    /**
     * @deprecated Use withMaterialTheme() or withCupertinoTheme() instead
     */
    @Deprecated("Use withMaterialTheme() or withCupertinoTheme() instead")
    internal fun defaultTheme(themeType: Theme) = apply {
        defaultThemeType = themeType
    }

    /**
     * @deprecated Use withPlatformThemeAdaptation() instead
     */
    @Deprecated("Use withPlatformThemeAdaptation() instead")
    internal fun enablePlatformAdaptive(enabled: Boolean = true) = apply {
        enablePlatformThemePreference = enabled
    }

    /**
     * @deprecated Use withCustomDimensions() instead
     */
    @Deprecated("Use withCustomDimensions() instead")
    internal fun dimensions(
        small: Dimensions = smallDimensions,
        compact: Dimensions = compactDimensions,
        medium: Dimensions = mediumDimensions,
        large: Dimensions = largeDimensions
    ) = apply {
        customDimensions = ResponsiveDimensions(small, compact, medium, large)
    }

    /**
     * @deprecated Use withCustomFontWeights() instead
     */
    @Deprecated("Use withCustomFontWeights() instead")
    internal fun fontWeights(
        small: ResponsiveFontWeights = smallFontWeights,
        compact: ResponsiveFontWeights = compactFontWeights,
        medium: ResponsiveFontWeights = mediumFontWeights,
        large: ResponsiveFontWeights = largeFontWeights
    ) = apply {
        customFontWeights = ResponsiveFontWeightConfiguration(small, compact, medium, large)
    }

    /**
     * @deprecated Use withCustomCupertinoTypography() instead
     */
    @Deprecated("Use withCustomCupertinoTypography() instead")
    internal fun cupertinoTypography(
        small: CupertinoTypography,
        compact: CupertinoTypography,
        medium: CupertinoTypography,
        large: CupertinoTypography
    ) = apply {
        customCupertinoTypography = ResponsiveCupertinoTypography(small, compact, medium, large)
    }

    /**
     * @deprecated Use withCustomMaterialTypography() instead
     */
    @Deprecated("Use withCustomMaterialTypography() instead")
    internal fun materialTypography(
        small: MaterialTypography,
        compact: MaterialTypography,
        medium: MaterialTypography,
        large: MaterialTypography
    ) = apply {
        customMaterialTypography = ResponsiveMaterialTypography(small, compact, medium, large)
    }

    /**
     * @deprecated Use withCustomMaterialColors() instead
     */
    @Deprecated("Use withCustomMaterialColors() instead")
    internal fun materialColors(
        light: MaterialColorScheme,
        dark: MaterialColorScheme
    ) = apply {
        customMaterialColors = ResponsiveMaterialColors(light, dark)
    }

    /**
     * @deprecated Use withCustomCupertinoColors() instead
     */
    @Deprecated("Use withCustomCupertinoColors() instead")
    internal fun cupertinoColors(
        light: CupertinoColorScheme,
        dark: CupertinoColorScheme
    ) = apply {
        customCupertinoColors = ResponsiveCupertinoColors(light, dark)
    }

    /**
     * Build the responsive configuration with all specified customizations.
     * 
     * @return The configured ResponsiveConfiguration with platform-aware defaults
     */
    fun build(): ResponsiveConfiguration {
        return ResponsiveConfiguration(
            customDimensions = customDimensions,
            customFontWeights = customFontWeights,
            customMaterialFontResources = customMaterialFontResources,
            customCupertinoFontResources = customCupertinoFontResources,
            customCupertinoTypography = customCupertinoTypography,
            customMaterialTypography = customMaterialTypography,
            customMaterialColors = customMaterialColors,
            customCupertinoColors = customCupertinoColors,
            defaultThemeType = defaultThemeType ?: getDefaultThemeForPlatform(),
            enablePlatformThemePreference = enablePlatformThemePreference
        )
    }
}

/**
 * DSL function for building responsive configurations with a fluent API.
 * 
 * This is the recommended way to create custom responsive configurations.
 * It provides a clean, readable syntax for configuring all aspects of the
 * responsive system.
 * 
 * @param builder Configuration block using ResponsiveConfigurationBuilder
 * @return The configured ResponsiveConfiguration
 * 
 * @sample
 * ```kotlin
 * val config = responsiveConfiguration {
 *     withMaterialTheme()
 *     withPlatformThemeAdaptation()
 *     withCustomDimensions(
 *         small = myCustomSmallDimensions,
 *         large = myCustomLargeDimensions
 *     )
 * }
 * 
 * ComposiveTheme(configuration = config) {
 *     // Your app content
 * }
 * ```
 */
fun responsiveConfiguration(builder: ResponsiveConfigurationBuilder.() -> Unit): ResponsiveConfiguration {
    return ResponsiveConfigurationBuilder().apply(builder).build()
}

/**
 * Helper function to get default responsive Cupertino typography configurations.
 * 
 * This is an internal helper function used by the library. Users should access
 * typography through the responsive theme system instead.
 * 
 * @return ResponsiveCupertinoTypography with default values for all screen sizes
 */
@Composable
internal fun getDefaultResponsiveCupertinoTypography(): ResponsiveCupertinoTypography {
    return ResponsiveCupertinoTypography(
        small = cupertinoTypographySmall,
        compact = cupertinoTypographyCompact,
        medium = cupertinoTypographyMedium,
        large = cupertinoTypographyBig
    )
}

/**
 * Helper function to get default responsive Material typography configurations.
 * 
 * This is an internal helper function used by the library. Users should access
 * typography through the responsive theme system instead.
 * 
 * @return ResponsiveMaterialTypography with default values for all screen sizes
 */
@Composable
internal fun getDefaultResponsiveMaterialTypography(): ResponsiveMaterialTypography {
    return ResponsiveMaterialTypography(
        small = materialTypographySmall,
        compact = materialTypographyCompact,
        medium = materialTypographyMedium,
        large = materialTypographyBig
    )
} 