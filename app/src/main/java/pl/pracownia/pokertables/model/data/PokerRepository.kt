package pl.pracownia.pokertables.model.data

import pl.pracownia.pokertables.model.Ring
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokerRepository @Inject constructor(private val pokerApi: PokerApi) {
  private var cachedRings: List<Ring>? = null

  suspend fun getRings(): List<Ring> {
    var cachedRings = cachedRings
    if (cachedRings == null) {
      cachedRings = pokerApi.getRings().rings
      this.cachedRings = cachedRings
    }
    return cachedRings
  }
}