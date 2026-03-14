package com.notte.app.presentation.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.ui.unit.IntOffset

/**
 * Animation constants for consistent timing across the app.
 */
object AnimationConstants {
    const val FAST_ANIMATION_DURATION = 150
    const val NORMAL_ANIMATION_DURATION = 250
    const val SLOW_ANIMATION_DURATION = 300
    
    val FastEasing = FastOutSlowInEasing
    val NormalEasing = LinearOutSlowInEasing
}

/**
 * Pre-configured animation transitions for Notte.
 */
object NotteAnimations {
    // Navigation transitions
    val navigationEnterTransition = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(
            durationMillis = AnimationConstants.NORMAL_ANIMATION_DURATION,
            easing = AnimationConstants.FastEasing
        )
    ) + fadeIn(animationSpec = tween(AnimationConstants.NORMAL_ANIMATION_DURATION))
    
    val navigationExitTransition = slideOutHorizontally(
        targetOffsetX = { -it / 3 },
        animationSpec = tween(
            durationMillis = AnimationConstants.NORMAL_ANIMATION_DURATION,
            easing = AnimationConstants.FastEasing
        )
    ) + fadeOut(animationSpec = tween(AnimationConstants.NORMAL_ANIMATION_DURATION))
    
    val navigationPopEnterTransition = slideInHorizontally(
        initialOffsetX = { -it / 3 },
        animationSpec = tween(
            durationMillis = AnimationConstants.NORMAL_ANIMATION_DURATION,
            easing = AnimationConstants.FastEasing
        )
    ) + fadeIn(animationSpec = tween(AnimationConstants.NORMAL_ANIMATION_DURATION))
    
    val navigationPopExitTransition = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(
            durationMillis = AnimationConstants.NORMAL_ANIMATION_DURATION,
            easing = AnimationConstants.FastEasing
        )
    ) + fadeOut(animationSpec = tween(AnimationConstants.NORMAL_ANIMATION_DURATION))
    
    // List item animations
    val itemEnterAnimation = fadeIn(
        animationSpec = tween(
            durationMillis = AnimationConstants.FAST_ANIMATION_DURATION,
            easing = AnimationConstants.NormalEasing
        )
    ) + expandVertically(
        animationSpec = tween(
            durationMillis = AnimationConstants.FAST_ANIMATION_DURATION,
            easing = AnimationConstants.NormalEasing
        )
    )
    
    val itemExitAnimation = fadeOut(
        animationSpec = tween(
            durationMillis = AnimationConstants.SLOW_ANIMATION_DURATION,
            easing = FastOutLinearInEasing
        )
    ) + shrinkVertically(
        animationSpec = tween(
            durationMillis = AnimationConstants.SLOW_ANIMATION_DURATION,
            easing = FastOutLinearInEasing
        )
    )
}
