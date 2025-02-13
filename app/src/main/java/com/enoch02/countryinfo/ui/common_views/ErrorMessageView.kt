package com.enoch02.countryinfo.ui.common_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * Display an error message and gives the user an action to perform
 *
 * @param message description of the error
 * @param onRetry code to run when the retry button is tapped
 */
@Composable
fun ErrorMessageView(modifier: Modifier = Modifier, message: String, onRetry: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(text = "An Error has occurred!")
            Text(text = message, textAlign = TextAlign.Center)

            Button(
                content = { Text(text = "Retry") },
                onClick = onRetry
            )
        }
    )
}