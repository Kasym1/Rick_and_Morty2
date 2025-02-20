package kg.geeks.rick_and_morty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geeks.rick_and_morty.data.db.daos.FavoriteCharacterDao
import kg.geeks.rick_and_morty.model.CharacterModel

@Database(entities = [CharacterModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}