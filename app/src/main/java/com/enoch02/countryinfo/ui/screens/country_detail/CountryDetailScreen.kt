package com.enoch02.countryinfo.ui.screens.country_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enoch02.countryinfo.R
import com.enoch02.countryinfo.ui.common_views.ErrorMessageView
import com.enoch02.countryinfo.ui.common_views.LoadingView
import com.enoch02.countryinfo.ui.screens.ContentState
import com.enoch02.countryinfo.ui.screens.CountryInfoViewModel
import com.enoch02.countryinfo.ui.screens.country_detail.components.CountryDetailView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDetailScreen(
    viewModel: CountryInfoViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    countryName: String,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getAllCountries(context)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = countryName) },
                navigationIcon = {
                    IconButton(
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_back_24),
                                contentDescription = "Navigate Back"
                            )
                        },
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                }
            )
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
                                onRetry = { viewModel.getAllCountries(context) },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        is ContentState.Loaded -> {
                            LaunchedEffect(Unit) {
                                viewModel.loadCountryWith(countryName)
                            }

                            viewModel.country?.let {
                                CountryDetailView(
                                    country = it,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }
                        }

                        is ContentState.Loading -> {
                            LoadingView(
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                }
            )
        }
    )
}