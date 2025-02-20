package kg.geeks.rick_and_morty.nav

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    if (currentRoute != null && currentRoute.contains("detail").not()) {
        TopAppBar(
            title = {
                Text(
                    text = when (currentRoute) {
                        "characters" -> "Characters"
                        "locations" -> "Locations"
                        "episodes" -> "Episodes"
                        "favorite_characters" -> "Favorite characters"
                        else -> "App"
                    },
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.DarkGray
            )
        )
    }
}