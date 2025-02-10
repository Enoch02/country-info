package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CountryListItem(
    modifier: Modifier = Modifier,
    flagUrl: String,
    name: String,
    capital: String,
    onClick: () -> Unit,
) {
    ListItem(
        leadingContent = {
            AsyncImage(
                model = flagUrl,
                contentDescription = "$name's flag",
                modifier = Modifier.size(24.dp)
            )
        },
        headlineContent = { Text(text = name) },
        supportingContent = { Text(text = capital) },
        modifier = modifier.clickable {
            onClick()
        }
    )
}

@Preview
@Composable
private fun Preview() {
    CountryListItem(
        flagUrl = "https://restfulcountries.com/storage/images/flags/Nigeria.png",
        name = "Nigeria",
        capital = "Abuja",
        onClick = {}
    )
}