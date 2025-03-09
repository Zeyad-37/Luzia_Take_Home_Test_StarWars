package com.luzia.starwars.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.luzia.sharedui.theme.LuziaStarWarsTheme
import com.luzia.starwars.screens.planets.detail.presentation.ui.PlanetDetailScreenStateHolder
import com.luzia.starwars.screens.planets.list.presentation.ui.PlanetsScreenStateHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { LuziaStarWarsTheme { MyApp() } }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PlanetList) {
        composable<PlanetList> { PlanetsScreenStateHolder { navController.navigate(PlanetDetail(it)) } }
        composable<PlanetDetail> { backStackEntry: NavBackStackEntry ->
            PlanetDetailScreenStateHolder(
                planetName = backStackEntry.toRoute<PlanetDetail>().planetId
            ) { navController.popBackStack() }
        }
    }
}
