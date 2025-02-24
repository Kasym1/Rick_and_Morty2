package kg.geeks.rick_and_morty.ui.screen.characters.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.geeks.rick_and_morty.data.paging.character.CharacterDetailState
import kg.geeks.rick_and_morty.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val repository: CharactersRepository) : ViewModel() {

    private val _state = MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val state: StateFlow<CharacterDetailState> = _state

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            try {
                _state.value = CharacterDetailState.Loading

                repository.getCharacterById(characterId).collect { character ->
                    if (character != null) {
                        _state.value = CharacterDetailState.Success(character)
                    } else {
                        _state.value = CharacterDetailState.Error("Character not found")
                    }
                }
            } catch (e: Exception) {
                _state.value = CharacterDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
}