package com.example.rick_and_morty_tp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.adapter.CharacterFavAdapter
import com.example.rick_and_morty_tp3.listener.OnCharacterFavedListener
import com.example.rick_and_morty_tp3.model.Character
import com.example.rick_and_morty_tp3.model.CharacterFaved
import com.example.rick_and_morty_tp3.repository.CharacterFavedRepository
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment(),OnCharacterFavedListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterFavAdapter
    private lateinit var characterFavedRepository: CharacterFavedRepository

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list_character)
        context?.let { characterFavedRepository = CharacterFavedRepository.getInstance(it) }
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            adapter = CharacterFavAdapter(characterFavedRepository.getAllCharacterFaved(), this@FavoritesFragment)
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter
        }
    }

    override fun onCharacterFaved(character: CharacterFaved, characterPosition: Int) {
        lifecycleScope.launch {
            characterFavedRepository.removeCharacterFaved(character)
            adapter.removeFav(characterPosition)
        }
    }
    /*
    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FavoritesFragment().apply {
            }
    }*/

}