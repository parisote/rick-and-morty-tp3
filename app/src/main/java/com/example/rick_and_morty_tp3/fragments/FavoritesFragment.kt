package com.example.rick_and_morty_tp3.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.adapter.CharacterFavAdapter
import com.example.rick_and_morty_tp3.listener.OnCharacterFavedListener
import com.example.rick_and_morty_tp3.model.Character
import com.example.rick_and_morty_tp3.model.CharacterFaved
import com.example.rick_and_morty_tp3.model.UserSession
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
        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val isDarkMode = preferences.getBoolean("dark_mode", false)
        val descr : TextView = view.findViewById(R.id.textView)
        val color = if (isDarkMode) R.color.white else R.color.black
        descr.setTextColor(ContextCompat.getColor(descr.context, color))

        context?.let { characterFavedRepository = CharacterFavedRepository.getInstance(it) }
    }


    @SuppressLint("StringFormatMatches")
    override fun onStart() {
        super.onStart()
        val fav_text: String = resources.getString(R.string.favorite_text, UserSession.username)
        var text_view = view?.findViewById<TextView>(R.id.textView)
        if (text_view != null) {
            text_view.text = fav_text
        }
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