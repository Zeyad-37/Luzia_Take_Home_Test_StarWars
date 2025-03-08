package com.luzia.starwars.screens.planets.list.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.luzia.starwars.R
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.ErrorListState
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.GoToPlanetDetailsEffect
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.InitialListState
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.LoadPlanetListInput
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.PlanetListInput
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.PlanetsState
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.PlanetsViewModel
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.SuccessListState
import com.luzia.starwars.screens.planets.shared.presentation.ui.ErrorScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlanetsScreenStateHolder(viewModel: PlanetsViewModel = hiltViewModel(), onPlanetClick: (String) -> Unit) {
    val snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val planetsState by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is GoToPlanetDetailsEffect -> onPlanetClick(it.planetId)
            }
        }
    }
    PlanetsScreenContent(Modifier, planetsState, snackBarHostState) { viewModel.process(it) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetsScreenContent(
    modifier: Modifier,
    state: PlanetsState,
    snackBarHostState: SnackbarHostState,
    process: (PlanetListInput) -> Unit,
) {
    Scaffold(
        modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                {
                    Text(
                        text = stringResource(R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displayLarge
                    )
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
            { process(LoadPlanetListInput) },
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (state) {
                is InitialListState -> process(LoadPlanetListInput)
                is ErrorListState -> ErrorScreen(state.message)
                is SuccessListState -> PlanetList(state.planets) { process(it) }
            }
        }
    }
}
