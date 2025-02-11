package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enoch02.countryinfo.R

@Composable
fun SearchAndFilterView(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onFilterByContinent: (continents: List<String>) -> Unit,
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
        "The Caribean",
        "Central America",
        "Oceana"
    )
    val selectedContinents = remember { mutableStateListOf<String>("Africa") }

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
            onClear = {
                query = ""
                onClear()
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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

            ElevatedButton(
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_filter_list_alt_24),
                        contentDescription = "Filter"
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(text = "Filter")
                },
                onClick = {
                    showFilters = true
                },
                shape = RectangleShape
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
            clearContinents = { selectedContinents.clear() },
            onShowResults = {
                onFilterByContinent(selectedContinents)
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
    clearContinents: () -> Unit,
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

            ContinentDropdown(
                modifier = Modifier.weight(1f),
                continents = continents,
                selectedContinents = selectedContinents,
                addContinent = { continent ->
                    addContinent(continent)
                },
                removeContinent = { continent ->
                    removeContinent(continent)
                }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .padding(bottom = 28.dp)
            ) {
                OutlinedButton(
                    content = { Text(text = "Reset") },
                    onClick = {
                        clearContinents()
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
    )
}


@Preview
@Composable
private fun Preview() {
    SearchAndFilterView(onSearch = {}, onClear = {}, onFilterByContinent = {})
}