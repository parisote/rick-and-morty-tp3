package com.example.rick_and_morty_tp3.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.rick_and_morty_tp3.R
import com.example.rick_and_morty_tp3.model.CharacterFaved
import com.example.rick_and_morty_tp3.model.UserSession
import com.example.rick_and_morty_tp3.repository.CharacterFavedRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var characterFavedRepository: CharacterFavedRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //this.setCharacterImage()
        this.setCharacterName()
        this.setCharacterStatus()
        this.setCharacterSpecies()
        this.setCharacterOrigin()

        val fabButton = view.findViewById<FloatingActionButton>(R.id.ch_fab)
        this.checkFabEnable(fabButton)

        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val isDarkMode = preferences.getBoolean("dark_mode", false)

        val name : TextView = view.findViewById(R.id.ch_name)
        val species : TextView = view.findViewById(R.id.ch_especie)
        val origin : TextView = view.findViewById(R.id.ch_origen)
        var color = if (isDarkMode) R.color.white else R.color.black

        name.setTextColor(ContextCompat.getColor(name.context, color))
        species.setTextColor(ContextCompat.getColor(species.context, color))
        origin.setTextColor(ContextCompat.getColor(origin.context, color))

        context?.let { characterFavedRepository = CharacterFavedRepository.getInstance(it) }

        lifecycleScope.launch {
            val ch = arguments?.getInt("id")
                ?.let { characterFavedRepository.isCharacterFavedById(it) }

            if (ch != null) {
                fabButton.setImageResource(R.drawable.ic_unfav)
                fabButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.purple_500));
            } else {
                fabButton.setImageResource(R.drawable.ic_fav)
                fabButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.purple_500));
            }
        }

        fabButton.setOnClickListener {
            //deberia agregar ID de usuario en sharedPreferences
            //val characterId = Integer.parseInt(view.findViewById<TextView>(R.id.ch_id).text.toString())
            val characterId = arguments?.getInt("id")
            val characterName = view.findViewById<TextView>(R.id.ch_name).text.toString()
            val characterStatus = view.findViewById<TextView>(R.id.ch_status).text.toString()
            val characterEspecie = view.findViewById<TextView>(R.id.ch_especie).text.toString()
            val characterOrigen = view.findViewById<TextView>(R.id.ch_origen).text.toString()
            val imgUrl = arguments?.getString("imgUrl").toString()
            lifecycleScope.launch {
                if (characterId != null)
                {
                    val newCharacterFaved = CharacterFaved(
                            0,
                            characterId,
                            UserSession.username.toString(),
                            characterName,
                            characterStatus,
                            characterEspecie,
                            "", //characterType
                            "", //characterGender
                            characterOrigen,
                            imgUrl,
                            Date()
                        )
                    try
                    {
                        val added = characterFavedRepository.addCharacterFaved(newCharacterFaved)
                        if (added) {
                            Toast.makeText(activity, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(activity, "Removido de favoritos", Toast.LENGTH_SHORT).show()
                        }
                    }
                    catch (error: Exception)
                    {
                        Log.e("ERROR", "Error guardando nuevo fav" + error.message.toString())
                    }
                }
                else {
                    Log.e("ERROR", "Error guardando nuevo fav - VINO NULL EL ID")
                }
                findNavController().navigate(R.id.favoritesFragment)
            }
        }

        Picasso
            .get()
            .load(arguments?.getString("imgUrl"))
            .into(view.findViewById<FloatingActionButton>(R.id.ch_Image))
    }

    private fun checkFabEnable(fabButton: FloatingActionButton) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val isFabEnable = preferences.getBoolean("enable_fab", false)
        fabButton.isVisible = isFabEnable
    }

    private fun setCharacterStatus() {
        val tvChName = view?.findViewById<TextView>(R.id.ch_status)
        val value = arguments?.getString("status")
        if (tvChName != null) {
            tvChName.text = value
            when (tvChName.text) {
                "Alive" -> tvChName.setTextColor(ContextCompat.getColor(tvChName.context, R.color.green))
                "Dead" -> tvChName.setTextColor(ContextCompat.getColor(tvChName.context, R.color.red))
                "unknown" -> tvChName.setTextColor(ContextCompat.getColor(tvChName.context, R.color.grey))
            }
        }
    }

    private fun setCharacterName() {
        val tvChName = view?.findViewById<TextView>(R.id.ch_name)
        val value = arguments?.getString("name")
        if (tvChName != null) {
            tvChName.text = value
        }
    }

    private fun setCharacterSpecies() {
        val tvChName = view?.findViewById<TextView>(R.id.ch_especie)
        val value = arguments?.getString("species");
        val title = getString(R.string.ch_detail_species)
        if (tvChName != null) {
            tvChName.text = "$value"
        }
    }

    private fun setCharacterOrigin() {
        val tvChName = view?.findViewById<TextView>(R.id.ch_origen)
        val value = arguments?.getString("origin")
        val title = getString(R.string.ch_detail_orgin)
        if (tvChName != null) {
            tvChName.text = "$value"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}