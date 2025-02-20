package kg.geeks.rick_and_morty.model

import kg.geeks.rick_and_morty.data.dto.LocationResponse

data class LocationModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)

fun LocationResponse.toLocationModel(): LocationModel {
    return LocationModel(
        id = this.id,
        name = this.name,
        type = this.type,
        dimension = this.dimension
    )
}