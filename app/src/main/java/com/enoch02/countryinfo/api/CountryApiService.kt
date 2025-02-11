package com.enoch02.countryinfo.api

import android.content.Context
import com.enoch02.countryinfo.model.CountryApiResponse
import com.enoch02.countryinfo.model.StateResponse
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface CountryApiService {

    //https://restfulcountries.com/api/v1/countries
    @GET("countries")
    suspend fun getAllCountries(
        @Header("Authorization") bearerToken: String,
    ): CountryApiResponse

    @GET("countries/{country}/states")
    suspend fun getStates(
        @Path("country") country: String,
        @Header("Authorization") bearerToken: String,
    ): StateResponse

    companion object {
        private var apiService: CountryApiService? = null

        fun getInstance(context: Context): CountryApiService {
            val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB Cache
            val cache = Cache(context.cacheDir, cacheSize)

            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://restfulcountries.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor { chain ->
                                val originalRequest = chain.request()
                                val newRequest = originalRequest.newBuilder()
                                    .header("Accept", "application/json")
                                    .build()
                                chain.proceed(newRequest)
                            }
                            // cache interceptor
                            .addInterceptor { chain ->
                                var response: Response = chain.proceed(chain.request())
                                val maxAge = 60 // Cache the response for 60 seconds
                                response = response.newBuilder()
                                    .header("Cache-Control", "public, max-age=$maxAge")
                                    .build()
                                response
                            }
                            .build()
                    )
                    .build()
                    .create(CountryApiService::class.java)
            }
            return apiService!!
        }
    }
}