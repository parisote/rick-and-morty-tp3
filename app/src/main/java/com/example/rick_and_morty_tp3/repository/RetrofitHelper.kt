package com.example.rick_and_morty_tp3.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper{

    val baseUrl = "https://rickandmortyapi.com/api/"

    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}