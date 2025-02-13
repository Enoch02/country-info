package com.enoch02.countryinfo.ui.screens.country_detail.components

import androidx.compose.ui.graphics.Color
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.enoch02.countryinfo.model.Country
import java.text.NumberFormat

/**
 * Displays the values obtained from instances of [Country]
 *
 * @param country the [Country] instance whose info will be displayed
 */
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
    val timeZone = country.timezones?.joinToString(", ") ?: ""
    val population = NumberFormat.getNumberInstance().format(country.population)
    val pagerState = rememberPagerState { 2 }

    if (isLandscape) {
        Row(modifier = Modifier.fillMaxWidth()) {
            FlagView(
                url = country.flags.png,
                name = country.name.common,
                modifier = Modifier.fillMaxWidth(0.4f)
            )

            Spacer(Modifier.width(12.dp))

            AsyncImage(
                model = country.coatOfArms.png,
                contentDescription = "${country.name.common} coat of arms",
                modifier = Modifier
                    .padding(12.dp)
                    .height(200.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                content = {
                    CountryDetailText(data = "Population", value = population.toString())
                    CountryDetailText(data = "Region", value = country.subregion)
                    CountryDetailText(data = "Capital City", value = capital)
                    Spacer(Modifier.height(4.dp))

                    /*if (country.currentPresident != null) {
                        CountryDetailText(
                            data = "Current President",
                            value = country.currentPresident.name
                        )
                    }*/
                    CountryDetailText(data = "Continent", value = continents)
                    CountryDetailText(data = "Time Zone", value = timeZone)
//                    CountryDetailText(data = "Country Code", value = country.phoneCode)
                }
            )
        }
    } else {
        Column(modifier = modifier) {
            HorizontalPager(
                state = pagerState,
                pageContent = { page ->
                    when (page) {
                        0 -> {
                            FlagView(
                                url = country.flags.png,
                                name = country.name.common,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        1 -> {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                AsyncImage(
                                    model = country.coatOfArms.png,
                                    contentDescription = "${country.name.common} coat of arms",
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .height(200.dp)
                                )
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            PagerIndicator(
                pagerState = pagerState, modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )

            Spacer(Modifier.height(12.dp))

            CountryDetailText(data = "Population", value = population.toString())
            CountryDetailText(data = "Region", value = country.subregion)
            CountryDetailText(data = "Capital City", value = capital)
            Spacer(Modifier.height(4.dp))

//            if (country.currentPresident != null) {
//                CountryDetailText(
//                    data = "Current President",
//                    value = country.currentPresident.name
//                )
//            }
            CountryDetailText(data = "Continent", value = continents)
            CountryDetailText(data = "Time Zone", value = timeZone)
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

@Composable
fun PagerIndicator(modifier: Modifier = Modifier, pagerState: PagerState) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
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
