package kambi.victor.walenje.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import walenje.core.designsystem.generated.resources.Montserrat
import walenje.core.designsystem.generated.resources.Res

@Composable
fun getSceptreTypography(): Typography {
  val montserratFont =
    FontFamily(
      Font(Res.font.Montserrat, FontWeight.Light),
      Font(Res.font.Montserrat, FontWeight.Normal),
      Font(Res.font.Montserrat, FontWeight.Medium),
      Font(Res.font.Montserrat, FontWeight.SemiBold),
      Font(Res.font.Montserrat, FontWeight.Bold),
    )

  val baseline = Typography()

  return Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = montserratFont),
    displayMedium = baseline.displayMedium.copy(fontFamily = montserratFont),
    displaySmall = baseline.displaySmall.copy(fontFamily = montserratFont),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = montserratFont),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = montserratFont),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = montserratFont),
    titleLarge = baseline.titleLarge.copy(fontFamily = montserratFont),
    titleMedium = baseline.titleMedium.copy(fontFamily = montserratFont),
    titleSmall = baseline.titleSmall.copy(fontFamily = montserratFont),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = montserratFont),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = montserratFont),
    bodySmall = baseline.bodySmall.copy(fontFamily = montserratFont),
    labelLarge = baseline.labelLarge.copy(fontFamily = montserratFont),
    labelMedium = baseline.labelMedium.copy(fontFamily = montserratFont),
    labelSmall = baseline.labelSmall.copy(fontFamily = montserratFont),
  )
}

@Composable
fun getSceptreFamily(): FontFamily {
  return FontFamily(
    Font(Res.font.Montserrat, FontWeight.Light),
    Font(Res.font.Montserrat, FontWeight.Normal),
    Font(Res.font.Montserrat, FontWeight.Medium),
    Font(Res.font.Montserrat, FontWeight.SemiBold),
    Font(Res.font.Montserrat, FontWeight.Bold),
  )
}
