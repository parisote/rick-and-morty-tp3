package com.example.rick_and_morty_tp3.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.model.Character

class CharacterAdapter(private val characterList: List<Character>) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList.get(position)
        holder.bind(characterList[position])
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View) {
                val b = Bundle()
                b.putString("imgUrl", character.imageUrl)
                b.putString("name", character.name)
                b.putString("status", character.status)
                b.putString("species", "human")
                b.putString("origin", "earth")
                b.putInt("id", 1)
                v.findNavController().navigate(R.id.action_character_to_characterDetailFragment, b)
            }
        }
        )
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}