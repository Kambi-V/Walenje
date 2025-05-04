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

/** A haptic effect to signal the confirmation or successful completion of a user interaction.. */
fun HapticFeedback.confirm() = performHapticFeedback(HapticFeedbackType.Confirm)

/** The user has performed a context click on an object. */
fun HapticFeedback.contextClick() = performHapticFeedback(HapticFeedbackType.ContextClick)

/** The user has finished a gesture (e.g. on the soft keyboard). */
fun HapticFeedback.gestureEnd() = performHapticFeedback(HapticFeedbackType.GestureEnd)

/**
 * The user is executing a swipe/drag-style gesture, such as pull-to-refresh, where the gesture
 * action is eligible at a certain threshold of movement, and can be cancelled by moving back past
 * the threshold.
 */
fun HapticFeedback.gestureThresholdActivate() =
  performHapticFeedback(HapticFeedbackType.GestureThresholdActivate)

/**
 * The user has performed a long press on an object that is resulting in an action being performed.
 */
fun HapticFeedback.longPress() = performHapticFeedback(HapticFeedbackType.LongPress)

/** A haptic effect to signal the rejection or failure of a user interaction. */
fun HapticFeedback.reject() = performHapticFeedback(HapticFeedbackType.Reject)

/**
 * The user is switching between a series of many potential choices, for example minutes on a clock
 * face, or individual percentages.
 */
fun HapticFeedback.segmentFrequentTick() =
  performHapticFeedback(HapticFeedbackType.SegmentFrequentTick)

/**
 * The user is switching between a series of potential choices, for example items in a list or
 * discrete points on a slider.
 */
fun HapticFeedback.segmentTick() = performHapticFeedback(HapticFeedbackType.SegmentTick)

/** The user has performed a selection/insertion handle move on text field. */
fun HapticFeedback.textHandleMove() = performHapticFeedback(HapticFeedbackType.TextHandleMove)

/** The user has toggled a switch or button into the off position. */
fun HapticFeedback.toggleOff() = performHapticFeedback(HapticFeedbackType.ToggleOff)

/** The user has toggled a switch or button into the on position. */
fun HapticFeedback.toggleOn() = performHapticFeedback(HapticFeedbackType.ToggleOn)

/** The user has pressed on a virtual on-screen key. */
fun HapticFeedback.virtualKey() = performHapticFeedback(HapticFeedbackType.VirtualKey)
