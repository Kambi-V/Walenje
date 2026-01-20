package kambi.victor.walenje.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import walenje.core.designsystem.generated.resources.Montserrat
import walenje.core.designsystem.generated.resources.Res

@Composable
fun montserratTypography(): Typography {
  val montserratFont = montserratFamily()

  return with(MaterialTheme.typography) {
    copy(
      displayLarge = displayLarge.copy(fontFamily = montserratFont),
      displayMedium = displayMedium.copy(fontFamily = montserratFont),
      displaySmall = displaySmall.copy(fontFamily = montserratFont),
      headlineLarge = headlineLarge.copy(fontFamily = montserratFont),
      headlineMedium = headlineMedium.copy(fontFamily = montserratFont),
      headlineSmall = headlineSmall.copy(fontFamily = montserratFont),
      titleLarge = titleLarge.copy(fontFamily = montserratFont),
      titleMedium = titleMedium.copy(fontFamily = montserratFont),
      titleSmall = titleSmall.copy(fontFamily = montserratFont),
      bodyLarge = bodyLarge.copy(fontFamily = montserratFont),
      bodyMedium = bodyMedium.copy(fontFamily = montserratFont),
      bodySmall = bodySmall.copy(fontFamily = montserratFont),
      labelLarge = labelLarge.copy(fontFamily = montserratFont),
      labelMedium = labelMedium.copy(fontFamily = montserratFont),
      labelSmall = labelSmall.copy(fontFamily = montserratFont)
    )
  }
}

@Composable
fun montserratFamily(): FontFamily = FontFamily(
  Font(Res.font.Montserrat, FontWeight.Light),
  Font(Res.font.Montserrat, FontWeight.Normal),
  Font(Res.font.Montserrat, FontWeight.Medium),
  Font(Res.font.Montserrat, FontWeight.SemiBold),
  Font(Res.font.Montserrat, FontWeight.Bold)
)
