package com.enoch02.countryinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.enoch02.countryinfo.navigation.CountryInfoNavHost
import com.enoch02.countryinfo.ui.theme.CountryInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryInfoTheme {
                CountryInfoNavHost()
            }
        }
    }
}
