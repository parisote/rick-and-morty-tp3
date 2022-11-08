package com.example.rick_and_morty_tp3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class CharacterFaved
    (
    @PrimaryKey(autoGenerate = true) val uid: Int,
        val id: Int,
        val userName: String,
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
        val origin: String,
        val image: String,
     @ColumnInfo(name = "faved_date") val favedDate: Date
     )
