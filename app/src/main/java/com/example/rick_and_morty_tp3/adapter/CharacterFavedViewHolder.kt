package com.example.rick_and_morty_tp3.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.model.CharacterFaved

class CharacterFavedViewHolder(characterView: View) : RecyclerView.ViewHolder(characterView) {
    //private val id : int = characterView.findViewById(R.id.ch_id)
    private val nameText : TextView = characterView.findViewById(R.id.ch_name)
    private val statusText : TextView = characterView.findViewById(R.id.ch_status)
    private val imageView : ImageView = characterView.findViewById(R.id.ch_Image)

    fun bind(ch: CharacterFaved) {
        //set id
        nameText.text = ch.name
        statusText.text = ch.status
        Glide.with(imageView.context).load(ch.imageUrl).override(500,500).into(imageView)
    }
}