package com.example.rick_and_morty_tp3.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rick_and_morty_tp3.model.CharacterFaved
import com.example.rick_and_morty_tp3.model.UserSession

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertCharacterFaved(character: CharacterFaved)

    @Delete
    suspend fun delete(character: CharacterFaved)

    @Query("SELECT * FROM characterFaved where userName=:userName ")
    suspend fun getAll(userName: String = UserSession.username.toString()): MutableList<CharacterFaved>

    @Query("SELECT * FROM CharacterFaved WHERE id=:id  and  userName=:userName")
    suspend fun loadSingle(id: Int, userName:String = UserSession.username.toString()): CharacterFaved
}