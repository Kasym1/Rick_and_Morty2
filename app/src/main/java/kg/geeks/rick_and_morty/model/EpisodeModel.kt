package kg.geeks.rick_and_morty.model

import kg.geeks.rick_and_morty.data.dto.EpisodeResponse

data class EpisodeModel(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
)

fun EpisodeResponse.toEpisodeModel(): EpisodeModel {
    return EpisodeModel(
        id = this.id,
        name = this.name,
        airDate = this.airDate,
        episode = this.episode
    )
}