package com.enoch02.countryinfo.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enoch02.countryinfo.api.CountryApiService
import com.enoch02.countryinfo.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CLViewModel"

class CountryInfoViewModel : ViewModel() {
    private var apiService: CountryApiService? = null
    private val countries = mutableStateListOf<Country>()

    var contentState: ContentState by mutableStateOf(ContentState.Loading)
    var country: Country? by mutableStateOf(null)

    val searchResults = mutableStateListOf<Country>()
    var showSearchResults by mutableStateOf(false)

    fun getAllCountries(context: Context) {
        apiService = CountryApiService.getInstance(context)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                contentState = ContentState.Loading
                val response =
                    apiService!!.getAllCountries()
                val sortedResponse = response.sortedBy { it.name.common }

                if (countries.isEmpty()) {
                    countries.addAll(sortedResponse)
                }
                contentState = ContentState.Loaded(sortedResponse)
            } catch (e: Exception) {
                Log.e(TAG, "getAllCountries: ${e.message}")
                contentState = ContentState.Error(e.message.toString())
            }
        }
    }

    fun loadCountryWith(name: String) {
        country = countries.first { it.name.common == name }
    }

    fun search(query: String) {
        searchResults.clear()
        searchResults.addAll(countries.filter { it.name.common.contains(query, ignoreCase = true) })
        showSearchResults = true
    }

    fun filterByContinents(continents: List<String>) {
        searchResults.clear()
        searchResults.addAll(countries.filter { it.continents.any { continent -> continent in continents } })
        showSearchResults = true
    }

    /*private suspend fun loadStates(context: Context, country: String) {
        try {
            statesResponse = apiService?.getStates(
                country = country,
                bearerToken = "Bearer ${context.getString(R.string.country_api_key)}"
            )
        } catch (e: Exception) {
            Log.e(TAG, "loadStates: ${e.message}")
        }
    }*/
}

sealed class ContentState {
    data object Loading : ContentState()
    data class Loaded(val countries: List<Country>) : ContentState()
    data class Error(val message: String) : ContentState()
}
