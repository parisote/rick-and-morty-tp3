package com.example.rick_and_morty_tp3.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.model.CharacterFaved
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.listener.OnCharacterFavedListener

class CharacterFavAdapter(
    private val characterFavedList: MutableList<CharacterFaved>,
    private val onCharacterFavedListener: OnCharacterFavedListener
) :
    RecyclerView.Adapter<CharacterFavedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterFavedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterFavedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterFavedViewHolder, position: Int) {
        //val character = characterList[position]
        //holder.bind(character, position)
        val characterFaved = characterFavedList[position]
        holder.bind(characterFavedList[position])

        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View) {
                val b = Bundle()
                b.putString("imgUrl", characterFaved.imageUrl)
                b.putString("name", characterFaved.name)
                b.putString("status", characterFaved.status)
                b.putString("species", "human")
                b.putString("origin", "earth")
                b.putInt("id", 1)
                v.findNavController().navigate(R.id.action_character_to_characterDetailFragment, b)
            }
        }
        )
    }

    override fun getItemCount(): Int {
        return characterFavedList.size
    }

    fun removeFav(characterFavedPosition: Int) {
        characterFavedList.removeAt(characterFavedPosition)
        notifyItemRemoved(characterFavedPosition)
    }

}