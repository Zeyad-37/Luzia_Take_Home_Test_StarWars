package com.luzia.starwars.screens.planets.presentation.detail.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.BackButtonTappedInput
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.ErrorState
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.GoBackEffect
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.InitialState
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.LoadPlanetInput
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.SuccessState
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.PlanetDetailInput
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.PlanetDetailState
import com.luzia.starwars.screens.planets.presentation.detail.viewmodel.PlanetDetailViewModel
import com.luzia.sharedui.composables.ErrorScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlanetDetailScreenStateHolder(
    viewModel: PlanetDetailViewModel = hiltViewModel(), planetName: String, onBackPressed: () -> Unit,
) {
    val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val planetDetailState by viewModel.state.collectAsState(InitialState(false, planetName))
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                GoBackEffect -> onBackPressed()
            }
        }
    }
    PlanetDetailScreenContent(Modifier, planetDetailState, planetName, snackBarHostState) { viewModel.process(it) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreenContent(
    modifier: Modifier = Modifier,
    state: PlanetDetailState,
    planetName: String,
    snackBarHostState: SnackbarHostState,
    process: (PlanetDetailInput) -> Unit,
) {
    Scaffold(
        modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                {
                    Text(
                        text = planetName,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displaySmall
                    )
                }, navigationIcon = {
                    IconButton(onClick = { process(BackButtonTappedInput) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { Snackbar(it, contentColor = Color.Red) })
        },
    ) { innerPadding ->
        PullToRefreshBox(
            state.isLoading,
            { process(LoadPlanetInput(planetName)) },
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (state) {
                is InitialState -> process(LoadPlanetInput(state.planetId))
                is ErrorState -> ErrorScreen(state.message)
                is SuccessState -> state.planet.PlanetDetail()
            }
        }
    }
}