package com.notte.app.presentation.animation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

/**
 * Modifier extension for clickable elements with haptic feedback.
 * 
 * @param onClick Callback when element is clicked
 * @param hapticFeedback Whether to provide haptic feedback (default: true)
 */
fun Modifier.notteClickable(
    onClick: () -> Unit,
    hapticFeedback: Boolean = true
): Modifier = composed {
    val haptic = LocalHapticFeedback.current
    
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) {
        if (hapticFeedback) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
        onClick()
    }
}
