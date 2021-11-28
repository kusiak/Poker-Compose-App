package pl.pracownia.pokertables.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import pl.pracownia.pokertables.model.data.PokerApi
import pl.pracownia.pokertables.model.data.PokerApi.Companion.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PokerApiProvider {

  @Provides
  @Singleton
  fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
    return OkHttpClient
      .Builder()
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return Retrofit
      .Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(API_URL)
      .build()
  }

  @Provides
  @Singleton
  fun providePokerApiService(
    retrofit: Retrofit
  ): PokerApi.Service {
    return retrofit.create(PokerApi.Service::class.java)
  }

}