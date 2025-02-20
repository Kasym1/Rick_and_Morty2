package kg.geeks.rick_and_morty.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.geeks.rick_and_morty.data.dto.CharacterResponse

@Entity(tableName = "favorite_characters")
data class CharacterModel(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val gender: String,
    val location: String,
    val image: String,
    val species: String
)

fun CharacterResponse.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        gender = this.gender,
        location = this.location?.name ?: "Unknown",
        image = this.image,
        species = this.species
    )
}