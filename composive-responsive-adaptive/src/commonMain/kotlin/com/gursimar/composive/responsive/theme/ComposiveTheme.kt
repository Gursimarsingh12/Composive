package com.gursimar.composive.responsive.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.gursimar.composive.responsive.core.*
import com.gursimar.composive.responsive.foundation.*
import com.gursimar.composive.responsive.configuration.*
import com.slapps.cupertino.adaptive.AdaptiveTheme
import com.slapps.cupertino.adaptive.CupertinoThemeSpec
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi
import com.slapps.cupertino.adaptive.MaterialThemeSpec
import com.slapps.cupertino.adaptive.Theme
import com.slapps.cupertino.theme.ColorScheme
import com.slapps.cupertino.theme.darkColorScheme
import com.slapps.cupertino.theme.lightColorScheme
import com.slapps.cupertino.theme.Typography as CupertinoTypography
import androidx.compose.material3.Typography as MaterialTypography
import androidx.compose.material3.ColorScheme as MaterialColorScheme

/**
 * Internal enum representing different responsive screen size categories.
 * This is used internally by the library to determine appropriate dimensions and typography.
 * Users should not access this directly - use the provided configurations instead.
 */
internal enum class ResponsiveSize {
    /** Small screens - mobile phones in portrait */
    SMALL,
    
    /** Compact screens - mobile phones in landscape, small tablets */
    COMPACT,
    
    /** Medium screens - tablets in portrait, large phones */
    MEDIUM,
    
    /** Large screens - tablets in landscape, desktop */
    LARGE
}

private val DarkCupertinoColorScheme: ColorScheme = darkColorScheme()
private val LightCupertinoColorScheme = lightColorScheme()
private val DarkMaterialColorScheme = androidx.compose.material3.darkColorScheme()
private val LightMaterialColorScheme = androidx.compose.material3.lightColorScheme()

val LocalResponsiveConfiguration = compositionLocalOf {
    ResponsiveConfiguration()
}

/**
 * Main theme composable that provides responsive design and cross-platform theming for your app.
 * 
 * This is the entry point for using the Composive responsive system. It automatically detects
 * screen sizes, device types, and applies appropriate dimensions, typography, and themes.
 * 
 * **Key features:**
 * - Automatic responsive behavior across all screen sizes
 * - Cross-platform theme support (Material 3 and Cupertino)
 * - **Smart theme defaults**: Android gets Material 3, iOS/Desktop get Cupertino
 * - Customizable dimensions, typography, and colors
 * - Platform-adaptive theme selection
 * 
 * **Default behavior:**
 * Without any configuration, the theme automatically chooses:
 * - Material 3 theme on Android platforms
 * - Cupertino theme on iOS and Desktop platforms
 * 
 * @param configuration Custom configuration to override default responsive behavior
 * @param darkTheme Whether to use dark theme (defaults to system preference)
 * @param themeType Which theme to use (Material3 or Cupertino) - overrides platform preference and configuration defaults
 * @param content Your app's content that will benefit from responsive design
 * 
 * @sample
 * ```kotlin
 * // Uses platform-appropriate theme automatically
 * ComposiveTheme {
 *     MyApp() // Android gets Material 3, iOS gets Cupertino
 * }
 * 
 * // Force Material 3 on all platforms
 * ComposiveTheme(
 *     configuration = responsiveConfiguration {
 *         withMaterialTheme()
 *     }
 * ) {
 *     MyApp()
 * }
 * 
 * // Force Cupertino on all platforms
 * ComposiveTheme(themeType = Theme.Cupertino) {
 *     MyApp()
 * }
 * ```
 */
