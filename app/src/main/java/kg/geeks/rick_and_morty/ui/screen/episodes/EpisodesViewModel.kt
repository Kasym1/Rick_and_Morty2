package kg.geeks.rick_and_morty.ui.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.geeks.rick_and_morty.data.repository.EpisodesRepository
import kg.geeks.rick_and_morty.model.EpisodeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodesViewModel(private val episodesRepository: EpisodesRepository) : ViewModel() {
    private val _episodes = MutableStateFlow<List<EpisodeModel>>(emptyList())
    val episodes: StateFlow<List<EpisodeModel>> = _episodes.asStateFlow()

    fun fetchAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _episodes.value = episodesRepository.getAllEpisodes()
            } catch (e: Exception) {
                _episodes.value = emptyList()
            }
        }
    }
}