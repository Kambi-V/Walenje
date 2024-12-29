package kambi.victor.walenje.core.utils.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {
  private val initialState: State by lazy { createInitialState() }

  abstract fun createInitialState(): State

  private val currentState: State
    get() = state.value

  private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
  val state = _state.asStateFlow()

  private val _event = MutableSharedFlow<Event>()
  val event = _event.asSharedFlow()

  private val _effect = Channel<Effect>()
  val effect = _effect.receiveAsFlow()

  init {
    subscribeEvents()
  }

  /** Handle each event */
  abstract fun handleEvent(event: Event)

  /** Start listening to Event */
  private fun subscribeEvents() {
    viewModelScope.launch { event.collect { handleEvent(it) } }
  }

  /** Set new Event */
  fun setEvent(event: Event) {
    val newEvent = event
    viewModelScope.launch { _event.emit(newEvent) }
  }

  /** Set new State */
  fun setState(reduce: State.() -> State) {
    val newState = currentState.reduce()
    _state.value = newState
  }

  /** Set new Effect */
  fun setEffect(builder: () -> Effect) {
    val effectValue = builder()
    viewModelScope.launch { _effect.send(effectValue) }
  }
}
