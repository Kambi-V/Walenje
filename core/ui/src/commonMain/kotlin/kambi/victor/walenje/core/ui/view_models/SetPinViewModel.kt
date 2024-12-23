package kambi.victor.walenje.core.ui.view_models

import androidx.lifecycle.ViewModel
import kambi.victor.walenje.core.ui.logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SetPinViewModel : ViewModel() {
  private val _pin = MutableStateFlow("")
  val pin = _pin.asStateFlow()

  fun onPinChange(entry: String) {
    logger.error { "In Set pin View model >>>>>>>>>>>>>>> $entry" }
    if (entry.length <= 6) _pin.value = entry
  }
}
