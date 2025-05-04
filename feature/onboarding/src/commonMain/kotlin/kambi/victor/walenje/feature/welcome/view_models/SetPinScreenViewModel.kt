package kambi.victor.walenje.feature.welcome.view_models

import kambi.victor.walenje.core.utils.mvi.BaseViewModel
import kambi.victor.walenje.core.utils.mvi.ResourceUiState
import kambi.victor.walenje.feature.welcome.SetPinContract
import kambi.victor.walenje.feature.welcome.log

class SetPinScreenViewModel :
  BaseViewModel<SetPinContract.Event, SetPinContract.State, SetPinContract.Effect>() {
  override fun createInitialState(): SetPinContract.State {
    return SetPinContract.State(
      pin = ResourceUiState.Idle,
      confirmPin = ResourceUiState.Idle,
      isPinMatching = ResourceUiState.Idle,
    )
  }

  override fun handleEvent(event: SetPinContract.Event) {
    when (event) {
      SetPinContract.Event.NavigateBack -> {
        setEffect { SetPinContract.Effect.NavigateToBackScreen }
      }
      SetPinContract.Event.NavigateNext -> {
        setEffect { SetPinContract.Effect.NavigateToNextScreen }
      }
      is SetPinContract.Event.SetConfirmPin -> {
        setConfirmPin(event.pin)
        confirmPin()
      }
      is SetPinContract.Event.SetPin -> {
        setPin(event.pin)
      }
    }
  }

  private fun setPin(pin: String) {
    log.i { "In view model:: $pin" }
    setState { copy(pin = ResourceUiState.Success(pin)) }
    setEffect { SetPinContract.Effect.PinConfigured }
  }

  private fun setConfirmPin(pin: String) {
    log.i { "In view model:: $pin" }
    setState { copy(confirmPin = ResourceUiState.Success(pin)) }
    setEffect { SetPinContract.Effect.ConfirmPinConfigured }
  }

  private fun confirmPin() {
    val isPinMatching = state.value.pin == state.value.confirmPin
    log.i { "Pin matching is :: $isPinMatching" }

    when {
      isPinMatching -> {
        setState { copy(isPinMatching = ResourceUiState.Success(true)) }
        setEffect { SetPinContract.Effect.PinMatched }
      }
      else -> {
        setState {
          copy(
            pin = ResourceUiState.Idle,
            confirmPin = ResourceUiState.Idle,
            isPinMatching = ResourceUiState.Error(),
          )
        }
        setEffect { SetPinContract.Effect.PinMismatched }
      }
    }
  }
}
