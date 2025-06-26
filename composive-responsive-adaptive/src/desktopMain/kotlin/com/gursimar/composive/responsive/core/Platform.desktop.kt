package com.gursimar.composive.responsive.core

import com.slapps.cupertino.adaptive.Theme

/**
 * Desktop-specific platform implementation
 */
internal class DesktopPlatform : Platform {
    override val name: String = buildString {
        append("Desktop (")
        append(System.getProperty("os.name"))
        append(" ")
        append(System.getProperty("os.version"))
        append(")")
    }

    override val type: PlatformType = PlatformType.DESKTOP
    override val supportsDarkModeDetection: Boolean = true
    override val preferredTheme: Theme get() = getRecommendedTheme() // Uses consistent logic: Desktop → Cupertino
    override val hasSystemBackButton: Boolean = false // Desktop uses window controls
    override val supportsEdgeToEdge: Boolean = false // Desktop has window decorations
    override val densityScale: Float = 1.2f // Desktop typically needs larger UI elements
    override val prefersCompactLayouts: Boolean = false // Desktop has more space
}

/**
 * Get the current platform instance for Desktop
 */
actual fun getCurrentPlatform(): Platform = DesktopPlatform()

/**
 * Desktop-specific utility functions
 */
object DesktopPlatformUtils {
    /**
     * Get the operating system name
     */
    fun getOSName(): String {
        return System.getProperty("os.name") ?: "Unknown"
    }

    /**
     * Get the operating system version
     */
    fun getOSVersion(): String {
        return System.getProperty("os.version") ?: "Unknown"
    }

    /**
     * Check if running on Windows
     */
    fun isWindows(): Boolean {
        return getOSName().lowercase().contains("windows")
    }

    /**
     * Check if running on macOS
     */
    fun isMacOS(): Boolean {
        val osName = getOSName().lowercase()
        return osName.contains("mac") || osName.contains("darwin")
    }

    /**
     * Check if running on Linux
     */
    fun isLinux(): Boolean {
        return getOSName().lowercase().contains("linux")
    }

    /**
     * Get the Java version being used
     */
    fun getJavaVersion(): String {
        return System.getProperty("java.version") ?: "Unknown"
    }

    /**
     * Check if the system is in dark mode (basic implementation)
     */
    fun isSystemInDarkMode(): Boolean {
        // This is a basic implementation
        // A more sophisticated approach would check system preferences
        return when {
            isWindows() -> {
                // Windows registry check would be needed for accurate detection
                false
            }

            isMacOS() -> {
                // macOS system preference check would be needed
                false
            }

            isLinux() -> {
                // Linux desktop environment check would be needed
                false
            }

            else -> false
        }
    }

    /**
     * Get the system's DPI scaling factor
     */
    fun getSystemScaleFactor(): Float {
        return try {
            val toolkit = java.awt.Toolkit.getDefaultToolkit()
            val screenResolution = toolkit.screenResolution
            screenResolution / 96.0f // 96 DPI is the standard
        } catch (e: Exception) {
            1.0f // Fallback
        }
    }

    /**
     * Get available screen space (accounting for taskbar, dock, etc.)
     */
    fun getAvailableScreenSize(): Pair<Int, Int> {
        return try {
            val toolkit = java.awt.Toolkit.getDefaultToolkit()
            val screenSize = toolkit.screenSize
            Pair(screenSize.width, screenSize.height)
        } catch (e: Exception) {
            Pair(1920, 1080) // Fallback
        }
    }

    /**
     * Check if the system supports multiple monitors
     */
    fun supportsMultipleMonitors(): Boolean {
        return try {
            val ge = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
            ge.screenDevices.size > 1
        } catch (e: Exception) {
            false
        }
    }
} 