package com.example.rick_and_morty_tp3.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rick_and_morty_tp3.database.dao.CharacterDao
import com.example.rick_and_morty_tp3.model.CharacterFaved

@Database(
    entities = [CharacterFaved::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}