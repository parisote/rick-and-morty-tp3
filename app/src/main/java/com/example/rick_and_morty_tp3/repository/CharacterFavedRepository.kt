package com.example.rick_and_morty_tp3.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rick_and_morty_tp3.database.AppDatabase
import com.example.rick_and_morty_tp3.database.dao.CharacterDao
import com.example.rick_and_morty_tp3.model.CharacterFaved

class CharacterFavedRepository private constructor(appDatabase: AppDatabase) {

    private val characterDao: CharacterDao = appDatabase.characterDao()

    suspend fun addCharacterFaved(characterFaved: CharacterFaved): Boolean {
        if (!isCharacterFaved(characterFaved))
        {
            Log.i("ERROR", "ENTRO EN insert")
            characterDao.insertCharacterFaved(characterFaved)
            return true
        }
        else
        {
            Log.i("ERROR", "ENTRO EN DELETE")
            removeCharacterFaved(characterFaved)
            return false
        }
    }

    suspend fun isCharacterFaved(characterFaved: CharacterFaved): Boolean
    {
        Log.i("ERROR", "BUSCAMOS ID: " + characterFaved.id.toString())
        val characterSearch = characterDao.loadSingle(characterFaved.id)
        return characterSearch != null
    }

    suspend fun isCharacterFavedById(chId: Int): CharacterFaved
    {
        val characterSearch = characterDao.loadSingle(chId)
        return characterSearch
    }

    suspend fun removeCharacterFaved(characterFaved: CharacterFaved) {
        characterDao.delete(characterFaved.id)
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