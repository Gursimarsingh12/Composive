package com.gursimar.composive.responsive.core

/**
 * Sealed class representing different screen orientations for responsive layout decisions.
 * 
 * This class helps determine the appropriate layout strategy based on the device's
 * orientation, enabling developers to create adaptive UIs that respond to orientation changes.
 * 
 * Use this in combination with window size classes to make informed layout decisions.
 */
sealed class Orientation{
    /**
     * Portrait orientation - height is greater than width.
     * 
     * Common on mobile phones in their default orientation and tablets held vertically.
     * Typically used for single-column layouts with vertical scrolling.
     */
    data object Portrait : Orientation()
    
    /**
     * Landscape orientation - width is greater than height.
     * 
     * Common on mobile phones when rotated horizontally, tablets held horizontally,
     * and desktop screens. Often used for multi-column layouts or side-by-side content.
     */
    data object Landscape : Orientation()
    
    /**
     * Square orientation - width equals height (or very close).
     * 
     * Rare but possible on some specialized devices or window configurations.
     * Can be treated similarly to Portrait for most layout purposes.
     */
    data object Square : Orientation()
} 