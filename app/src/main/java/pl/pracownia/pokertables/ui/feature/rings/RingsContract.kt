package pl.pracownia.pokertables.ui.feature.rings

import pl.pracownia.pokertables.base.UiEvent
import pl.pracownia.pokertables.base.UiEffect
import pl.pracownia.pokertables.base.UiState
import pl.pracownia.pokertables.model.Ring

class RingsContract {
    sealed class Event : UiEvent {
        data class RingSelection(val ringId: Long) : Event()
    }

    data class State(val rings: List<Ring> = listOf(), val isLoading: Boolean = false) : UiState

    sealed class Effect : UiEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToCategoryDetails(val ringId: Long) : Navigation()
        }
    }

}