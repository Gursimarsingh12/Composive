package com.gursimar.composive.responsive.core

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

/**
 * Desktop-specific implementation of ResponsiveWindowSizeProvider.
 * Uses calculateWindowSizeClass() which is reactive to window size changes.
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