package kambi.victor.walenje.feature.welcome.view_models

import androidx.lifecycle.ViewModel
import kambi.victor.walenje.feature.welcome.logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SetPinScreenViewModel : ViewModel() {
  private val _pin = MutableStateFlow("")
  val pin = _pin.asStateFlow()

  private val _confirmPin = MutableStateFlow("")
  val confirmPin = _confirmPin.asStateFlow()

  fun setPin(pin: String) {
    _pin.value = pin
  }

  fun setConfirmPin(pin: String) {
    _confirmPin.value = pin
  }

  fun confirmPin(): Boolean {
    logger.info { "Confirm Pin :: ${pin.value == confirmPin.value}" }
    return pin.value == confirmPin.value
  }
}
