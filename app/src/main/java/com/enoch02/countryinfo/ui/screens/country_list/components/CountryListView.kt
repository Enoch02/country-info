package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enoch02.countryinfo.model.CountryData

@Composable
fun CountryListView(
    modifier: Modifier = Modifier,
    countries: List<CountryData>,
    onItemClick: (name: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        content = {
            items(countries) { country ->
                CountryListItem(
                    flagUrl = country.href.flag,
                    name = country.name,
                    capital = country.capital,
                    onClick = {
                        onItemClick(country.name)
                    }
                )
            }
        }
    )
}