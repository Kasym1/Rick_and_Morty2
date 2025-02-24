package kg.geeks.rick_and_morty.ui.screen.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kg.geeks.rick_and_morty.data.repository.LocationsRepository

class LocationsViewModel(private val locationsRepository: LocationsRepository) : ViewModel() {
    val locations = locationsRepository.getLocationsPager()
        .flow
        .cachedIn(viewModelScope)
}