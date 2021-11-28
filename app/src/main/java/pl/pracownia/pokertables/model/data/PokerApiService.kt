package pl.pracownia.pokertables.model.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import pl.pracownia.pokertables.model.response.ErrorResponse
import pl.pracownia.pokertables.model.response.RingsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.replaypoker.com/"

//TODO add dependency injection
private val retrofit: Retrofit = Retrofit.Builder()
  .baseUrl(BASE_URL)
  .addCallAdapterFactory(NetworkResponseAdapterFactory())
  .addConverterFactory(GsonConverterFactory.create())
  .build()

interface PokerApiService {
  @GET("rings")
  suspend fun getRings(): NetworkResponse<RingsResponse, ErrorResponse>
}

object PokerApi {
  val retrofitService: PokerApiService by lazy {
    retrofit.create(PokerApiService::class.java)
  }
}