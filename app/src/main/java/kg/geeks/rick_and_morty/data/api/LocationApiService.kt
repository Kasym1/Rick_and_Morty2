package kg.geeks.rick_and_morty.data.api

import kg.geeks.rick_and_morty.data.dto.LocationsResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("api/location")
    suspend fun getAllLocations(
        @Query("page") page: Int
    ): LocationsResultResponse
}