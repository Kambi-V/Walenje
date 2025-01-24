package kambi.victor.walenje.core.designsystem

import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

/**
 * Plays a medium haptic feedback, similar to force-touch or long-tap effect.
 *
 * The effect can vary between platforms or be unavailable (e.g. desktop, browser).
 *
 * @see HapticFeedbackType.LongPress
 */
fun HapticFeedback.medium() = performHapticFeedback(HapticFeedbackType.LongPress)

/**
 * Plays a short haptic feedback, similar to a software keyboard typing vibration.
 *
 * The effect can vary between platforms or be unavailable (e.g. desktop, browser).
 *
 * @see HapticFeedbackType.TextHandleMove
 */
fun HapticFeedback.small() = performHapticFeedback(HapticFeedbackType.TextHandleMove)
