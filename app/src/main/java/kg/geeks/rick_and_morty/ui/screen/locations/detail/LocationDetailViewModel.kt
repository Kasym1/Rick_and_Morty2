package kg.geeks.rick_and_morty.ui.screen.locations.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.geeks.rick_and_morty.data.paging.location.LocationDetailState
import kg.geeks.rick_and_morty.data.repository.LocationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationDetailViewModel(private val repository: LocationsRepository) : ViewModel() {

    private val _state = MutableStateFlow<LocationDetailState>(LocationDetailState.Loading)
    val state: StateFlow<LocationDetailState> = _state

    fun getLocationById(locationId: Int) {
        viewModelScope.launch {
            try {
                _state.value = LocationDetailState.Loading

                repository.getLocationById(locationId).collect { location ->
                    if (location != null) {
                        _state.value = LocationDetailState.Success(location)
                    } else {
                        _state.value = LocationDetailState.Error("Location not found")
                    }
                }
            } catch (e: Exception) {
                _state.value = LocationDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
}