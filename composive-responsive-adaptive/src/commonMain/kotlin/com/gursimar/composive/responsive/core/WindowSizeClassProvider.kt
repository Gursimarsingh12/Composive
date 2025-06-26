package com.gursimar.composive.responsive.core

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Internal function that provides WindowSizeClass to the composition tree using CompositionLocal.
 * This enables reactive window size detection across all platforms.
 * 
 * This is a lower-level function that users typically don't need to call directly.
 * Use [ResponsiveWindowSizeProvider] instead, which automatically provides the
 * appropriate WindowSizeClass for each platform.
 * 
 * @param windowSizeClass The current window size class to provide
 * @param content The composable content that will have access to the window size class
 */
@Composable
internal fun ProvideWindowSizeClass(
    windowSizeClass: WindowSizeClass,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalWindowSizeClassProvider provides { windowSizeClass }
    ) {
        content()
    }
}

/**
 * Platform-specific composable that provides the appropriate WindowSizeClass
 * and wraps the content with the provider.
 * 
 * This is the recommended way to initialize window size class detection in your app.
 * It automatically handles platform-specific WindowSizeClass calculation and provides
 * reactive updates when the window size changes.
 * 
 * Each platform implementation handles WindowSizeClass calculation differently:
 * - **Android**: Uses Activity context for accurate size detection
 * - **iOS**: Uses standard calculateWindowSizeClass()
 * - **Desktop**: Uses calculateWindowSizeClass() with automatic window resize detection
 * 
 * @param content The composable content that will have access to the window size class
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun App() {
 *     ResponsiveWindowSizeProvider {
 *         ComposiveTheme {
 *             // Your app content here - can now use rememberDeviceConfiguration()
 *             MainScreen()
 *         }
 *     }
 * }
 * ```
 */
@Composable
expect fun ResponsiveWindowSizeProvider(
    content: @Composable () -> Unit
) 