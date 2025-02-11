package com.enoch02.countryinfo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.enoch02.countryinfo.navigation.CountryInfoNavHost
import com.enoch02.countryinfo.ui.theme.CountryInfoTheme

class MainActivity : ComponentActivity() {
    private lateinit var prefs: SharedPreferences
    private val key = "dark"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        prefs = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        setContent {
            var darkTheme by rememberSaveable { mutableStateOf(loadBoolean(key)) }

            CountryInfoTheme(darkTheme = darkTheme) {
                CountryInfoNavHost(
                    onToggleTheme = {
                        darkTheme = !darkTheme
                        saveBoolean(key, darkTheme)
                    }
                )
            }
        }
    }

    private fun saveBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    private fun loadBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }
}
