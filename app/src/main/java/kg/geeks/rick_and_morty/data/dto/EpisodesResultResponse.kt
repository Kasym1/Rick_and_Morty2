package kg.geeks.rick_and_morty.data.dto

import com.google.gson.annotations.SerializedName
import kg.geeks.rick_and_morty.data.paging.PagingInfo

data class EpisodesResultResponse(
    @SerializedName("info")
    val pagingInfo: PagingInfo,
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