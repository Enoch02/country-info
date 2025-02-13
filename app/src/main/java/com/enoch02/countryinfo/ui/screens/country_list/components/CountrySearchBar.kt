package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp

/**
 * Obtains the user query and initiates a search
 *
 * @param query what to search for
 * @param onQueryChange a callback function triggered when the query is modifier
 * @param placeholder search bar hint
 * @param onSearch specifies how to initiate the search
 * @param onSearchQueryClear code to run when the search bar's clear button is tapped
 */
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
    colors: TextFieldColors = TextFieldDefaults.colors(),
    onSearch: () -> Unit,
    onSearchQueryClear: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

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
                IconButton(onClick = onSearchQueryClear) {
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
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearch()
            }
        ),
        colors = colors
    )
}