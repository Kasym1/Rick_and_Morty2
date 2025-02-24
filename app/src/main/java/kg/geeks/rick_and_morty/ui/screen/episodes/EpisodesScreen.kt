package kg.geeks.rick_and_morty.ui.screen.episodes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import kg.geeks.rick_and_morty.data.paging.ErrorItem
import kg.geeks.rick_and_morty.data.paging.LoadingItem
import kg.geeks.rick_and_morty.model.EpisodeModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodesScreen(
    onNavigateToDetail: (Int) -> Unit,
    paddingValues: PaddingValues,
    episodesViewModel: EpisodesViewModel = koinViewModel()
) {
    val episodes = episodesViewModel.episodes.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 10.dp)
    ) {
        items(episodes.itemCount) { index ->
            val episode = episodes[index]
            episode?.let {
                EpisodesItem(
                    episodes = it,
                    onItemClick = { onNavigateToDetail(it.id) }
                )
            }
        }
        when (val state = episodes.loadState.append) {
            is LoadState.Loading -> item { LoadingItem() }
            is LoadState.Error -> item { ErrorItem(state.error) { episodes.retry() } }
            else -> {}
        }
    }
}

@Composable
fun EpisodesItem(episodes: EpisodeModel, onItemClick: (EpisodeModel) -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable { onItemClick(episodes) }
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = episodes.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 1
            )
        }
    }
}