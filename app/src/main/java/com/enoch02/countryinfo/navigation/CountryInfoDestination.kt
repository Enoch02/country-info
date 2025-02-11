package com.enoch02.countryinfo.navigation

sealed class CountryInfoDestination(val route: String) {

    data object CountryList : CountryInfoDestination("country_list")
    data object CountryDetail : CountryInfoDestination("country_detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}