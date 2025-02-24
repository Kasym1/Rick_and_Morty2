package kg.geeks.rick_and_morty.ui.screen.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import kg.geeks.rick_and_morty.data.paging.ErrorItem
import kg.geeks.rick_and_morty.data.paging.LoadingItem
import kg.geeks.rick_and_morty.model.CharacterModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersScreen(
    onNavigateToDetail: (Int) -> Unit,
    paddingValues: PaddingValues,
    charactersViewModel: CharactersViewModel = koinViewModel()
) {

    val characters = charactersViewModel.characters.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 10.dp)
    ) {
        items(characters.itemCount) { index ->
            val character = characters[index]
            character?.let {
                CharacterItem(
                    character = it,
                    onItemClick = { onNavigateToDetail(it.id) }
                )
            }
        }
        when (val state = characters.loadState.append) {
            is LoadState.Loading -> item { LoadingItem() }
            is LoadState.Error -> item { ErrorItem(state.error) { characters.retry() } }
            else -> {}
        }
    }
}

@Composable
fun CharacterItem(character: CharacterModel, onItemClick: (CharacterModel) -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable { onItemClick(character) }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = "${character.name}'s image",
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = character.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1
            )
            Text(
                text = character.status,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}