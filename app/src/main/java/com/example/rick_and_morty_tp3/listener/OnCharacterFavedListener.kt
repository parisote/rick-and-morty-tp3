package com.example.rick_and_morty_tp3.listener

import com.example.rick_and_morty_tp3.model.CharacterFaved

interface OnCharacterFavedListener {
    fun onCharacterFaved(character: CharacterFaved,characterPosition: Int)
}