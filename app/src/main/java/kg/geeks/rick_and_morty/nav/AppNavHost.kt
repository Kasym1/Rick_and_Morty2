package kg.geeks.rick_and_morty.nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kg.geeks.rick_and_morty.ui.screen.characters.CharactersScreen
import kg.geeks.rick_and_morty.ui.screen.characters.CharactersViewModel
import kg.geeks.rick_and_morty.ui.screen.characters.datail.CharacterDetailScreen
import kg.geeks.rick_and_morty.ui.screen.episodes.EpisodesScreen
import kg.geeks.rick_and_morty.ui.screen.episodes.EpisodesViewModel
import kg.geeks.rick_and_morty.ui.screen.episodes.detail.EpisodeDetailScreen
import kg.geeks.rick_and_morty.ui.screen.fav.FavoriteCharactersViewModel
import kg.geeks.rick_and_morty.ui.screen.fav.FavoritesScreen
import kg.geeks.rick_and_morty.ui.screen.locations.LocationsScreen
import kg.geeks.rick_and_morty.ui.screen.locations.LocationsViewModel
import kg.geeks.rick_and_morty.ui.screen.locations.detail.LocationDetailScreen
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    val charactersViewModel: CharactersViewModel = koinViewModel()
    val locationsViewModel: LocationsViewModel = koinViewModel()
    val episodesViewModel: EpisodesViewModel = koinViewModel()
    val favoriteCharactersViewModel: FavoriteCharactersViewModel = koinViewModel()

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
            val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: 0
            val character =
                charactersViewModel.characters.value.firstOrNull { it.id == characterId }
            if (character != null) {
                CharacterDetailScreen(
                    character = character,
                    paddingValues = paddingValues,
                    onBackClick = { navController.popBackStack() },
                    favoritesViewModel = favoriteCharactersViewModel
                )
            }
        }

        composable("location_detail/{locationId}") { backStackEntry ->
            val locationId = backStackEntry.arguments?.getString("locationId")?.toInt() ?: 0
            val location = locationsViewModel.locations.value.firstOrNull { it.id == locationId }
            if (location != null) {
                LocationDetailScreen(
                    location = location,
                    paddingValues = paddingValues,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable("episode_detail/{episodeId}") { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getString("episodeId")?.toInt() ?: 0
            val episode = episodesViewModel.episodes.value.firstOrNull { it.id == episodeId }
            if (episode != null) {
                EpisodeDetailScreen(
                    episode = episode,
                    paddingValues = paddingValues,
                    onBackClick = { navController.popBackStack() }
                )
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