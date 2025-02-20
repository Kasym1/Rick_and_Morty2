package kg.geeks.rick_and_morty.ui.screen.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.geeks.rick_and_morty.data.repository.LocationsRepository
import kg.geeks.rick_and_morty.model.LocationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationsViewModel(private val locationsRepository: LocationsRepository) : ViewModel() {
    private val _locations = MutableStateFlow<List<LocationModel>>(emptyList())
    val locations: StateFlow<List<LocationModel>> = _locations.asStateFlow()

    fun fetchAllLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _locations.value = locationsRepository.getAllCharacter()
            } catch (e: Exception) {
                _locations.value = emptyList()
            }
        }
    }
}