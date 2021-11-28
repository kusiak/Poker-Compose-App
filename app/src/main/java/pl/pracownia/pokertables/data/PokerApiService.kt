package pl.pracownia.pokertables.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import pl.pracownia.pokertables.data.network.ErrorResponse
import pl.pracownia.pokertables.data.network.RingsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

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