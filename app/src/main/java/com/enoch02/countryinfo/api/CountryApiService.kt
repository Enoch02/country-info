package com.enoch02.countryinfo.api

import android.content.Context
import com.enoch02.countryinfo.model.Country
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Uses retrofit to call the API endpoint
 *
 */
interface CountryApiService {

    @GET("all")
    suspend fun getAllCountries(
        @Query("fields") fields: String = "name,subregion,flags,population,capital,continents,callingCodes,coatOfArms,timezones",
    ): List<Country>


    companion object {
        private var apiService: CountryApiService? = null

        fun getInstance(context: Context): CountryApiService {
            val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB Cache
            val cache = Cache(context.cacheDir, cacheSize)

            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://restcountries.com/v3.1/")
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