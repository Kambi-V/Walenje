package kambi.victor.walenje

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kambi.victor.walenje.core.authentication.BiometricPromptManager

class MainActivity : AppCompatActivity() {

  private val biometrics by lazy { BiometricPromptManager(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent { App(biometrics = biometrics) }
  }
}

@Preview
@Composable
fun AppAndroidPreview() {
  //    App()
}
