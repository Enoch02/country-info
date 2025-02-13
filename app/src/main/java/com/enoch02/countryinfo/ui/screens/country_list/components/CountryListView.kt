package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.enoch02.countryinfo.model.Country

/**
 * Displays multiple [Country] in a simple list
 *
 * @param countries a list of [Country] instances to display
 * @param onItemClick what to do when a specific item is tapped
 */
@Composable
fun CountryListView(
    modifier: Modifier = Modifier,
    countries: List<Country>,
    onItemClick: (name: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        content = {
            items(countries) { country ->
                val capital = if (country.capital.isNotEmpty()) {
                    country.capital.first()
                } else {
                    ""
                }

                CountryListItem(
                    flagUrl = country.flags.png,
                    name = country.name.common,
                    capital = capital,
                    onClick = {
                        onItemClick(country.name.common)
                    }
                )
            }
        }
    )
}