package kg.geeks.rick_and_morty.data.paging.episode

import kg.geeks.rick_and_morty.model.EpisodeModel

sealed class EpisodeDetailState {
    object Loading : EpisodeDetailState()
    data class Success(val episode: EpisodeModel) : EpisodeDetailState()
    data class Error(val message: String) : EpisodeDetailState()
}