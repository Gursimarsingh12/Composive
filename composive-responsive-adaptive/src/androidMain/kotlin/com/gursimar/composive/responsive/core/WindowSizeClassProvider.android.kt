package com.gursimar.composive.responsive.core

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

/**
 * Android-specific implementation of ResponsiveWindowSizeProvider.
 * Uses calculateWindowSizeClass() with Activity context for accurate size detection.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
actual fun ResponsiveWindowSizeProvider(
    content: @Composable () -> Unit
) {
    val activity = LocalActivity.current
    val windowSizeClass = calculateWindowSizeClass(activity as Activity)
    ProvideWindowSizeClass(
        windowSizeClass = windowSizeClass,
        content = content
    )
} 