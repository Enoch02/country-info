package com.enoch02.countryinfo.api

import com.enoch02.countryinfo.model.CountryApiResponse
import com.enoch02.countryinfo.model.StateResponse
import okhttp3.OkHttpClient
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

        fun getInstance(): CountryApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://restfulcountries.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .addInterceptor { chain ->
                                val originalRequest = chain.request()
                                val newRequest = originalRequest.newBuilder()
                                    .header("Accept", "application/json")
                                    .build()
                                chain.proceed(newRequest)
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