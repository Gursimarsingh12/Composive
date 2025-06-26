package com.gursimar.composive.responsive.core

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.CompositionLocalProvider

/**
 * CompositionLocal for providing WindowSizeClass throughout the composition tree.
 * This allows for better reactivity to window size changes across all platforms.
 * 
 * This is an internal implementation detail. Users should use ResponsiveWindowSizeProvider instead.
 */
internal val LocalWindowSizeClassProvider = compositionLocalOf<@Composable () -> WindowSizeClass> {
    error("WindowSizeClass provider not initialized. Make sure to call ProvideWindowSizeClass in your App.")
}

/**
 * Represents different device configurations based on screen size and orientation.
 * 
 * This enum helps determine the appropriate layout and spacing for different device types
 * and screen sizes. It's the foundation of the responsive system and used throughout
 * to make intelligent layout decisions.
 * 
 * The configurations are determined by analyzing both width and height size classes
 * from Material3's WindowSizeClass system.
 */
enum class DeviceConfiguration {
    /**
     * Mobile device in portrait orientation (narrow width, tall height).
     * 
     * Characteristics:
     * - Compact width, medium/expanded height
     * - Single-column layouts preferred
     * - Bottom navigation recommended
     * - Compact spacing and typography
     * 
     * **Best for**: Phones in portrait mode
     */
    MOBILE_PORTRAIT,
    
    /**
     * Mobile device in landscape orientation (medium width, short height).
     * 
     * Characteristics:
     * - Medium width, compact height
     * - Horizontal navigation preferred
     * - Two-column layouts possible
     * - Efficient use of horizontal space
     * 
     * **Best for**: Phones in landscape mode
     */
    MOBILE_LANDSCAPE,
    
    /**
     * Tablet device in portrait orientation (medium width, tall height).
     * 
     * Characteristics:
     * - Medium width, expanded height
     * - Side navigation rail suitable
     * - Two-column layouts with sidebar
     * - Generous vertical space
     * 
     * **Best for**: Tablets in portrait mode, large phones
     */
    TABLET_PORTRAIT,
    
    /**
     * Tablet device in landscape orientation (wide width, medium height).
     * 
     * Characteristics:
     * - Expanded width, medium height
     * - Navigation drawer recommended
     * - Multi-column layouts (2-3 columns)
     * - Efficient horizontal space usage
     * 
     * **Best for**: Tablets in landscape mode
     */
    TABLET_LANDSCAPE,
    
    /**
     * Desktop or large screen device (wide width, tall height).
     * 
     * Characteristics:
     * - Expanded width and height
     * - Full navigation drawer
     * - Multi-column layouts (3+ columns)
     * - Maximum content density
     * - Mouse/keyboard interaction optimized
     * 
     * **Best for**: Desktop screens, large displays
     */
    DESKTOP;

    /**
     * Checks if this configuration represents a mobile device (portrait or landscape).
     * 
     * @return true if this is MOBILE_PORTRAIT or MOBILE_LANDSCAPE
     */
    fun isMobile(): Boolean = this == MOBILE_PORTRAIT || this == MOBILE_LANDSCAPE
    
    /**
     * Checks if this configuration represents a tablet device (portrait or landscape).
     * 
     * @return true if this is TABLET_PORTRAIT or TABLET_LANDSCAPE
     */
    fun isTablet(): Boolean = this == TABLET_PORTRAIT || this == TABLET_LANDSCAPE
    
    /**
     * Checks if this configuration represents a desktop device.
     * 
     * @return true if this is DESKTOP
     */
    fun isDesktop(): Boolean = this == DESKTOP
    
    /**
     * Checks if this configuration is in portrait orientation.
     * 
     * @return true if this is MOBILE_PORTRAIT, TABLET_PORTRAIT, or DESKTOP
     */
    fun isPortrait(): Boolean = this == MOBILE_PORTRAIT || this == TABLET_PORTRAIT || this == DESKTOP
    
    /**
     * Checks if this configuration is in landscape orientation.
     * 
     * @return true if this is MOBILE_LANDSCAPE or TABLET_LANDSCAPE
     */
    fun isLandscape(): Boolean = this == MOBILE_LANDSCAPE || this == TABLET_LANDSCAPE
    
    /**
     * Gets the recommended number of columns for this device configuration.
     * 
     * @return Recommended number of columns for layouts
     */
    fun getRecommendedColumns(): Int = when (this) {
        MOBILE_PORTRAIT -> 1
        MOBILE_LANDSCAPE -> 2
        TABLET_PORTRAIT -> 2
        TABLET_LANDSCAPE -> 3
        DESKTOP -> 4
    }

