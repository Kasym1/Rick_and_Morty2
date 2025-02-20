package kg.geeks.rick_and_morty.data.api

import kg.geeks.rick_and_morty.data.dto.CharactersResultResponse
import retrofit2.http.GET

interface CharacterApiService {

    @GET("api/character")
    suspend fun getAllCharacters() : CharactersResultResponse
}