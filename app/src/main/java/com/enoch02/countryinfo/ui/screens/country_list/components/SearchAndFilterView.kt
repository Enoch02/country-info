package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enoch02.countryinfo.R

@Composable
fun SearchAndFilterView(
    modifier: Modifier = Modifier,
    darkTheme: Boolean,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onFilterByContinent: (query: String, continents: List<String>, timezones: List<String>) -> Unit,
) {
    var query by remember { mutableStateOf("") }
    var showFilters by remember { mutableStateOf(false) }
    val continents = listOf(
        "Africa",
        "Asia",
        "Australia",
        "Europe",
        "North America",
        "South America",
        "Central America",
        "Oceania"
    )
    val selectedContinents = remember { mutableStateListOf<String>() }
    val timezones = listOf(
        "UTC-12:00", "UTC-11:00", "UTC-10:00", "UTC-09:00",
        "UTC-08:00", "UTC-07:00", "UTC-06:00", "UTC-05:00",
        "UTC-04:00", "UTC-03:00", "UTC-02:00", "UTC-01:00",
        "UTC+00:00", "UTC+01:00", "UTC+02:00", "UTC+03:00",
        "UTC+04:00", "UTC+05:00", "UTC+06:00", "UTC+07:00",
        "UTC+08:00", "UTC+09:00", "UTC+10:00", "UTC+11:00",
        "UTC+12:00", "UTC+13:00", "UTC+14:00"
    )
    val selectedTimezones = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier.padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CountrySearchBar(
            query = query,
            onQueryChange = { query = it },
            placeholder = "Search Country",
            onSearch = { onSearch(query) },
            onSearchQueryClear = {
                query = ""
                onClear()
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val colors = if (darkTheme) {
                ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            } else {
                ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            }

            ElevatedButton(
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_language_24),
                        contentDescription = "Language"
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(text = "EN") //TODO
                },
                onClick = {

                },
                shape = RectangleShape,
                modifier = Modifier.alpha(0f)
            )

            OutlinedButton(
                content = {
                    Icon(
                        painter = painterResource(R.drawable.outline_filter_alt_24),
                        contentDescription = "Filter"
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(text = "Filter")
                },
                onClick = {
                    showFilters = true
                },
                shape = RectangleShape,
                colors = colors
            )
        }
    }

    if (showFilters) {
        FilterBottomSheet(
            modifier = Modifier.fillMaxHeight(0.75f),
            onDismiss = { showFilters = false },
            continents = continents,
            selectedContinents = selectedContinents,
            addContinent = { selectedContinents.add(it) },
            removeContinent = { selectedContinents.remove(it) },
            timezones = timezones,
            selectedTimezones = selectedTimezones,
            addTimezone = { selectedTimezones.add(it) },
            removeTimezone = { selectedTimezones.remove(it) },
            onClear = {
                selectedContinents.clear()
                selectedTimezones.clear()
            },
            onShowResults = {
                onFilterByContinent(query, selectedContinents, selectedTimezones)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    continents: List<String>,
    selectedContinents: List<String>,
    addContinent: (String) -> Unit,
    removeContinent: (String) -> Unit,
    timezones: List<String>,
    selectedTimezones: List<String>,
    addTimezone: (String) -> Unit,
    removeTimezone: (String) -> Unit,
    onClear: () -> Unit,
    onShowResults: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        windowInsets = WindowInsets.navigationBars,
        modifier = modifier.navigationBarsPadding(),
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Filter", style = MaterialTheme.typography.titleLarge)

                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(bottom = 78.dp)) {
                    MultiChoiceDropdown(
                        title = "Continent",
                        values = continents,
                        selectedValues = selectedContinents,
                        addValue = { continent ->
                            addContinent(continent)
                        },
                        removeValue = { continent ->
                            removeContinent(continent)
                        }
                    )

                    MultiChoiceDropdown(
                        title = "Time Zone",
                        values = timezones,
                        selectedValues = selectedTimezones,
                        addValue = { timezone ->
                            addTimezone(timezone)
                        },
                        removeValue = { timezone ->
                            removeTimezone(timezone)
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .padding(bottom = 28.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    OutlinedButton(
                        content = { Text(text = "Reset") },
                        onClick = {
                            onClear()
                        },
                        shape = RectangleShape
                    )

                    Button(
                        content = { Text(text = "Show Results") },
                        onClick = {
                            onShowResults()
                            onDismiss()
                        },
                        shape = RectangleShape
                    )
                }
            }
        }
    )
}


@Preview
@Composable
private fun Preview() {
    SearchAndFilterView(
        darkTheme = false,
        onSearch = {},
        onClear = {},
        onFilterByContinent = { _, _, _ -> })
}