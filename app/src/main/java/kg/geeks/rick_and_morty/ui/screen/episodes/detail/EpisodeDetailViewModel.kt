package kg.geeks.rick_and_morty.ui.screen.episodes.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.geeks.rick_and_morty.data.paging.episode.EpisodeDetailState
import kg.geeks.rick_and_morty.data.repository.EpisodesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeDetailViewModel(private val repository: EpisodesRepository) : ViewModel() {

    private val _state = MutableStateFlow<EpisodeDetailState>(EpisodeDetailState.Loading)
    val state: StateFlow<EpisodeDetailState> = _state

    fun getEpisodeById(episodeId: Int) {
        viewModelScope.launch {
            try {
                _state.value = EpisodeDetailState.Loading

                repository.getEpisodeById(episodeId).collect { episode ->
                    if (episode != null) {
                        _state.value = EpisodeDetailState.Success(episode)
                    } else {
                        _state.value = EpisodeDetailState.Error("Episode not found")
                    }
                }
            } catch (e: Exception) {
                _state.value = EpisodeDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
}