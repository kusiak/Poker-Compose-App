package pl.pracownia.pokertables.ui.rings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse.NetworkError
import com.haroldadmin.cnradapter.NetworkResponse.ServerError
import com.haroldadmin.cnradapter.NetworkResponse.Success
import com.haroldadmin.cnradapter.NetworkResponse.UnknownError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.pracownia.pokertables.data.PokerApi
import pl.pracownia.pokertables.data.PokerApiService
import pl.pracownia.pokertables.model.Ring

data class RingsViewModelState(
  val rings: List<Ring>? = null,
  val isLoading: Boolean = false,
)

class RingsViewModel : ViewModel() {
  private val api: PokerApiService = PokerApi.retrofitService
  val viewModelState = MutableStateFlow(RingsViewModelState(isLoading = true))

  init {
    refreshRings()
  }

  fun refreshRings() {
    viewModelState.update { it.copy(isLoading = true) }

    viewModelScope.launch {
      when (api.getRings()) {
        is Success -> {
          viewModelState.update { it.copy(rings = it.rings) }
        }
        else -> TODO() //its an error, panic.
      }
      viewModelState.update { it.copy(isLoading = false) }
    }
  }
}