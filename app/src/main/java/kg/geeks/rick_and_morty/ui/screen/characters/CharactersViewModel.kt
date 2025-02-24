package kg.geeks.rick_and_morty.ui.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kg.geeks.rick_and_morty.data.repository.CharactersRepository

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {
    val characters = charactersRepository.getCharactersPager()
        .flow
        .cachedIn(viewModelScope)
}