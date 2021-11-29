package pl.pracownia.pokertables.ui.feature.ring_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.pracownia.pokertables.NavigationKeys
import pl.pracownia.pokertables.base.BaseViewModel
import pl.pracownia.pokertables.model.data.PokerRepository
import javax.inject.Inject

@HiltViewModel
class RingDetailsViewModel @Inject constructor(
  private val stateHandle: SavedStateHandle,
  private val repository: PokerRepository
) :
  BaseViewModel<RingDetailsContract.Event, RingDetailsContract.State, RingDetailsContract.Effect>() {

  init {
    viewModelScope.launch {
      val ringId = stateHandle.get<Long>(NavigationKeys.Arg.RING_ID)
        ?: throw IllegalStateException("No ringId was passed to destination.")
      val categories = repository.getRings()
      val category = categories.first { it.id == ringId }
      setState { copy(ring = category) }
    }
  }

  override fun setInitialState() = RingDetailsContract.State(null)

  override fun handleEvents(event: RingDetailsContract.Event) {}
}
