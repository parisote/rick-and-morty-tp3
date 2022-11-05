package com.example.rick_and_morty_tp3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class CharacterFaved
    (
    @PrimaryKey(autoGenerate = true) val id: Int,
     val name: String,
     val status: String,
     val imageUrl: String,
     val especie: String,
     val origen: String,
     @ColumnInfo(name = "faved_date") val favedDate: Date
     )
