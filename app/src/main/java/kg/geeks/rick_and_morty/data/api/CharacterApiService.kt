package kg.geeks.rick_and_morty.data.api

import kg.geeks.rick_and_morty.data.dto.CharactersResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("api/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharactersResultResponse
}