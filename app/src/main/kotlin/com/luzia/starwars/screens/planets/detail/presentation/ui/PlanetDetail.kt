package com.luzia.starwars.screens.planets.detail.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM
import com.luzia.starwars.ui.theme.LuziaStarWarsTheme

@Composable
fun PlanetPM.PlanetDetail() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val rowModifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        Row(rowModifier) {
            Text(
                text = "Climate",
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = climate,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
        Row(rowModifier) {
            Text(
                text = "Population",
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = population,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
        Row(rowModifier) {
            Text(
                text = "Diameter",
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = diameter,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
        Row(rowModifier) {
            Text(
                text = "Gravity",
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = gravity,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
        Row(rowModifier) {
            Text(
                text = "Terrain",
                Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = terrain,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlanetDetailPreview(modifier: Modifier = Modifier) {
    LuziaStarWarsTheme {
        PlanetPM(
            "climate",
            "diameter",
            "gravity",
            "Name",
            "population",
            "terrain",
        ).PlanetDetail()
    }
}
