package com.gursimar.composive.responsive.core

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

/**
 * iOS-specific implementation of ResponsiveWindowSizeProvider.
 * Uses calculateWindowSizeClass() for iOS devices.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
actual fun ResponsiveWindowSizeProvider(
    content: @Composable () -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    
    ProvideWindowSizeClass(
        windowSizeClass = windowSizeClass,
        content = content
    )
}