@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun ComposiveTheme(
    configuration: ResponsiveConfiguration = ResponsiveConfiguration(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeType: Theme? = null,
    content: @Composable () -> Unit
) {
    ResponsiveWindowSizeProvider {
        ResponsiveThemeInternal(
            configuration = configuration,
            darkTheme = darkTheme,
            themeType = themeType,
            content = content
        )
    }
}

/**
 * @deprecated Use ComposiveTheme instead for better brand consistency
 */
@OptIn(ExperimentalAdaptiveApi::class)
@Composable
@Deprecated("Use ComposiveTheme instead", ReplaceWith("ComposiveTheme(configuration, darkTheme, themeType, content)"))
fun ResponsiveTheme(
    configuration: ResponsiveConfiguration = ResponsiveConfiguration(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeType: Theme? = null,
    content: @Composable () -> Unit
) {
    ComposiveTheme(configuration, darkTheme, themeType, content)
}

/**
 * Internal composable that handles the theming logic once WindowSizeClass is provided.
 */
@OptIn(ExperimentalAdaptiveApi::class)
@Composable
private fun ResponsiveThemeInternal(
    configuration: ResponsiveConfiguration,
    darkTheme: Boolean,
    themeType: Theme?,
    content: @Composable () -> Unit
) {
    val windowSizeClass = rememberWindowSizeClass()
    val platform = remember { getCurrentPlatform() }
    
    // Determine the effective theme type based on platform preferences
    val effectiveThemeType = when {
        themeType != null -> themeType
        configuration.enablePlatformThemePreference -> platform.preferredTheme
        else -> configuration.defaultThemeType
    }
    
    // Get color schemes - use custom ones if provided, otherwise use defaults
    val cupertinoColors = if (darkTheme) {
        configuration.customCupertinoColors?.dark ?: DarkCupertinoColorScheme
    } else {
        configuration.customCupertinoColors?.light ?: LightCupertinoColorScheme
    }

    val materialColors = if (darkTheme) {
        configuration.customMaterialColors?.dark ?: DarkMaterialColorScheme
    } else {
        configuration.customMaterialColors?.light ?: LightMaterialColorScheme
    }

    val orientation = remember(windowSizeClass.widthSizeClass, windowSizeClass.heightSizeClass) {
        when {
            windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded ||
            (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium && 
             windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact) -> Orientation.Landscape
            else -> Orientation.Portrait
        }
    }

    val responsiveWidthSize = remember(windowSizeClass.widthSizeClass) {
        mapToResponsiveSize(windowSizeClass.widthSizeClass)
    }
    
    val responsiveHeightSize = remember(windowSizeClass.heightSizeClass) {
        mapToResponsiveSize(windowSizeClass.heightSizeClass)
    }

    val sizeThatMatters = when (orientation) {
        Orientation.Portrait -> responsiveWidthSize
        else -> responsiveHeightSize
    }

    // Apply platform-specific scaling to dimensions
    val platformScaledDimensions = remember(platform, sizeThatMatters) {
        val baseDimensions = when (sizeThatMatters) {
        ResponsiveSize.SMALL -> configuration.customDimensions?.small ?: smallDimensions
        ResponsiveSize.COMPACT -> configuration.customDimensions?.compact ?: compactDimensions
        ResponsiveSize.MEDIUM -> configuration.customDimensions?.medium ?: mediumDimensions
        ResponsiveSize.LARGE -> configuration.customDimensions?.large ?: largeDimensions
        }
        
        // Apply platform-specific scaling if needed
        if (platform.densityScale != 1.0f) {
            // Create scaled dimensions based on platform
            scaleDimensionsForPlatform(baseDimensions, platform)
        } else {
            baseDimensions
        }
    }

    // Get Cupertino typography - use custom ones if provided, otherwise use defaults with font resources
    val cupertinoTypography = when (sizeThatMatters) {
        ResponsiveSize.SMALL -> configuration.customCupertinoTypography?.small ?: cupertinoTypographySmall(configuration.customCupertinoFontResources)
        ResponsiveSize.COMPACT -> configuration.customCupertinoTypography?.compact ?: cupertinoTypographyCompact(configuration.customCupertinoFontResources)
        ResponsiveSize.MEDIUM -> configuration.customCupertinoTypography?.medium ?: cupertinoTypographyMedium(configuration.customCupertinoFontResources)
        ResponsiveSize.LARGE -> configuration.customCupertinoTypography?.large ?: cupertinoTypographyBig(configuration.customCupertinoFontResources)
    }

    // Get Material typography - use custom ones if provided, otherwise use defaults with font resources
    val materialTypography = when (sizeThatMatters) {
        ResponsiveSize.SMALL -> configuration.customMaterialTypography?.small ?: materialTypographySmall(configuration.customMaterialFontResources)
        ResponsiveSize.COMPACT -> configuration.customMaterialTypography?.compact ?: materialTypographyCompact(configuration.customMaterialFontResources)
        ResponsiveSize.MEDIUM -> configuration.customMaterialTypography?.medium ?: materialTypographyMedium(configuration.customMaterialFontResources)
        ResponsiveSize.LARGE -> configuration.customMaterialTypography?.large ?: materialTypographyBig(configuration.customMaterialFontResources)
    }

    // Get font weights - use custom ones if provided, otherwise use defaults
    val fontWeights = when (sizeThatMatters) {
        ResponsiveSize.SMALL -> configuration.customFontWeights?.small ?: smallFontWeights
        ResponsiveSize.COMPACT -> configuration.customFontWeights?.compact ?: compactFontWeights
        ResponsiveSize.MEDIUM -> configuration.customFontWeights?.medium ?: mediumFontWeights
        ResponsiveSize.LARGE -> configuration.customFontWeights?.large ?: largeFontWeights
    }

    CompositionLocalProvider(
        LocalResponsiveConfiguration provides configuration,
        LocalCurrentPlatform provides platform
    ) {
        provideResponsiveAppUtils(
            dimensions = platformScaledDimensions,
            orientation = orientation,
            fontWeights = fontWeights,
            cupertinoTypography = cupertinoTypography,
            materialTypography = materialTypography,
            materialColors = materialColors,
            cupertinoColors = cupertinoColors
        ) {
            AdaptiveTheme(
                target = effectiveThemeType,
                material = MaterialThemeSpec.Default(
                        colorScheme = materialColors,
                        typography = materialTypography,
                        shapes = materialShapes,
                ),
                cupertino = CupertinoThemeSpec.Default(
                    colorScheme = cupertinoColors,
                    typography = cupertinoTypography,
                    shapes = cupertinoShapes,
                ),
                content = content
            )
        }
    }
}

/**
 * Scale dimensions based on platform-specific requirements
 */
private fun scaleDimensionsForPlatform(dimensions: Dimensions, platform: Platform): Dimensions {
    val scale = platform.densityScale
    return dimensions.copy(
        space1 = dimensions.space1 * scale,
        space2 = dimensions.space2 * scale,
        space3 = dimensions.space3 * scale,
        space4 = dimensions.space4 * scale,
        space5 = dimensions.space5 * scale,
        space6 = dimensions.space6 * scale,
        space8 = dimensions.space8 * scale,
        space10 = dimensions.space10 * scale,
        space12 = dimensions.space12 * scale,
        space16 = dimensions.space16 * scale,
        contentPaddingSmall = dimensions.contentPaddingSmall * scale,
        contentPaddingMedium = dimensions.contentPaddingMedium * scale,
        contentPaddingLarge = dimensions.contentPaddingLarge * scale,
        screenPaddingHorizontal = dimensions.screenPaddingHorizontal * scale,
        screenPaddingVertical = dimensions.screenPaddingVertical * scale,
        sectionSpacing = dimensions.sectionSpacing * scale,
        itemSpacing = dimensions.itemSpacing * scale,
        iconTiny = dimensions.iconTiny * scale,
        iconSmall = dimensions.iconSmall * scale,
        iconMedium = dimensions.iconMedium * scale,
        iconLarge = dimensions.iconLarge * scale,
        avatarSmall = dimensions.avatarSmall * scale,
        avatarMedium = dimensions.avatarMedium * scale,
        avatarLarge = dimensions.avatarLarge * scale,
        avatarXLarge = dimensions.avatarXLarge * scale,
        imageThumb = dimensions.imageThumb * scale,
        imageSmall = dimensions.imageSmall * scale,
        imageMedium = dimensions.imageMedium * scale,
        imageLarge = dimensions.imageLarge * scale,
        imageHero = dimensions.imageHero * scale,
        buttonHeightSmall = dimensions.buttonHeightSmall * scale,
        buttonHeightMedium = dimensions.buttonHeightMedium * scale,
        buttonHeightLarge = dimensions.buttonHeightLarge * scale,
        buttonMinWidth = dimensions.buttonMinWidth * scale,
        inputHeight = dimensions.inputHeight * scale,
        inputMinWidth = dimensions.inputMinWidth * scale,
        cardPadding = dimensions.cardPadding * scale,
        cardSpacing = dimensions.cardSpacing * scale,
        cardElevation = dimensions.cardElevation * scale,
        bottomSheetPeekHeight = dimensions.bottomSheetPeekHeight * scale,
        dialogMaxWidth = dimensions.dialogMaxWidth * scale,
        dialogPadding = dimensions.dialogPadding * scale
    )
}

/**
 * Internal function that maps WindowSizeClass values to ResponsiveSize values.
 * This mapping determines which dimensions and typography to use for different screen sizes.
 */
internal fun mapToResponsiveSize(sizeClass: Any): ResponsiveSize {
    return when (sizeClass) {
        WindowWidthSizeClass.Compact -> ResponsiveSize.SMALL
        WindowHeightSizeClass.Compact -> ResponsiveSize.SMALL
        WindowWidthSizeClass.Medium -> ResponsiveSize.COMPACT
        WindowHeightSizeClass.Medium -> ResponsiveSize.COMPACT
        WindowWidthSizeClass.Expanded -> ResponsiveSize.MEDIUM
        WindowHeightSizeClass.Expanded -> ResponsiveSize.MEDIUM
        else -> ResponsiveSize.LARGE
    }
}

/**
 * Object providing access to the current responsive theme values.
 * Use this to access dimensions, typography, colors, etc. in your composables.
 */
object AppTheme {
    val dimensions: Dimensions
        @Composable
        get() = LocalDimensions.current

    val orientation: Orientation
        @Composable
        get() = LocalOrientation.current

    val fontWeights: ResponsiveFontWeights
        @Composable
        get() = LocalFontWeights.current

    val cupertinoTypography: CupertinoTypography
        @Composable
        get() = LocalCupertinoTypography.current

    val materialTypography: MaterialTypography
        @Composable
        get() = LocalMaterialTypography.current

    val materialColors: MaterialColorScheme
        @Composable
        get() = LocalMaterialColors.current

    val cupertinoColors: ColorScheme
        @Composable
        get() = LocalCupertinoColors.current
        
    val configuration: ResponsiveConfiguration
        @Composable
        get() = LocalResponsiveConfiguration.current
        
    val platform: Platform
        @Composable
        get() = LocalCurrentPlatform.current
} 