package kg.geeks.rick_and_morty.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kg.geeks.rick_and_morty.nav.AppBottomNavigation
import kg.geeks.rick_and_morty.nav.AppNavHost
import kg.geeks.rick_and_morty.nav.AppTopBar
import kg.geeks.rick_and_morty.ui.theme.Rick_and_MortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Rick_and_MortyTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val showBottomBar = currentRoute != null && !currentRoute.contains("detail")

    Scaffold(
        topBar = {
            AppTopBar(navController = navController)
        },
        bottomBar = {
            if (showBottomBar) {
                AppBottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Rick_and_MortyTheme {
        MainApp()
    }
}