package com.gursimar.composive.responsive.foundation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import com.slapps.cupertino.theme.Shapes as CupertinoShapes
import androidx.compose.material3.Shapes as MaterialShapes

/**
 * Default Cupertino shape scheme for the responsive theme system.
 * 
 * These shapes follow Apple's Human Interface Guidelines with subtle rounded corners
 * that provide a friendly, approachable feel while maintaining the clean aesthetic
 * expected in iOS applications.
 * 
 * - Small: 4dp radius for small components like chips and small buttons
 * - Medium: 6dp radius for medium components like cards and input fields  
 * - Large: 8dp radius for large components like dialogs and large cards
 */
val cupertinoShapes = CupertinoShapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp)
)

/**
 * Default Material shape scheme for the responsive theme system.
 * 
 * These shapes follow Material Design guidelines with rounded corners that create
 * visual hierarchy and improve usability across different screen sizes.
 * 
 * - Small: 4dp radius for small components like chips and small buttons
 * - Medium: 6dp radius for medium components like cards and input fields
 * - Large: 8dp radius for large components like dialogs and large cards
 */
val materialShapes = MaterialShapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(6.dp),
    large = RoundedCornerShape(8.dp)
) 