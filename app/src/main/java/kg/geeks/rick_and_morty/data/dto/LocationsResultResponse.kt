package kg.geeks.rick_and_morty.data.dto

import com.google.gson.annotations.SerializedName

data class LocationsResultResponse(
    @SerializedName("results")
    val locationsResponse: List<LocationResponse>
)

data class LocationResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String
)