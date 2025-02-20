package kg.geeks.rick_and_morty.data.repository

import kg.geeks.rick_and_morty.data.api.CharacterApiService
import kg.geeks.rick_and_morty.model.CharacterModel
import kg.geeks.rick_and_morty.model.toCharacterModel

class CharactersRepository(private val apiService: CharacterApiService) {

    suspend fun getAllCharacter(): List<CharacterModel> {
        val response = apiService.getAllCharacters()
        return response.charactersResponse.map { it.toCharacterModel() }
    }
}