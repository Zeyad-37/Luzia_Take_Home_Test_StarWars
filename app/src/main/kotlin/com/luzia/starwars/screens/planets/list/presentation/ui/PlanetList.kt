package com.luzia.starwars.screens.planets.list.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luzia.sharedui.theme.LuziaStarWarsTheme
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.PlanetClickedInput
import com.luzia.starwars.screens.planets.list.presentation.viewmodel.PlanetListInput
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM

@Composable
fun PlanetList(
    list: List<PlanetPM>,
    listState: LazyListState = rememberLazyListState(),
    process: (PlanetListInput) -> Unit,
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
        listState
    ) {
        items(list, { item -> item.name }) { planet ->
            PlanetListItem(planet) { process(PlanetClickedInput(planet.name)) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlanetListPreview() {
    LuziaStarWarsTheme {
        PlanetList(
            listOf(
                PlanetPM(
                    "climate",
                    "diameter",
                    "gravity",
                    "name",
                    "population",
                    "terrain",
                ),
                PlanetPM(
                    "climate",
                    "diameter",
                    "gravity",
                    "name",
                    "population",
                    "terrain",
                )
            ), rememberLazyListState(), {}
        )
    }
}
