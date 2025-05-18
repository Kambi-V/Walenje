package kambi.victor.walenje.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class TextType {
  DisplayLarge,
  TitleLarge,
  TitleBase,
  TextLarge,
  TextMedium,
  TextBase,
  PinSmall,
  LabelBase,
  LabelSmall,
}

@Composable
fun WalenjeTextStyle(textType: TextType): TextStyle {
  return when (textType) {
    TextType.DisplayLarge -> MaterialTheme.typography.displayLarge
    TextType.TitleLarge -> MaterialTheme.typography.headlineLarge
    TextType.TitleBase -> MaterialTheme.typography.headlineSmall
    TextType.TextLarge -> MaterialTheme.typography.bodyLarge
    TextType.TextMedium -> MaterialTheme.typography.bodyLarge
    TextType.TextBase -> MaterialTheme.typography.bodyMedium
    TextType.LabelBase -> MaterialTheme.typography.labelLarge
    TextType.LabelSmall -> MaterialTheme.typography.labelMedium
    TextType.PinSmall -> MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp)
  }
}

@Composable
fun WalenjeText(
  text: String,
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontWeight: FontWeight? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = Int.MAX_VALUE,
  minLines: Int = 1,
  textType: TextType = TextType.TextBase,
) {
  Text(
    text = text,
    modifier = modifier,
    color = color,
    fontWeight = fontWeight,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    minLines = minLines,
    style = WalenjeTextStyle(textType),
  )
}

@Composable
fun WalenjeText(
  text: AnnotatedString,
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontWeight: FontWeight? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  overflow: TextOverflow = TextOverflow.Clip,
  softWrap: Boolean = true,
  maxLines: Int = Int.MAX_VALUE,
  minLines: Int = 1,
  textType: TextType = TextType.TextBase,
) {
  Text(
    text = text,
    modifier = modifier,
    color = color,
    fontWeight = fontWeight,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    minLines = minLines,
    style = WalenjeTextStyle(textType),
  )
}

@Composable fun WalenjeBasicText() {}
