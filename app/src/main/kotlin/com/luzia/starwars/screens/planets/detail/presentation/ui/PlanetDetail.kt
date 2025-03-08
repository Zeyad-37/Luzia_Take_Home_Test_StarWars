package com.luzia.starwars.screens.planets.detail.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luzia.starwars.R
import com.luzia.starwars.screens.planets.shared.presentation.model.PlanetPM
import com.luzia.starwars.screens.planets.shared.presentation.ui.PlanetItemTextSlot
import com.luzia.starwars.ui.theme.LuziaStarWarsTheme

@Composable
fun PlanetDetail(modifier: Modifier = Modifier, planet: PlanetPM) {
    var showButton by remember { mutableStateOf(false) }
    Column(modifier.fillMaxSize()) {
//        AsyncImage(
//            modifier = Modifier.fillMaxWidth(),
//            model = planet.image,
//            contentScale = ContentScale.FillWidth,
//            contentDescription = null,
//            clipToBounds = true,
//            onError = { showButton = true },
//            onSuccess = { showButton = false },
//            error = painterResource(R.drawable.ic_launcher_foreground),
//            placeholder = painterResource(R.drawable.ic_launcher_foreground),
//        )
        with(planet) {
            PlanetItemTextSlot(name, climate, population, diameter, gravity, terrain)
        }
        if (showButton) {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
//                    colors = ButtonColors(Purple, White, TaupeGray, OffWhite),
                    shape = AbsoluteCutCornerShape(2.dp)
                ) {
                    Text("Download image")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetDetailPreview(modifier: Modifier = Modifier) {
    LuziaStarWarsTheme {
        PlanetDetail(
            planet = PlanetPM(
                "climate",
                "diameter",
                "gravity",
                "name",
                "population",
                "terrain",
            )
        )
    }
}
