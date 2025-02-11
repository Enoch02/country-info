package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enoch02.countryinfo.R

@Composable
fun SearchAndFilterView(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
) {
    var query by remember { mutableStateOf("") }

    if (query.isEmpty()) {
        onClear()
    }

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

                    Text(text = "EN") //TODO
                },
                onClick = {

                },
                shape = RectangleShape
            )

            ElevatedButton(
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_filter_list_alt_24),
                        contentDescription = "Language"
                    )

                    Text(text = "Filter") //TODO
                },
                onClick = {

                },
                shape = RectangleShape
            )
        }
    }
}

@Composable
fun CountrySearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    leadingIcon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search"
        )
    },
    onSearch: () -> Unit,
    onClear: () -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        placeholder = {
            Text(
                text = placeholder,
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearch() })
    )
}


@Preview
@Composable
private fun Preview() {
    SearchAndFilterView(onSearch = {}, onClear = {})
}