    companion object {
        /**
         * Determines the device configuration from a given WindowSizeClass.
         * 
         * This function analyzes the width and height size classes to determine
         * the most appropriate device configuration for layout purposes.
         * 
         * The logic prioritizes width for portrait orientations and considers
         * both dimensions for landscape scenarios.
         * 
         * @param windowSizeClass The current window size class from Material3
         * @return The corresponding DeviceConfiguration
         * 
         * @sample
         * ```kotlin
         * val windowSizeClass = calculateWindowSizeClass()
         * val deviceConfig = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
         * 
         * when (deviceConfig) {
         *     DeviceConfiguration.MOBILE_PORTRAIT -> {
         *         // Show single column layout
         *     }
         *     DeviceConfiguration.DESKTOP -> {
         *         // Show multi-column layout with sidebar
         *     }
         *     // ... handle other configurations
         * }
         * ```
         */
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {
            val widthClass = windowSizeClass.widthSizeClass
            val heightClass = windowSizeClass.heightSizeClass

            return when {
                // Desktop: Both width and height are expanded (large screens)
                widthClass == WindowWidthSizeClass.Expanded &&
                        heightClass == WindowHeightSizeClass.Expanded -> DESKTOP

                // Mobile portrait: Compact width with any height
                widthClass == WindowWidthSizeClass.Compact -> {
                    when (heightClass) {
                        WindowHeightSizeClass.Compact -> MOBILE_LANDSCAPE // Very small screen
                        else -> MOBILE_PORTRAIT // Normal mobile portrait
                    }
                }

                // Mobile landscape: Medium width with compact height
                widthClass == WindowWidthSizeClass.Medium &&
                        heightClass == WindowHeightSizeClass.Compact -> MOBILE_LANDSCAPE

                // Tablet portrait: Medium width with expanded height
                widthClass == WindowWidthSizeClass.Medium &&
                        heightClass == WindowHeightSizeClass.Expanded -> TABLET_PORTRAIT

                // Tablet landscape: Expanded width with medium height
                widthClass == WindowWidthSizeClass.Expanded &&
                        heightClass == WindowHeightSizeClass.Medium -> TABLET_LANDSCAPE

                // Additional tablet cases
                widthClass == WindowWidthSizeClass.Medium &&
                        heightClass == WindowHeightSizeClass.Medium -> TABLET_PORTRAIT

                // Expanded width with compact height - could be tablet landscape or desktop
                widthClass == WindowWidthSizeClass.Expanded &&
                        heightClass == WindowHeightSizeClass.Compact -> TABLET_LANDSCAPE

                // Fallback: If we have expanded width, it's likely desktop or tablet
                widthClass == WindowWidthSizeClass.Expanded -> {
                    // If width is expanded but height is not, it's likely tablet landscape
                    // If both are expanded, it should have been caught above as desktop
                    when (heightClass) {
                        WindowHeightSizeClass.Expanded -> DESKTOP
                        else -> TABLET_LANDSCAPE
                    }
                }

                // Medium width cases
                widthClass == WindowWidthSizeClass.Medium -> {
                    when (heightClass) {
                        WindowHeightSizeClass.Compact -> MOBILE_LANDSCAPE
                        WindowHeightSizeClass.Medium -> TABLET_PORTRAIT
                        WindowHeightSizeClass.Expanded -> TABLET_PORTRAIT
                        else -> TABLET_PORTRAIT
                    }
                }

                // Default fallback - if nothing else matches, assume desktop for large screens
                else -> DESKTOP
            }
        }
        
        /**
         * Determines the device configuration from platform type and WindowSizeClass.
         * 
         * This enhanced version considers the platform type along with window size
         * to make more accurate device configuration decisions. It's particularly
         * useful for desktop applications that need to adapt to different window sizes.
         * 
         * @param platform The current platform information
         * @param windowSizeClass The current window size class
         * @return The corresponding DeviceConfiguration
         * 
         * @sample
         * ```kotlin
         * val platform = getCurrentPlatform()
         * val windowSizeClass = calculateWindowSizeClass()
         * val deviceConfig = DeviceConfiguration.fromPlatformAndWindowSize(platform, windowSizeClass)
         * ```
         */
        fun fromPlatformAndWindowSize(platform: Platform, windowSizeClass: WindowSizeClass): DeviceConfiguration {
            val widthClass = windowSizeClass.widthSizeClass
            val heightClass = windowSizeClass.heightSizeClass
            
            return when (platform.type) {
                PlatformType.DESKTOP -> {
                    // Desktop is responsive to window size but has desktop-friendly defaults
                    when (widthClass) {
                        WindowWidthSizeClass.Compact -> {
                            if (heightClass == WindowHeightSizeClass.Compact) {
                                MOBILE_LANDSCAPE
                            } else {
                                MOBILE_PORTRAIT
                            }
                        }
                        WindowWidthSizeClass.Medium -> {
                            if (heightClass == WindowHeightSizeClass.Compact) {
                                MOBILE_LANDSCAPE
                            } else {
                                TABLET_PORTRAIT
                            }
                        }
                        else -> DESKTOP // Expanded width on desktop
                    }
                }
                else -> {
                    // For mobile platforms, use standard window size class logic
                    fromWindowSizeClass(windowSizeClass)
                }
            }
        }
    }
}



