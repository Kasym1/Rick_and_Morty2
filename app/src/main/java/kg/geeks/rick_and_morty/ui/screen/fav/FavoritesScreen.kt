package kg.geeks.rick_and_morty.ui.screen.fav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.geeks.rick_and_morty.model.CharacterModel
import kg.geeks.rick_and_morty.ui.screen.characters.CharacterItem

@Composable
fun FavoritesScreen(
    paddingValues: PaddingValues,
    onNavigateToDetail: (Int) -> Unit,
    favoritesViewModel: FavoriteCharactersViewModel
) {
    val favoriteCharacters =
        favoritesViewModel.favoriteCharacters.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
    ) {
        items(favoriteCharacters.value) { character ->
            CharacterItem(
                character = CharacterModel(
                    id = character.id,
                    name = character.name,
                    image = character.image,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    location = character.location
                ),
                onItemClick = { onNavigateToDetail(character.id) }
            )
        }
    }
}