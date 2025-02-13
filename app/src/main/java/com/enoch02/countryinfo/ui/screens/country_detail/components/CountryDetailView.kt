package com.enoch02.countryinfo.ui.screens.country_detail.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.enoch02.countryinfo.model.Country

@Composable
fun CountryDetailView(
    modifier: Modifier = Modifier,
    country: Country,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val capital = if (country.capital.isNotEmpty()) {
        country.capital.first()
    } else {
        ""
    }
    val continents = country.continents.joinToString(", ")

    if (isLandscape) {
        Row(modifier = Modifier.fillMaxWidth()) {
            FlagView(
                url = country.flags.png,
                name = country.name.common,
                modifier = Modifier.fillMaxWidth(0.4f)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                content = {
                    CountryDetailText(data = "Population", value = country.population.toString())
                    CountryDetailText(data = "Capital City", value = capital)
                    /*if (country.currentPresident != null) {
                        CountryDetailText(
                            data = "Current President",
                            value = country.currentPresident.name
                        )
                    }*/
                    CountryDetailText(data = "Continent", value = continents)
//                    CountryDetailText(data = "Country Code", value = country.phoneCode)

//                    StatesView(statesData = statesData)
                }
            )
        }
    } else {
        Column(modifier = modifier) {
            FlagView(
                url = country.flags.png,
                name = country.name.common,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            CountryDetailText(data = "Population", value = country.population.toString())
            CountryDetailText(data = "Capital City", value = capital)
//            if (country.currentPresident != null) {
//                CountryDetailText(
//                    data = "Current President",
//                    value = country.currentPresident.name
//                )
//            }
            CountryDetailText(data = "Continent", value = continents)
            CountryDetailText(data = "Region", value = country.subregion)
//            CountryDetailText(data = "Country Code", value = country.phoneCode)

            Spacer(Modifier.height(12.dp))

//            StatesView(statesData = statesData)
        }
    }
}

@Composable
fun FlagView(modifier: Modifier = Modifier, url: String, name: String) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .height(200.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = "${name}'s Flag",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

//@Composable
//fun StatesView(modifier: Modifier = Modifier, statesData: List<StateData>) {
//    if (statesData.isNotEmpty()) {
//        Text(text = "States", style = MaterialTheme.typography.labelLarge)
//        LazyColumn(
//            content = {
//                items(statesData) { stateData ->
//                    ListItem(
//                        headlineContent = {
//                            Text(stateData.name)
//                        },
//                        supportingContent = {
//                            stateData.region?.let { Text(it) }
//                        }
//                    )
//                }
//            }
//        )
//    }
//}
