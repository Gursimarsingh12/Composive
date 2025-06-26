package com.gursimar.composive.responsive.core

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.slapps.cupertino.adaptive.Theme

/**
 * Android-specific platform implementation
 */
internal class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.RELEASE}"
    override val type: PlatformType = PlatformType.ANDROID
    override val supportsDarkModeDetection: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    override val preferredTheme: Theme get() = getRecommendedTheme() // Uses consistent logic: Android → Material3
    override val hasSystemBackButton: Boolean = true
    override val supportsEdgeToEdge: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    override val densityScale: Float = 1.0f // Android handles density automatically
    override val prefersCompactLayouts: Boolean = false // Android can handle various layouts
}

/**
 * Get the current platform instance for Android
 */
actual fun getCurrentPlatform(): Platform = AndroidPlatform()

/**
 * Android-specific utility functions
 */
object AndroidPlatformUtils {
    /**
     * Get the Android API level
     */
    fun getApiLevel(): Int = Build.VERSION.SDK_INT

    /**
     * Get the Android version name
     */
    fun getVersionName(): String = Build.VERSION.RELEASE

    /**
     * Get the device manufacturer
     */
    fun getManufacturer(): String = Build.MANUFACTURER

    /**
     * Get the device model
     */
    fun getModel(): String = Build.MODEL

    /**
     * Check if the device is a tablet based on screen size
     */
    @Composable
    fun isTablet(): Boolean {
        val context = LocalContext.current
        val resources = context.resources
        val configuration = resources.configuration
        return configuration.smallestScreenWidthDp >= 600
    }

    /**
     * Get the current activity if available
     */
    @Composable
    fun getActivity(): Activity? {
        val context = LocalContext.current
        return context as? Activity
    }

    /**
     * Check if a specific permission is granted
     */
    fun hasPermission(context: Context, permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Get the app version name
     */
    fun getAppVersionName(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    /**
     * Get the app version code
     */
    fun getAppVersionCode(context: Context): Long {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode
            } else {
                @Suppress("DEPRECATION")
                packageInfo.versionCode.toLong()
            }
        } catch (e: Exception) {
            -1L
        }
    }
} 