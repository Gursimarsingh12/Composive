package com.gursimar.composive.responsive.core

import com.slapps.cupertino.adaptive.Theme

/**
 * Platform-specific utilities and constants for responsive design.
 *
 * This interface provides platform-specific information and utilities that help
 * the responsive system adapt to different operating systems and devices.
 * 
 * Each platform implementation (Android, iOS, Desktop) provides specific values
 * for these properties to ensure optimal user experience on each platform.
 */
interface Platform {
    /**
     * The name of the platform (e.g., "Android 14", "iOS 17.0", "Desktop (Windows 11)")
     */
    val name: String
    
    /**
     * The type of platform for categorization and layout decisions
     */
    val type: PlatformType
    
    /**
     * Whether this platform supports system-level dark mode detection.
     * Used to determine if the app should respect system theme preferences.
     */
    val supportsDarkModeDetection: Boolean
    
    /**
     * The preferred theme type for this platform.
     * iOS typically prefers Cupertino, while Android prefers Material3.
     */
    val preferredTheme: Theme
    
    /**
     * Whether this platform has a system back button/gesture.
     * Android has a back button, iOS uses swipe gestures, Desktop uses window controls.
     */
    val hasSystemBackButton: Boolean
    
    /**
     * Whether this platform supports edge-to-edge display.
     * Modern platforms support extending content to screen edges.
     */
    val supportsEdgeToEdge: Boolean
    
    /**
     * Platform-specific density scale factor for UI elements.
     * Desktop typically uses larger elements (1.2f), mobile uses 1.0f.
     */
    val densityScale: Float
    
    /**
     * Whether this platform prefers compact layouts by default.
     * Mobile platforms typically prefer compact layouts for better usability.
     */
    val prefersCompactLayouts: Boolean
}

/**
 * Enum representing different platform types for responsive design decisions.
 */
enum class PlatformType {
    /** Android mobile and tablet devices */
    ANDROID,
    
    /** iOS mobile and tablet devices */
    IOS,
    
    /** Desktop applications (Windows, macOS, Linux) */
    DESKTOP,
    
    /** Web applications running in browsers */
    WEB
}

/**
 * Get the current platform instance.
 * 
 * This function returns the appropriate platform implementation based on
 * the current runtime environment.
 * 
 * @return The current Platform instance
 */
expect fun getCurrentPlatform(): Platform

/**
 * Check if the current platform is mobile (Android or iOS).
 * 
 * @return true if the platform is Android or iOS
 */
fun Platform.isMobile(): Boolean = type == PlatformType.ANDROID || type == PlatformType.IOS

/**
 * Check if the current platform is desktop.
 * 
 * @return true if the platform is desktop
 */
fun Platform.isDesktop(): Boolean = type == PlatformType.DESKTOP

/**
 * Check if the current platform is iOS.
 * 
 * @return true if the platform is iOS
 */
fun Platform.isIOS(): Boolean = type == PlatformType.IOS

/**
 * Check if the current platform is Android.
 * 
 * @return true if the platform is Android
 */
fun Platform.isAndroid(): Boolean = type == PlatformType.ANDROID

/**
 * Check if the current platform is web.
 * 
 * @return true if the platform is web
 */
fun Platform.isWeb(): Boolean = type == PlatformType.WEB

/**
 * Get platform-appropriate spacing multiplier.
 * 
 * Different platforms have different spacing preferences:
 * - iOS/Android: 1.0f (standard spacing)
 * - Desktop: 1.2f (larger spacing for mouse interaction)
 * - Web: 1.1f (slightly larger for web usability)
 * 
 * @return The spacing multiplier for this platform
 */
fun Platform.getSpacingMultiplier(): Float = when (type) {
    PlatformType.IOS -> 1.0f
    PlatformType.ANDROID -> 1.0f
    PlatformType.DESKTOP -> 1.2f
    PlatformType.WEB -> 1.1f
}

/**
 * Get platform-appropriate minimum touch target size in dp.
 * 
 * Each platform has different minimum touch target requirements:
 * - iOS: 44dp (Apple's HIG recommendation)
 * - Android: 48dp (Material Design recommendation)
 * - Desktop: 32dp (smaller for mouse precision)
 * - Web: 40dp (compromise for various input methods)
 * 
 * @return The minimum touch target size in dp
 */
fun Platform.getMinTouchTargetSize(): Float = when (type) {
    PlatformType.IOS -> 44f
    PlatformType.ANDROID -> 48f
    PlatformType.DESKTOP -> 32f
    PlatformType.WEB -> 40f
}

/**
 * Get the recommended theme based on platform name.
 * 
 * Logic: If platform name contains "Android" (case insensitive) → Material3, otherwise → Cupertino
 * This provides a consistent theme selection strategy across all platforms.
 * 
 * @return The recommended Theme for this platform
 */
fun Platform.getRecommendedTheme(): Theme = if (name.contains("Android", ignoreCase = true)) {
    Theme.Material3
} else {
    Theme.Cupertino
}

/**
 * Check if this platform should use Material3 theme by default.
 * 
 * @return true if Material3 is recommended for this platform
 */
fun Platform.shouldUseMaterial3(): Boolean = name.contains("Android", ignoreCase = true)

/**
 * Check if this platform should use Cupertino theme by default.
 * 
 * @return true if Cupertino is recommended for this platform
 */
fun Platform.shouldUseCupertino(): Boolean = !name.contains("Android", ignoreCase = true) 