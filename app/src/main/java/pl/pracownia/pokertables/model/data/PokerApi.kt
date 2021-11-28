package pl.pracownia.pokertables.model.data

import pl.pracownia.pokertables.model.response.RingsResponse
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokerApi @Inject constructor(private val service: Service) {

  suspend fun getRings(): RingsResponse = service.getRings()

  interface Service {
    @GET("rings")
    suspend fun getRings(): RingsResponse
  }

  companion object {
    const val API_URL = "https://www.replaypoker.com/"
  }
}
