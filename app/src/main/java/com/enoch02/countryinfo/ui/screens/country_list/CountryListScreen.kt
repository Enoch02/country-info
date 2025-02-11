package com.enoch02.countryinfo.ui.screens.country_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enoch02.countryinfo.R
import com.enoch02.countryinfo.navigation.CountryInfoDestination
import com.enoch02.countryinfo.ui.common_views.ErrorMessageView
import com.enoch02.countryinfo.ui.common_views.LoadingView
import com.enoch02.countryinfo.ui.screens.ContentState
import com.enoch02.countryinfo.ui.screens.CountryInfoViewModel
import com.enoch02.countryinfo.ui.screens.country_list.components.CountryListView
import com.enoch02.countryinfo.ui.screens.country_list.components.SearchAndFilterView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListScreen(
    viewModel: CountryInfoViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    var darkMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllCountries(context)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Explore")
                },
                actions = {
                    IconButton(
                        content = {
                            val iconRes = if (darkMode) {
                                R.drawable.baseline_light_mode_24
                            } else {
                                R.drawable.baseline_dark_mode_24
                            }

                            Icon(painter = painterResource(iconRes), contentDescription = null)
                        },
                        onClick = {
                            darkMode = !darkMode
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
                    SearchAndFilterView(modifier = Modifier.weight(0.2f))

                    when (val content = viewModel.contentState) {
                        is ContentState.Loaded -> {
                            CountryListView(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.8f),
                                countries = content.documents.data,
                                onItemClick = { name ->
                                    navController.navigate(
                                        CountryInfoDestination.CountryDetail.withArgs(
                                            name
                                        )
                                    )
                                }
                            )
                        }

                        ContentState.Loading -> {
                            LoadingView(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.8f)
                            )
                        }

                        is ContentState.Error -> {
                            ErrorMessageView(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(0.8f),
                                message = content.message,
                                onRetry = { viewModel.getAllCountries(context) }
                            )
                        }
                    }
                }
            )
        }
    )
}
