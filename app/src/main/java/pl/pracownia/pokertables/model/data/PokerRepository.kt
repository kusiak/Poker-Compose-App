package pl.pracownia.pokertables.model.data

import pl.pracownia.pokertables.model.Ring
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokerRepository @Inject constructor(private val pokerApi: PokerApi) {
  suspend fun getRings(): List<Ring> {
    return pokerApi.getRings().rings
  }

  
}