/**
 * Remember the current WindowSizeClass from the composition local.
 * 
 * This function provides access to the current window size class that has been
 * provided by ResponsiveWindowSizeProvider higher up in the composition tree.
 * 
 * For most use cases, you should use rememberDeviceConfiguration() instead,
 * which provides a higher-level API for responsive design decisions.
 * 
 * @return The current WindowSizeClass
 * @throws IllegalStateException if WindowSizeClass provider is not initialized
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun MyScreen() {
 *     val windowSizeClass = rememberWindowSizeClass()
 *     val isCompact = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
 *     
 *     if (isCompact) {
 *         CompactLayout()
 *     } else {
 *         ExpandedLayout()
 *     }
 * }
 * ```
 */
@Composable
fun rememberWindowSizeClass(): WindowSizeClass {
    val provider = LocalWindowSizeClassProvider.current
    return provider()
}

/**
 * Remember the current device configuration based on window size class.
 * 
 * This is the primary function you'll use to get responsive information in your composables.
 * It automatically calculates the appropriate device configuration based on the current
 * window size and provides intelligent defaults for layout decisions.
 * 
 * @return The current DeviceConfiguration
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun AdaptiveLayout() {
 *     val deviceConfig = rememberDeviceConfiguration()
 *     
 *     when (deviceConfig) {
 *         DeviceConfiguration.MOBILE_PORTRAIT -> {
 *             SingleColumnLayout()
 *         }
 *         DeviceConfiguration.TABLET_LANDSCAPE,
 *         DeviceConfiguration.DESKTOP -> {
 *             MultiColumnLayout(columns = deviceConfig.getRecommendedColumns())
 *         }
 *         else -> {
 *             TwoColumnLayout()
 *         }
 *     }
 * }
 * ```
 */
@Composable
fun rememberDeviceConfiguration(): DeviceConfiguration {
    val windowSizeClass = rememberWindowSizeClass()
    return remember(windowSizeClass) {
        DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    }
}

/**
 * Remember the current device configuration with platform information.
 * 
 * This enhanced version considers both window size and platform type to make
 * more informed decisions about device configuration. Particularly useful
 * for desktop applications that need platform-specific behavior.
 * 
 * @return The current DeviceConfiguration considering platform
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun PlatformAdaptiveLayout() {
 *     val deviceConfig = rememberDeviceConfigurationWithPlatform()
 *     
 *     // Desktop apps can be more aggressive with multi-column layouts
 *     val columns = if (deviceConfig.isDesktop()) {
 *         deviceConfig.getRecommendedColumns()
 *     } else {
 *         minOf(deviceConfig.getRecommendedColumns(), 2)
 *     }
 *     
 *     ResponsiveGrid(columns = columns) {
 *         // Your content
 *     }
 * }
 * ```
 */
@Composable
fun rememberDeviceConfigurationWithPlatform(): DeviceConfiguration {
    val windowSizeClass = rememberWindowSizeClass()
    val platform = remember { getCurrentPlatform() }
    return remember(windowSizeClass, platform) {
        DeviceConfiguration.fromPlatformAndWindowSize(platform, windowSizeClass)
    }
}

/**
 * Remember the current platform information.
 * 
 * This is an internal helper function. Users should use currentPlatform() instead
 * for accessing platform information in their composables.
 * 
 * @return The current Platform instance
 */
@Composable
internal fun rememberCurrentPlatform(): Platform {
    return remember { getCurrentPlatform() }
}