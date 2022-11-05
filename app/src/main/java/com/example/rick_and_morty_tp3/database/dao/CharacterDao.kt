package com.example.rick_and_morty_tp3.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rick_and_morty_tp3.model.CharacterFaved

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertCharacterFaved(character: CharacterFaved)

    @Delete
    suspend fun delete(character: CharacterFaved)

    @Query("SELECT * FROM characterFaved")
    suspend fun getAll(): MutableList<CharacterFaved>

    @Query("SELECT * FROM CharacterFaved WHERE id=:id ")
    fun loadSingle(id: Int): LiveData<CharacterFaved>
}