package pl.pracownia.pokertables.ui.feature.rings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import pl.pracownia.pokertables.model.Ring

data class RingsViewModelState(
  val rings: List<Ring>? = null,
  val isLoading: Boolean = false,
)

class RingsViewModel : ViewModel() {
  val viewModelState = MutableStateFlow(RingsViewModelState(isLoading = true))

  init {
  }


}