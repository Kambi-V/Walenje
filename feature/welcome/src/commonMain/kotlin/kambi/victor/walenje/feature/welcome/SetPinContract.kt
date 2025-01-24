package kambi.victor.walenje.feature.welcome

import kambi.victor.walenje.core.utils.mvi.ResourceUiState
import kambi.victor.walenje.core.utils.mvi.UiEffect
import kambi.victor.walenje.core.utils.mvi.UiEvent
import kambi.victor.walenje.core.utils.mvi.UiState

interface SetPinContract {
  sealed interface Event : UiEvent {
    data class SetPin(val pin: String) : Event

    data class SetConfirmPin(val pin: String) : Event

    data object NavigateBack : Event

    data object NavigateNext : Event
  }

  data class State(
    val pin: ResourceUiState<String>,
    val confirmPin: ResourceUiState<String>,
    val isPinMatching: ResourceUiState<Boolean>,
  ) : UiState

  sealed interface Effect : UiEffect {
    data object PinConfigured : Effect

    data object ConfirmPinConfigured : Effect

    data object PinMatched : Effect

    data object PinMismatched : Effect

    data object NavigateToNextScreen : Effect

    object NavigateToBackScreen : Effect
  }
}
