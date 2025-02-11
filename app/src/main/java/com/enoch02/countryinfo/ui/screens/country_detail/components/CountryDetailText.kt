package com.enoch02.countryinfo.ui.screens.country_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountryDetailText(modifier: Modifier = Modifier, data: String, value: String) {
    Row(modifier = modifier) {
        Text(text = "$data:", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = value)
    }
}

@Preview
@Composable
private fun Preview() {
    CountryDetailText(
        data = "Population",
        value = "208,355,710",
        modifier = Modifier.background(
            Color.White
        )
    )
}