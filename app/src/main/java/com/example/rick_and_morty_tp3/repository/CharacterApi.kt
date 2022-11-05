package com.example.rick_and_morty_tp3.repository

import com.example.rick_and_morty_tp3.model.CharacterList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters() : CharacterList

    @GET("character")
    suspend fun getCharactersById() : CharacterList
}