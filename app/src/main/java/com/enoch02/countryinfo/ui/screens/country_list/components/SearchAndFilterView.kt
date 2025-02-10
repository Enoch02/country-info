package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enoch02.countryinfo.R

@Composable
fun SearchAndFilterView(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CountrySearchBar(
            query = query,
            onQueryChange = { query = it },
            placeholder = "Search Country"
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
        singleLine = true,
        shape = RectangleShape,
    )
}


@Preview
@Composable
private fun Preview() {
    SearchAndFilterView()
}