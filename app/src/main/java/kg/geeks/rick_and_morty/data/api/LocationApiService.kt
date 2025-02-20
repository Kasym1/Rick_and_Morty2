package kg.geeks.rick_and_morty.data.api

import kg.geeks.rick_and_morty.data.dto.LocationsResultResponse
import retrofit2.http.GET

interface LocationApiService {

    @GET("api/location")
    suspend fun getAllLocations(): LocationsResultResponse
}