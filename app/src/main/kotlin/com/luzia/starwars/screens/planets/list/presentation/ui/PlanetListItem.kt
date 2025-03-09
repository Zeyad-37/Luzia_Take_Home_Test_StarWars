package com.luzia.starwars.screens.planets.list.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luzia.sharedui.theme.LuziaStarWarsTheme
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM

@Composable
fun PlanetListItem(planet: PlanetPM, onItemClick: () -> Unit = {}) {
    OutlinedCard(
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(top = 4.dp)) {
        with(planet) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = name, Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Climate",
                        Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = climate,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Population",
                        Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = population,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlanetListItemPreview() {
    LuziaStarWarsTheme {
        PlanetListItem(
            PlanetPM(
                name = "Name",
                climate = "climate",
                diameter = "diameter",
                gravity = "gravity",
                terrain = "terrain",
                population = "population"
            )
        )
    }
}