package com.example.rick_and_morty_tp3.repository

import android.content.Context
import androidx.room.Room
import com.example.rick_and_morty_tp3.database.AppDatabase
import com.example.rick_and_morty_tp3.database.dao.CharacterDao
import com.example.rick_and_morty_tp3.model.CharacterFaved

class CharacterFavedRepository private constructor(appDatabase: AppDatabase) {

    private val characterDao: CharacterDao = appDatabase.characterDao()

    suspend fun addCharacterFaved(characterFaved: CharacterFaved) {
        if (!isCharacterFaved(characterFaved))
        {
            characterDao.insertCharacterFaved(characterFaved)
        }
        else
        {
            removeCharacterFaved(characterFaved)
        }
    }

    suspend fun isCharacterFaved(characterFaved: CharacterFaved): Boolean
    {
        var characterSearch = characterDao.loadSingle(characterFaved.id)
        return characterSearch != null
    }

    suspend fun removeCharacterFaved(characterFaved: CharacterFaved) {
        characterDao.delete(characterFaved)
    }

    suspend fun getAllCharacterFaved(): MutableList<CharacterFaved> {
        return characterDao.getAll()
    }

    companion object {
        private var characterFavedRepository: CharacterFavedRepository? = null

        fun getInstance(context: Context): CharacterFavedRepository {
            return characterFavedRepository ?: kotlin.run {

                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "character-database"
                ).build()

                val createdCharacterFavRepository = CharacterFavedRepository(db)
                characterFavedRepository = CharacterFavedRepository(db)
                createdCharacterFavRepository
            }
        }
    }
}