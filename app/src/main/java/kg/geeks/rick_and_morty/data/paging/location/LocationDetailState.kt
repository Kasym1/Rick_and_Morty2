package kg.geeks.rick_and_morty.data.paging.location

import kg.geeks.rick_and_morty.model.LocationModel

sealed class LocationDetailState {
    object Loading : LocationDetailState()
    data class Success(val location: LocationModel) : LocationDetailState()
    data class Error(val message: String) : LocationDetailState()
}