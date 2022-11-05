package com.example.rick_and_morty_tp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.rick_and_morty_tp3.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //this.setCharacterImage()
        this.setCharacterName()
        this.setCharacterStatus()
        this.setCharacterSpecies()
        this.setCharacterOrigin()

        val fabButton = view.findViewById<FloatingActionButton>(R.id.ch_fab)

        fabButton.setOnClickListener {
            //deberia agregar ID de usuario en sharedPreferences
            Toast.makeText(activity, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
        }

        Picasso
            .get()
            .load(arguments?.getString("imgUrl"))
            .into(view.findViewById<FloatingActionButton>(R.id.ch_Image))
    }

    private fun setCharacterStatus() {
        val tvChName = view?.findViewById<TextView>(R.id.ch_status)
        val value = arguments?.getString("status")
        if (tvChName != null) {
            tvChName.text = value
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
            tvChName.text = "$title $value"
        }
    }

    private fun setCharacterOrigin() {
        val tvChName = view?.findViewById<TextView>(R.id.ch_origen)
        val value = arguments?.getString("origin")
        val title = getString(R.string.ch_detail_orgin)
        if (tvChName != null) {
            tvChName.text = "$title $value"
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