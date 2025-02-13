package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enoch02.countryinfo.R

@Composable
fun ContinentDropdown(
    modifier: Modifier = Modifier,
    continents: List<String>,
    selectedContinents: List<String>,
    addContinent: (String) -> Unit,
    removeContinent: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = "Continent",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            },
            trailingContent = {
                Icon(
                    painter = if (expanded) {
                        painterResource(R.drawable.baseline_arrow_drop_up_24)
                    } else {
                        painterResource(R.drawable.baseline_arrow_drop_down_24)
                    },
                    contentDescription = "Dropdown Arrow"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }
        )

        AnimatedVisibility(expanded) {
            LazyColumn {
                items(continents) { continent ->
                    ListItem(
                        headlineContent = {
                            Text(text = continent)
                        },
                        trailingContent = {
                            Checkbox(
                                checked = continent in selectedContinents,
                                onCheckedChange = {
                                    if (it) {
                                        addContinent(continent)
                                    } else {
                                        removeContinent(continent)
                                    }
                                }
                            )
                        },
                        modifier = Modifier.clickable {
                            if (continent in selectedContinents) {
                                removeContinent(continent)
                            } else {
                                addContinent(continent)
                            }
                        }
                    )

                }
            }
        }
    }
}


@Composable
fun TimeZoneDropdown(
    modifier: Modifier = Modifier,
    timezones: List<String>,
    selectedTimezones: List<String>,
    addTimezone: (String) -> Unit,
    removeTimezone: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = "Time Zone",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            },
            trailingContent = {
                Icon(
                    painter = if (expanded) {
                        painterResource(R.drawable.baseline_arrow_drop_up_24)
                    } else {
                        painterResource(R.drawable.baseline_arrow_drop_down_24)
                    },
                    contentDescription = "Dropdown Arrow"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }
        )

        AnimatedVisibility(expanded) {
            LazyColumn {
                items(timezones) { continent ->
                    ListItem(
                        headlineContent = {
                            Text(text = continent)
                        },
                        trailingContent = {
                            Checkbox(
                                checked = continent in selectedTimezones,
                                onCheckedChange = {
                                    if (it) {
                                        addTimezone(continent)
                                    } else {
                                        removeTimezone(continent)
                                    }
                                }
                            )
                        },
                        modifier = Modifier.clickable {
                            if (continent in selectedTimezones) {
                                removeTimezone(continent)
                            } else {
                                addTimezone(continent)
                            }
                        }
                    )

                }
            }
        }
    }
}