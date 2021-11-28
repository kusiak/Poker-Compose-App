package pl.pracownia.pokertables.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface UiState

interface UiEvent

interface UiEffect

abstract class BaseViewModel<Event : UiEvent, UiState : pl.pracownia.pokertables.base.UiState, Effect : UiEffect> :
    ViewModel() {

    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    private val _uiState: MutableState<UiState> = mutableStateOf(initialState)
    val uiState: State<UiState> = _uiState

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _uiEffect: Channel<Effect> = Channel()
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = uiState.value.reducer()
        _uiState.value = newState
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _uiEvent.collect {
                handleEvents(it)
            }
        }
    }

    abstract fun handleEvents(event: Event)

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _uiEffect.send(effectValue) }
    }

}