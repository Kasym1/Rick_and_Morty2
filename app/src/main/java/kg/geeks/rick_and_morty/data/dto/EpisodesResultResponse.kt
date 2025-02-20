package kg.geeks.rick_and_morty.data.dto

import com.google.gson.annotations.SerializedName

data class EpisodesResultResponse(
    @SerializedName("results")
    val episodesResponse: List<EpisodeResponse>
)

data class EpisodeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String
)