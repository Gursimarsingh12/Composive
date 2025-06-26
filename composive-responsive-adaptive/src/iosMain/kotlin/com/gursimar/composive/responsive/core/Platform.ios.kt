package com.gursimar.composive.responsive.core

import com.slapps.cupertino.adaptive.Theme
import platform.UIKit.UIDevice
import kotlin.math.absoluteValue

/**
 * iOS-specific platform implementation
 */
internal class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: PlatformType = PlatformType.IOS
    override val supportsDarkModeDetection: Boolean = true
    override val preferredTheme: Theme get() = getRecommendedTheme() // Uses consistent logic: iOS → Cupertino
    override val hasSystemBackButton: Boolean = false // iOS uses swipe gestures
    override val supportsEdgeToEdge: Boolean = true // iOS supports edge-to-edge layouts
    override val densityScale: Float = 1.0f // iOS handles density automatically
    override val prefersCompactLayouts: Boolean = true // iOS generally prefers compact layouts
}

/**
 * Get the current platform instance for iOS
 */
actual fun getCurrentPlatform(): Platform = IOSPlatform()

/**
 * iOS-specific utility functions
 */
object IOSPlatformUtils {
    /**
     * Get the iOS version
     */
    fun getIOSVersion(): String {
        return UIDevice.currentDevice.systemVersion
    }

    /**
     * Get the device model
     */
    fun getDeviceModel(): String {
        return UIDevice.currentDevice.model
    }

    /**
     * Get the device name
     */
    fun getDeviceName(): String {
        return UIDevice.currentDevice.name
    }

    /**
     * Check if the device is an iPad
     */
    fun isIPad(): Boolean {
        return UIDevice.currentDevice.userInterfaceIdiom.absoluteValue == 1L // UIUserInterfaceIdiomPad
    }

    /**
     * Check if the device is an iPhone
     */
    fun isIPhone(): Boolean {
        return UIDevice.currentDevice.userInterfaceIdiom.absoluteValue == 0L // UIUserInterfaceIdiomPhone
    }

    /**
     * Get system identifier string
     */
    fun getSystemIdentifier(): String {
        return "${UIDevice.currentDevice.systemName} ${UIDevice.currentDevice.systemVersion}"
    }
}

/**
 * Data class representing iOS safe area insets
 */
data class IOSSafeAreaInsets(
    val top: androidx.compose.ui.unit.Dp,
    val bottom: androidx.compose.ui.unit.Dp,
    val left: androidx.compose.ui.unit.Dp,
    val right: androidx.compose.ui.unit.Dp
) 