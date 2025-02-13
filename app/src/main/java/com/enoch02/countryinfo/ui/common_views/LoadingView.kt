package com.enoch02.countryinfo.ui.common_views

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Indeterminate progress indicator
 */
@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = { CircularProgressIndicator() }
    )
}