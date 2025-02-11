package com.enoch02.countryinfo.ui.screens.country_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.enoch02.countryinfo.model.CountryData
import com.enoch02.countryinfo.model.CountryHref
import com.enoch02.countryinfo.model.Covid19Data
import com.enoch02.countryinfo.model.PresidentData
import com.enoch02.countryinfo.model.PresidentHref
import com.enoch02.countryinfo.model.StateData

@Composable
fun CountryDetailView(
    modifier: Modifier = Modifier,
    countryData: CountryData,
    statesData: List<StateData>,
) {
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(200.dp)
        ) {
            AsyncImage(
                model = countryData.href.flag,
                contentDescription = "${countryData.name}'s Flag",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Spacer(Modifier.height(12.dp))

        CountryDetailText(data = "Population", value = countryData.population)
        CountryDetailText(data = "Capital City", value = countryData.capital)
        if (countryData.currentPresident != null) {
            CountryDetailText(
                data = "Current President",
                value = countryData.currentPresident.name
            )
        }
        CountryDetailText(data = "Continent", value = countryData.continent)
        CountryDetailText(data = "Country Code", value = countryData.phoneCode)

        Spacer(Modifier.height(12.dp))

        if (statesData.isNotEmpty()) {
            Text(text = "States", style = MaterialTheme.typography.labelLarge)
            LazyColumn(
                content = {
                    items(statesData) { stateData ->
                        ListItem(
                            headlineContent = {
                                Text(stateData.name)
                            },
                            supportingContent = {
                                stateData.region?.let { Text(it) }
                            }
                        )
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    CountryDetailView(
        statesData = emptyList(),
        countryData = CountryData(
            name = "Nigeria",
            fullName = "Federal Republic of Nigeria",
            capital = "Abuja",
            iso2 = "NG",
            iso3 = "NGA",
            covid19 = Covid19Data(
                totalCase = "67,557",
                totalDeaths = "1,173",
                lastUpdated = "2020-12-01T08:35:50.000000Z"
            ),
            currentPresident = PresidentData(
                name = "Muhammadu Buhari",
                gender = "Male",
                appointmentStartDate = "2015-05 -29",
                appointmentEndDate = "null",
                href = PresidentHref(
                    self = "https://restfulcountries.com/api/v1/countries/Nigeria/presidents/Muhammadu-Buhari",
                    country = "https://restfulcountries.com/api/v1/countries/Nigeria",
                    picture = "https://restfulcountries.com/storage/images/presidents/muhammadu-buharipxpjw98lcj.jpg"
                )
            ),
            currency = "NGN",
            phoneCode = "234",
            continent = "Africa",
            description = """Nigeria is the
            most populous country in Africa and the seventh most populous country in the world,
            with an estimated
            206 million inhabitants as of late 2019.The name Nigeria was taken from the Niger River running through the country.""",
            size = "923,768 km²",
            independenceDate = "1960-10-01",
            population = "208,355,710",
            href = CountryHref(
                self = "https://restfulcountries.com/api/v1/countries/Nigeria",
                states = "https://restfulcountries.com/api/v1/countries/Nigeria/states",
                presidents = "https://restfulcountries.com/api/v1/countries/Nigeria/presidents",
                flag = "https://restfulcountries.com/assets/images/flags/Nigeria.png)"
            )
        )
    )
}
