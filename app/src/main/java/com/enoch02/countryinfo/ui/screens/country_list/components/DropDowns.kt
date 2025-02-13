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

/**
 * Display a multi-choice list of options for the user to choose from
 *
 * @param title shown at the top of possible values
 * @param values a list of values the user can choose from
 * @param selectedValues a list of values selected by the user
 * @param addValue add a new value to the list of selected values
 * @param removeValue remove a value from the list of selected values
 */
@Composable
fun MultiChoiceDropdown(
    modifier: Modifier = Modifier,
    title: String,
    values: List<String>,
    selectedValues: List<String>,
    addValue: (String) -> Unit,
    removeValue: (String) -> Unit,
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
                    text = title,
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
                items(values) { continent ->
                    ListItem(
                        headlineContent = {
                            Text(text = continent)
                        },
                        trailingContent = {
                            Checkbox(
                                checked = continent in selectedValues,
                                onCheckedChange = {
                                    if (it) {
                                        addValue(continent)
                                    } else {
                                        removeValue(continent)
                                    }
                                }
                            )
                        },
                        modifier = Modifier.clickable {
                            if (continent in selectedValues) {
                                removeValue(continent)
                            } else {
                                addValue(continent)
                            }
                        }
                    )

                }
            }
        }
    }
}
