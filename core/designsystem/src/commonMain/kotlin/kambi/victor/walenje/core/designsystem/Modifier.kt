package kambi.victor.walenje.core.designsystem

// import androidx.compose.foundation.layout.ExperimentalLayoutApi
// import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role

@Composable fun isKeyboardVisible(): Boolean = WindowInsets.ime.getBottom(LocalDensity.current) > 0

/** A modifier to clear focus on inputs when the KeyBoard is dismissed */
@Composable
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
  var isFocused by remember { mutableStateOf(false) }
  var hasKeyboardAppearedSinceLastFocus by remember { mutableStateOf(false) }
  //      val imeIsVisible = WindowInsets.isImeVisible
  val imeIsVisible = isKeyboardVisible()
  val focusManager = LocalFocusManager.current

  LaunchedEffect(imeIsVisible) {
    if (imeIsVisible) {
      hasKeyboardAppearedSinceLastFocus = true
    } else if (hasKeyboardAppearedSinceLastFocus) {
      focusManager.clearFocus()
    }
  }

  onFocusEvent {
    if (isFocused != it.isFocused) {
      isFocused = it.isFocused
      if (isFocused) {
        hasKeyboardAppearedSinceLastFocus = false
      }
    }
  }
}

/** A clickable [Modifier] without indication (no ripple) */
@Composable
fun Modifier.noIndicationClickable(
  enabled: Boolean = true,
  onClickLabel: String? = null,
  role: Role? = null,
  interactionSource: MutableInteractionSource? = null,
  onClick: () -> Unit,
) =
  clickable(
    interactionSource = interactionSource,
    indication = null,
    enabled = enabled,
    onClickLabel = onClickLabel,
    role = role,
    onClick = onClick,
  )

/**
 * Apply [ifTrue] when [condition] is `true`, otherwise apply [ifFalse], (nothing additional happens
 * by default)
 */
@Composable
inline fun Modifier.conditional(
  condition: Boolean,
  ifTrue: Modifier.() -> Modifier,
  ifFalse: Modifier.() -> Modifier = { this },
): Modifier = if (condition) then(ifTrue(Modifier)) else then(ifFalse(this))

/**
 * [condition] determines which block is executed, [ifTrue] block is executed when [condition] is
 * `true`, [ifFalse] block is executed when [condition] is `false`, (nothing additional by default)
 *
 * @return the chained modifier with changes applied, if any
 */
@Composable
inline fun Modifier.thenIf(
  condition: Boolean,
  ifTrue: Modifier.() -> Modifier,
  ifFalse: Modifier.() -> Modifier = { this },
) = Modifier.let { if (condition) it.ifTrue() else it.ifFalse() }

/**
 * Apply [ifNull] (nothing additional by default) if the [argument] is null, otherwise apply
 * [ifNotNull]
 */
@Composable
inline fun <T> Modifier.nullConditional(
  argument: T?,
  ifNull: Modifier.() -> Modifier = { this },
  ifNotNull: Modifier.(T & Any) -> Modifier,
) =
  if (argument != null) {
    then(ifNotNull(Modifier, argument))
  } else {
    then(ifNull(Modifier))
  }

/**
 * Apply the [block] to the modifier chain only if the [argument] is not null, otherwise apply
 * [ifNull] (nothing additional by default)
 *
 * @return the modifier chain with changes applied, if any
 */
@Composable
inline fun <T> Modifier.thenIfNotNull(
  argument: T,
  ifNull: Modifier.() -> Modifier = { this },
  block: Modifier.(T & Any) -> Modifier,
) = then(Modifier.let { if (argument != null) it.block(argument) else it.ifNull() })

@Composable
fun Modifier.bold(isBold: Boolean) =
  this.thenIf(isBold, ifTrue = { graphicsLayer(scaleX = 1.2f, scaleY = 1.2f) }, ifFalse = { this })

@Composable
fun Modifier.rubberbandClickable(onClick: () -> Unit): Modifier {
  var pressed by remember { mutableStateOf(false) }

  val scale by
    animateFloatAsState(
      targetValue = if (pressed) 0.9f else 1f, // Shrink slightly when pressed
      animationSpec = spring(stiffness = 500f),
      label = "", // Control the "rubberband" effect
    )

  return this.graphicsLayer(scaleX = scale, scaleY = scale) // Apply scaling
    .clickable(
      onClick = {
        pressed = true
        onClick()
      },
      onClickLabel = null,
    )
    .thenIf(!pressed, ifTrue = { this }) { this } // Reset `pressed` to false after animation
}
