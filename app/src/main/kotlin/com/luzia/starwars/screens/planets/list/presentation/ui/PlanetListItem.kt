package com.luzia.starwars.screens.planets.list.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM
import com.luzia.starwars.screens.planets.shared.presentation.ui.PlanetItemTextSlot
import com.luzia.starwars.ui.theme.LuziaStarWarsTheme

@Composable
fun PlanetListItem(
    planet: PlanetPM,
    onItemClick: () -> Unit = {},
) {
    Row(modifier = Modifier.clickable { onItemClick() }) {
        Column {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Status"
            )
        }
        with(planet) {
            PlanetItemTextSlot(name, climate, population, diameter, gravity, terrain)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlanetListItemPreview() {
    LuziaStarWarsTheme {
        PlanetListItem(
            PlanetPM(
                name = "name",
                climate = "climate",
                diameter = "diameter",
                gravity = "gravity",
                terrain = "terrain",
                population = "population"
            )
        )
    }
}