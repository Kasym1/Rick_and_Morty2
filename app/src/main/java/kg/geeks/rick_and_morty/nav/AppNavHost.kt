package kg.geeks.rick_and_morty.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kg.geeks.rick_and_morty.ui.screen.characters.CharactersScreen
import kg.geeks.rick_and_morty.ui.screen.characters.CharactersViewModel
import kg.geeks.rick_and_morty.ui.screen.characters.detail.CharacterDetailScreen
import kg.geeks.rick_and_morty.ui.screen.episodes.EpisodesScreen
import kg.geeks.rick_and_morty.ui.screen.episodes.EpisodesViewModel
import kg.geeks.rick_and_morty.ui.screen.episodes.detail.EpisodeDetailScreen
import kg.geeks.rick_and_morty.ui.screen.episodes.detail.EpisodeDetailViewModel
import kg.geeks.rick_and_morty.ui.screen.fav.FavoriteCharactersViewModel
import kg.geeks.rick_and_morty.ui.screen.fav.FavoritesScreen
import kg.geeks.rick_and_morty.ui.screen.locations.LocationsScreen
import kg.geeks.rick_and_morty.ui.screen.locations.LocationsViewModel
import kg.geeks.rick_and_morty.ui.screen.locations.detail.LocationDetailScreen
import kg.geeks.rick_and_morty.ui.screen.locations.detail.LocationDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    val charactersViewModel: CharactersViewModel = koinViewModel()
    val locationsViewModel: LocationsViewModel = koinViewModel()
    val episodesViewModel: EpisodesViewModel = koinViewModel()
    val favoriteCharactersViewModel: FavoriteCharactersViewModel = koinViewModel()
    val episodeDetailViewModel: EpisodeDetailViewModel = koinViewModel()
    val locationDetailViewModel: LocationDetailViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = "characters") {
        composable("characters") {
            CharactersScreen(
                onNavigateToDetail = { characterId ->
                    navController.navigate("character_detail/$characterId")
                },
                paddingValues = paddingValues,
                charactersViewModel = charactersViewModel
            )
        }

        composable("locations") {
            LocationsScreen(
                onNavigateToDetail = { locationId ->
                    navController.navigate("location_detail/$locationId")
                },
                paddingValues = paddingValues,
                locationsViewModel = locationsViewModel
            )
        }

        composable("episodes") {
            EpisodesScreen(
                onNavigateToDetail = { episodesId ->
                    navController.navigate("episode_detail/$episodesId")
                },
                paddingValues = paddingValues,
                episodesViewModel = episodesViewModel
            )
        }

        composable("favorite_characters") {
            FavoritesScreen(
                onNavigateToDetail = { favId ->
                    navController.navigate("episode_detail/$favId")
                },
                paddingValues = paddingValues,
                favoritesViewModel = favoriteCharactersViewModel
            )
        }

        composable("character_detail/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()

            if (characterId != null) {
                CharacterDetailScreen(
                    characterId = characterId,
                    paddingValues = paddingValues,
                    onBackClick = { navController.popBackStack() },
                    favoritesViewModel = favoriteCharactersViewModel
                )
            } else {
                Text("Invalid character ID")
            }
        }

        composable("location_detail/{locationId}") { backStackEntry ->
            val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull()

            if (locationId != null) {
                LocationDetailScreen(
                    locationId = locationId,
                    paddingValues = paddingValues,
                    onBackClick = { navController.popBackStack() })
            } else {
                Text("Invalid location ID")
            }
        }

        composable("episode_detail/{episodeId}") { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getString("episodeId")?.toIntOrNull()

            if (episodeId != null) {
                EpisodeDetailScreen(
                    episodeId = episodeId,
                    paddingValues = paddingValues,
                    onBackClick = { navController.popBackStack() }
                )
            } else {
                Text("Invalid episode ID")
            }
        }

        composable("favorite_characters/{favId}") {
            FavoritesScreen(
                paddingValues = paddingValues,
                onNavigateToDetail = { favId ->
                    navController.navigate("favorite_characters/$favId")
                },
                favoritesViewModel = koinViewModel()
            )
        }
    }
}
