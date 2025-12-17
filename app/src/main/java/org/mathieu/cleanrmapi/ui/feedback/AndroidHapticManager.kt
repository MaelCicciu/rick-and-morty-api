package org.mathieu.cleanrmapi.ui.feedback

import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import org.mathieu.cleanrmapi.domain.feedback.HapticManager

/**
 * Manager for vibrations
 * Applies vibrations to clicks
 *
 * @param haptic Extends the HapticFeedback interface
 */
class AndroidHapticManager(
    private val haptic: HapticFeedback
) : HapticManager {

    override fun click() {
        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
    }
}
