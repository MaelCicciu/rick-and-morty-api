package org.mathieu.cleanrmapi.ui.feedback

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalHapticFeedback
import org.mathieu.cleanrmapi.domain.feedback.HapticManager

/**
 * Bridges Compose and the manager
 * Remembers managers
 */
@Composable
fun rememberClickHaptic(): HapticManager {
    val haptic = LocalHapticFeedback.current
    return remember { AndroidHapticManager(haptic) }
}

