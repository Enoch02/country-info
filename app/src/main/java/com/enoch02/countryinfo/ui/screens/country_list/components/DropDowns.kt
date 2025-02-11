package com.enoch02.countryinfo.ui.screens.country_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Continent",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Icon(
                painter = if (expanded) {
                    painterResource(R.drawable.baseline_arrow_drop_up_24)
                } else {
                    painterResource(R.drawable.baseline_arrow_drop_down_24)
                },
                contentDescription = "Dropdown Arrow"
            )
        }

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
                                    }
                                    else {
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

