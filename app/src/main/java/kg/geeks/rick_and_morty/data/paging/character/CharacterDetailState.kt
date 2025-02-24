package kg.geeks.rick_and_morty.data.paging.character

import kg.geeks.rick_and_morty.model.CharacterModel

sealed class CharacterDetailState {
    object Loading : CharacterDetailState()
    data class Success(val character: CharacterModel) : CharacterDetailState()
    data class Error(val message: String) : CharacterDetailState()
}