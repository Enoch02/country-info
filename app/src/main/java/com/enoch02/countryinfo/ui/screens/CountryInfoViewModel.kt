package com.enoch02.countryinfo.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enoch02.countryinfo.R
import com.enoch02.countryinfo.api.CountryApiService
import com.enoch02.countryinfo.model.CountryApiResponse
import com.enoch02.countryinfo.model.CountryData
import com.enoch02.countryinfo.model.StateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CLViewModel"

class CountryInfoViewModel : ViewModel() {
    private var apiService: CountryApiService? = null
    private val countries = mutableStateListOf<CountryData>()

    var contentState: ContentState by mutableStateOf(ContentState.Loading)
    var country: CountryData? by mutableStateOf(null)
    var statesResponse: StateResponse? by mutableStateOf(null)
    val searchResults = mutableStateListOf<CountryData>()

    fun getAllCountries(context: Context) {
        apiService = CountryApiService.getInstance(context)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                contentState = ContentState.Loading
                val response =
                    apiService!!.getAllCountries("Bearer ${context.getString(R.string.country_api_key)}")

                if (countries.isEmpty()) {
                    countries.addAll(response.data)
                }
                contentState = ContentState.Loaded(response)
            } catch (e: Exception) {
                Log.e(TAG, "getAllCountries: ${e.message}")
                contentState = ContentState.Error(e.message.toString())
            }
        }
    }

    fun loadCountryWith(name: String, context: Context) {
        country = countries.first { it.name == name }
        viewModelScope.launch(Dispatchers.IO) {
            loadStates(context, name)
        }
    }

    fun search(query: String) {
        searchResults.clear()
        searchResults.addAll(countries.filter { it.name.contains(query, ignoreCase = true) })
    }

    private suspend fun loadStates(context: Context, country: String) {
        try {
            statesResponse = apiService?.getStates(
                country = country,
                bearerToken = "Bearer ${context.getString(R.string.country_api_key)}"
            )
        } catch (e: Exception) {
            Log.e(TAG, "loadStates: ${e.message}")
        }
    }
}

sealed class ContentState {
    data object Loading : ContentState()
    data class Loaded(val documents: CountryApiResponse) : ContentState()
    data class Error(val message: String) : ContentState()
}
