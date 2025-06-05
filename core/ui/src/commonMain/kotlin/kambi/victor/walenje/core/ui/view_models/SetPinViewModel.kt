package kambi.victor.walenje.core.ui.view_models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SetPinViewModel : ViewModel() {
  private val _pin = MutableStateFlow("")
  val pin = _pin.asStateFlow()

  fun onPinChange(entry: String) {
    if (entry.length <= 6) _pin.value = entry
  }
}
