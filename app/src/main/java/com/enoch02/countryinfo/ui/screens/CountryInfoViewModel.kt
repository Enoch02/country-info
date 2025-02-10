package com.enoch02.countryinfo.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enoch02.countryinfo.R
import com.enoch02.countryinfo.api.CountryApiService
import com.enoch02.countryinfo.model.CountryApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "CLViewModel"

class CountryInfoViewModel : ViewModel() {
    private val apiService = CountryApiService.getInstance()
    var contentState: ContentState by mutableStateOf(ContentState.Loading)

    fun getAllCountries(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                contentState = ContentState.Loading
                contentState =
                    ContentState.Loaded(apiService.getAllCountries("Bearer ${context.getString(R.string.country_api_key)}"))
            } catch (e: Exception) {
                Log.e(TAG, "getAllCountries: ${e.message}")
                contentState = ContentState.Error(e.message.toString())
            }
        }
    }

    fun getCountryWith(name: String) {
//        return contentState.
    }
}

sealed class ContentState {
    data object Loading : ContentState()
    data class Loaded(val documents: CountryApiResponse) : ContentState()
    data class Error(val message: String) : ContentState()
}