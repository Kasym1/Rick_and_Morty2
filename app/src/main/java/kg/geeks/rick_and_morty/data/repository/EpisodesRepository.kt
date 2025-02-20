package kg.geeks.rick_and_morty.data.repository

import kg.geeks.rick_and_morty.data.api.EpisodeApiService
import kg.geeks.rick_and_morty.model.EpisodeModel
import kg.geeks.rick_and_morty.model.toEpisodeModel


class EpisodesRepository(private val apiService: EpisodeApiService) {

    suspend fun getAllEpisodes(): List<EpisodeModel> {
        val response = apiService.getAllEpisodes()
        return response.episodesResponse.map { it.toEpisodeModel() }
    }
}