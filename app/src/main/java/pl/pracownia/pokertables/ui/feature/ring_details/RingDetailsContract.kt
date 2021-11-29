package pl.pracownia.pokertables.ui.feature.ring_details

import pl.pracownia.pokertables.base.UiEffect
import pl.pracownia.pokertables.base.UiEvent
import pl.pracownia.pokertables.base.UiState
import pl.pracownia.pokertables.model.Ring

class RingDetailsContract {
  sealed class Event : UiEvent

  data class State(
    val ring: Ring?
  ) : UiState

  sealed class Effect : UiEffect
}