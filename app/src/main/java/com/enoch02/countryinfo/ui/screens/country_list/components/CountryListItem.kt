package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            Card(
                modifier = Modifier
                    .size(32.dp),
                shape = RoundedCornerShape(6.dp),
                content = {
                    AsyncImage(
                        model = flagUrl,
                        contentDescription = "$name's flag",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
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