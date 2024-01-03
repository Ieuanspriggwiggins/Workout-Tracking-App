/**
 * @author ieuan sprigg-wiggins
 * Data class for the icon group for the top level navigation bar at the bottom
 * of the application, allows each navigation destination to have a label, filled
 * and outlined icon image vector
 */

package com.ieuan.dev.yourworkouts.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class IconGroup(
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    val label: String
)