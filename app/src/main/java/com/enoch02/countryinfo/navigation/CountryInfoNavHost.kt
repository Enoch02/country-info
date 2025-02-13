package com.enoch02.countryinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.enoch02.countryinfo.ui.screens.country_detail.CountryDetailScreen
import com.enoch02.countryinfo.ui.screens.country_list.CountryListScreen

@Composable
fun CountryInfoNavHost(
    navController: NavHostController = rememberNavController(),
    darkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = CountryInfoDestination.CountryList.route,
        builder = {
            composable(CountryInfoDestination.CountryList.route) {
                CountryListScreen(
                    navController = navController,
                    darkTheme = darkTheme,
                    onToggleTheme = onToggleTheme
                )
            }

            composable(
                route = CountryInfoDestination.CountryDetail.route + "/{name}",
                arguments = listOf(navArgument(name = "name") { type = NavType.StringType })
            ) { entry ->
                entry.arguments?.getString("name")?.let { name ->
                    CountryDetailScreen(navController = navController, countryName = name)
                }
            }
        }
    )
}