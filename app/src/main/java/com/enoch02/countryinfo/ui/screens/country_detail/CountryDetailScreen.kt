package com.enoch02.countryinfo.ui.screens.country_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enoch02.countryinfo.ui.common_views.ErrorMessageView
import com.enoch02.countryinfo.ui.common_views.LoadingView
import com.enoch02.countryinfo.ui.screens.ContentState
import com.enoch02.countryinfo.ui.screens.CountryInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(viewModel: CountryInfoViewModel = viewModel(), countryName: String) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Hello") })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                content = {
                    when (val content = viewModel.contentState) {
                        is ContentState.Error -> {
                            ErrorMessageView(
                                message = content.message,
                                onRetry = { viewModel.getAllCountries(context) }
                            )
                        }

                        is ContentState.Loaded -> {

                        }

                        is ContentState.Loading -> {
                            LoadingView(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.8f)
                            )
                        }
                    }
                }
            )
        }
    )
}