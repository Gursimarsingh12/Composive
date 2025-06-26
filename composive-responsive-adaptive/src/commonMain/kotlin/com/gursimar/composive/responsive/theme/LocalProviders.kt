package com.gursimar.composive.responsive.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.gursimar.composive.responsive.core.Orientation
import com.gursimar.composive.responsive.core.Platform
import com.gursimar.composive.responsive.core.getCurrentPlatform
import com.gursimar.composive.responsive.foundation.*
import com.slapps.cupertino.theme.Typography as CupertinoTypography
import androidx.compose.material3.Typography as MaterialTypography
import androidx.compose.material3.ColorScheme as MaterialColorScheme
import com.slapps.cupertino.theme.ColorScheme as CupertinoColorScheme

/**
 * Internal function that provides responsive app utilities to the composition tree.
 * 
 * This function sets up all the necessary CompositionLocal providers for responsive
 * design values including dimensions, typography, colors, and orientation.
 * Users typically don't need to call this directly as it's handled by ComposiveTheme.
 * 
 * @param dimensions The dimensions to use for the current screen size
 * @param orientation The current screen orientation
 * @param fontWeights The font weights to use for the current screen size
 * @param cupertinoTypography The Cupertino typography to use
 * @param materialTypography The Material typography to use
 * @param materialColors The Material color scheme to use
 * @param cupertinoColors The Cupertino color scheme to use
 * @param content The composable content that will have access to these values
 */
@Composable
fun provideResponsiveAppUtils(
    dimensions: Dimensions,
    orientation: Orientation,
    fontWeights: ResponsiveFontWeights,
    cupertinoTypography: CupertinoTypography,
    materialTypography: MaterialTypography,
    materialColors: MaterialColorScheme,
    cupertinoColors: CupertinoColorScheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDimensions provides dimensions,
        LocalOrientation provides orientation,
        LocalFontWeights provides fontWeights,
        LocalCupertinoTypography provides cupertinoTypography,
        LocalMaterialTypography provides materialTypography,
        LocalMaterialColors provides materialColors,
        LocalCupertinoColors provides cupertinoColors
    ) {
        content()
    }
}

// CompositionLocal providers (for internal use)

/**
 * CompositionLocal for accessing responsive font weights throughout the app.
 * 
 * @throws IllegalStateException if font weights are not provided
 */
internal val LocalFontWeights = compositionLocalOf<ResponsiveFontWeights> {
    error("FontWeights not provided")
}

/**
 * CompositionLocal for accessing responsive dimensions throughout the app.
 * 
 * Defaults to small dimensions if not explicitly provided.
 */
internal val LocalDimensions = compositionLocalOf {
    smallDimensions
}

/**
 * CompositionLocal for accessing the current screen orientation throughout the app.
 * 
 * Defaults to Portrait orientation if not explicitly provided.
 */
internal val LocalOrientation = compositionLocalOf<Orientation> {
    Orientation.Portrait
}

/**
 * CompositionLocal for accessing Cupertino typography throughout the app.
 * 
 * @throws IllegalStateException if Cupertino typography is not provided
 */
internal val LocalCupertinoTypography = compositionLocalOf<CupertinoTypography> {
    error("CupertinoTypography not provided")
}

/**
 * CompositionLocal for accessing Material typography throughout the app.
 * 
 * @throws IllegalStateException if Material typography is not provided
 */
internal val LocalMaterialTypography = compositionLocalOf<MaterialTypography> {
    error("MaterialTypography not provided")
}

/**
 * CompositionLocal for accessing Material color scheme throughout the app.
 * 
 * @throws IllegalStateException if Material colors are not provided
 */
internal val LocalMaterialColors = compositionLocalOf<MaterialColorScheme> {
    error("MaterialColors not provided")
}

/**
 * CompositionLocal for accessing Cupertino color scheme throughout the app.
 * 
 * @throws IllegalStateException if Cupertino colors are not provided
 */
internal val LocalCupertinoColors = compositionLocalOf<CupertinoColorScheme> {
    error("CupertinoColors not provided")
}

/**
 * CompositionLocal for accessing the current platform throughout the app.
 * 
 * Defaults to the current platform if not explicitly provided.
 */
internal val LocalCurrentPlatform = compositionLocalOf<Platform> {
    getCurrentPlatform()
} 

// Public API functions for users to access responsive values

/**
 * Access the current responsive dimensions within a Composable.
 * 
 * This function provides access to the current screen size-appropriate dimensions
 * that automatically adapt based on device configuration (mobile, tablet, desktop).
 * 
 * @return Current responsive dimensions for spacing, sizing, and layout
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun MyComponent() {
 *     val dimensions = currentResponsiveDimensions()
 *     
 *     Box(
 *         modifier = Modifier.padding(dimensions.screenPaddingHorizontal)
 *     ) {
 *         // Content with responsive padding
 *     }
 * }
 * ```
 */
@Composable
fun currentResponsiveDimensions(): Dimensions = LocalDimensions.current

/**
 * Access the current screen orientation within a Composable.
 * 
 * @return Current screen orientation (Portrait, Landscape, or Square)
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun AdaptiveLayout() {
 *     val orientation = currentScreenOrientation()
 *     
 *     when (orientation) {
 *         Orientation.Portrait -> VerticalLayout()
 *         Orientation.Landscape -> HorizontalLayout()
 *         Orientation.Square -> SquareLayout()
 *     }
 * }
 * ```
 */
@Composable
fun currentScreenOrientation(): Orientation = LocalOrientation.current

/**
 * Access the current responsive font weights within a Composable.
 * 
 * @return Current responsive font weights appropriate for the screen size
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun ResponsiveText() {
 *     val fontWeights = currentResponsiveFontWeights()
 *     
 *     Text(
 *         text = "Responsive Text",
 *         fontWeight = fontWeights.headlineBold
 *     )
 * }
 * ```
 */
@Composable
fun currentResponsiveFontWeights(): ResponsiveFontWeights = LocalFontWeights.current

/**
 * Access the current platform information within a Composable.
 * 
 * @return Current platform information (type, capabilities, preferences)
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun PlatformSpecificContent() {
 *     val platform = currentPlatform()
 *     
 *     if (platform.isDesktop()) {
 *         DesktopToolbar()
 *     } else {
 *         MobileToolbar()
 *     }
 * }
 * ```
 */
@Composable
fun currentPlatform(): Platform = LocalCurrentPlatform.current

/**
 * Access the current Cupertino typography within a Composable.
 * 
 * This is automatically selected based on screen size and provides
 * iOS-appropriate typography scales.
 * 
 * @return Current Cupertino typography configuration
 */
@Composable
fun currentCupertinoTypography(): CupertinoTypography = LocalCupertinoTypography.current

/**
 * Access the current Material typography within a Composable.
 * 
 * This is automatically selected based on screen size and provides
 * Material Design-appropriate typography scales.
 * 
 * @return Current Material typography configuration
 */
@Composable
fun currentMaterialTypography(): MaterialTypography = LocalMaterialTypography.current

/**
 * Access the current Material color scheme within a Composable.
 * 
 * This automatically adapts to light/dark theme preferences.
 * 
 * @return Current Material color scheme
 */
@Composable
fun currentMaterialColors(): MaterialColorScheme = LocalMaterialColors.current

/**
 * Access the current Cupertino color scheme within a Composable.
 * 
 * This automatically adapts to light/dark theme preferences.
 * 
 * @return Current Cupertino color scheme
 */
@Composable
fun currentCupertinoColors(): CupertinoColorScheme = LocalCupertinoColors.current