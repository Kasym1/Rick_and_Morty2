package kg.geeks.rick_and_morty.data.repository

import kg.geeks.rick_and_morty.data.api.LocationApiService
import kg.geeks.rick_and_morty.model.LocationModel
import kg.geeks.rick_and_morty.model.toLocationModel

class LocationsRepository(private val apiService: LocationApiService) {

    suspend fun getAllCharacter(): List<LocationModel> {
        val response = apiService.getAllLocations()
        return response.locationsResponse.map { it.toLocationModel() }
    }
}