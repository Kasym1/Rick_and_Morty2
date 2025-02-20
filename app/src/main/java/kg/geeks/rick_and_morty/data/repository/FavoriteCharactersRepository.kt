package kg.geeks.rick_and_morty.data.repository

import kg.geeks.rick_and_morty.data.db.daos.FavoriteCharacterDao
import kg.geeks.rick_and_morty.model.CharacterModel


class FavoriteCharactersRepository(private val dao: FavoriteCharacterDao) {
    val favoriteCharacters = dao.getAllFavoriteCharacters()

    suspend fun addFavoriteCharacter(character: CharacterModel) {
        dao.addFavoriteCharacter(character)
    }

    suspend fun removeFavoriteCharacter(character: CharacterModel) {
        dao.removeFavoriteCharacter(character)
    }

    suspend fun isFavorite(id: Int): Boolean {
        return dao.getFavoriteCharacterById(id) != null
    }
}