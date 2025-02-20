package kg.geeks.rick_and_morty.ui.screen.characters.datail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kg.geeks.rick_and_morty.R
import kg.geeks.rick_and_morty.model.CharacterModel
import kg.geeks.rick_and_morty.ui.screen.fav.FavoriteCharactersViewModel

@Composable
fun CharacterDetailScreen(
    character: CharacterModel,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    favoritesViewModel: FavoriteCharactersViewModel
) {
    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(character.id) {
        favoritesViewModel.isFavorite(character.id) { isFav ->
            isFavorite.value = isFav
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(paddingValues)
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = "${character.name}'s image",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Name: ${character.name}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            color = Color.Black
        )
        Text(
            text = "Status: ${character.status}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            color = Color.Black
        )
        Text(
            text = "Gender: ${character.gender}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            color = Color.Black
        )
        Text(
            text = "Location: ${character.location}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            color = Color.Black
        )
        Text(
            text = "Species: ${character.species}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Back", fontSize = 16.sp)
        }
        IconButton(
            onClick = {
                if (isFavorite.value) {
                    favoritesViewModel.removeFavoriteCharacter(
                        CharacterModel(
                            id = character.id,
                            name = character.name,
                            image = character.image,
                            status = character.status,
                            species = character.species,
                            gender = character.gender,
                            location = character.location
                        )
                    )
                } else {
                    favoritesViewModel.addFavoriteCharacter(
                        CharacterModel(
                            id = character.id,
                            name = character.name,
                            image = character.image,
                            status = character.status,
                            species = character.species,
                            gender = character.gender,
                            location = character.location
                        )
                    )
                }
                isFavorite.value = !isFavorite.value
            }
        ) {
            Icon(
                imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (isFavorite.value) "Remove from favorites" else "Add to favorites"
            )
        }
    }
}