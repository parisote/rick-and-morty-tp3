package com.example.rick_and_morty_tp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.adapter.CharacterAdapter
import com.example.rick_and_morty_tp3.model.Character

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = Character("Jorge", "Alive", "https://rickandmortyapi.com/api/character/avatar/21.jpeg")
        val person1 = Character("Pepe", "Alive", "https://rickandmortyapi.com/api/character/avatar/538.jpeg")
        val person2 = Character("Mario", "Dead", "https://rickandmortyapi.com/api/character/avatar/823.jpeg")

        val characters = listOf<Character>(person, person1, person2)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list_character)
        val adapter = CharacterAdapter(characters)

        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FavoritesFragment().apply {
            }
    }
}