package kg.geeks.rick_and_morty.data.api

import kg.geeks.rick_and_morty.data.dto.EpisodesResultResponse
import retrofit2.http.GET

interface EpisodeApiService {

    @GET("api/episode")
    suspend fun getAllEpisodes(): EpisodesResultResponse
}