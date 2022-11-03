package com.example.rick_and_morty_tp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.fragments.FavoritesFragment
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
                v.findNavController().navigate(R.id.action_homeFragment_to_characterDetailFragment)
            }
        }
        )
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}