package pl.pracownia.pokertables.ui.feature.rings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.pracownia.pokertables.base.BaseViewModel
import pl.pracownia.pokertables.model.data.PokerRepository
import javax.inject.Inject

@HiltViewModel
class RingsViewModel @Inject constructor(private val repository: PokerRepository) :
  BaseViewModel<RingsContract.Event, RingsContract.State, RingsContract.Effect>() {

  init {
    viewModelScope.launch { getRingCategories() }
  }

  override fun setInitialState() =
    RingsContract.State(rings = listOf(), isLoading = true)

  override fun handleEvents(event: RingsContract.Event) {
    when (event) {
      is RingsContract.Event.RingSelection -> {
        setEffect { RingsContract.Effect.Navigation.ToCategoryDetails(event.ringId) }
      }
    }
  }

  private suspend fun getRingCategories() {
    val rings = repository.getRings()
    setState {
      copy(rings = rings, isLoading = false)
    }
    setEffect { RingsContract.Effect.DataWasLoaded }
  }
}
