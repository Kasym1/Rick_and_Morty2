package kg.geeks.rick_and_morty.ui.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kg.geeks.rick_and_morty.data.repository.EpisodesRepository

class EpisodesViewModel(private val episodesRepository: EpisodesRepository) : ViewModel() {
    val episodes = episodesRepository.getEpisodesPager()
        .flow
        .cachedIn(viewModelScope)
}