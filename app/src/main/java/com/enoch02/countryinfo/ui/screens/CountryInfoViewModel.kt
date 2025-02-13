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

/**
 *
 * @property apiService to the [CountryApiService] instance
 * @property countries list of countries from the API
 * @property contentState current state of the UI
 * @property country a single country to be displayed in the detail screen
 * @property searchResults filtered list of countries
 * @property showSearchResults changes the list of countries to a filtered one
 */
class CountryInfoViewModel : ViewModel() {
    private var apiService: CountryApiService? = null
    private val countries = mutableStateListOf<Country>()

    var contentState: ContentState by mutableStateOf(ContentState.Loading)
    var country: Country? by mutableStateOf(null)

    val searchResults = mutableStateListOf<Country>()
    var showSearchResults by mutableStateOf(false)

    /**
     * Obtains a list of countries from the API
     *
     * @param context this application context is used to obtain the API instance
     */
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

    /**
     * Load the country to be displayed on the detail screen
     *
     * @param name the country's name
     */
    fun loadCountryWith(name: String) {
        country = countries.first { it.name.common == name }
    }

    /**
     * Filter the countries based on the values of the argument supplied
     *
     * @param query country name to search
     * @param continents list of selected continents
     * @param timezones list of selected timezones
     */
    fun search(
        query: String,
        continents: List<String> = emptyList(),
        timezones: List<String> = emptyList(),
    ) {
        searchResults.clear()

        val filteredByName = if (query.isBlank()) countries else countries.filter {
            it.name.common.contains(query, ignoreCase = true)
        }

        val filteredByContinent =
            if (continents.isEmpty()) filteredByName else filteredByName.filter {
                it.continents.any { continent -> continent in continents }
            }

        val filteredByTimeZone =
            if (timezones.isEmpty()) filteredByContinent else filteredByContinent.filter {
                it.timezones?.any { timezone -> timezone in timezones } ?: false
            }

        searchResults.addAll(filteredByTimeZone)
        showSearchResults = true
    }
}

/**
 * Represents various states of the application UI
 *
 * @property Loading the application is loading content in the background
 * @property Loaded app content has finished loading and is ready to be display
 * @property Error some error has prevented the app from loading successfully
 */
sealed class ContentState {
    data object Loading : ContentState()
    data class Loaded(val countries: List<Country>) : ContentState()
    data class Error(val message: String) : ContentState()
}
