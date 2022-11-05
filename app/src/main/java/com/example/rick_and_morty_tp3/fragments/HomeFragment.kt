package com.example.rick_and_morty_tp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rick_and_morty_tp3.R
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.adapter.CharacterAdapter
import com.example.rick_and_morty_tp3.model.Character
import com.example.rick_and_morty_tp3.model.CharacterList
import com.example.rick_and_morty_tp3.model.Origin
import com.example.rick_and_morty_tp3.repository.CharacterApi
import com.example.rick_and_morty_tp3.repository.CharacterRepositoryDataSource
import com.example.rick_and_morty_tp3.repository.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "password"
private const val ARG_PARAM2 = "username"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var title: TextView

    private var adapter: CharacterAdapter? = null
    private var list: List<Character>? = ArrayList<Character>()

    private lateinit var recycler: RecyclerView

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // title = view.findViewById(R.id.txtHello)
        // Pongo el nombre del usuario en el titulo.
        // Advertencia: Al momento de mostrar un texto al usuario siempre usar un String resource. Nunca hardcodear de
        // esta manera.
        // title.text = "Hola, ${param1}"

        // lista de personajes hardcodeada
        val person = Character(1,"Jorge", "Alive", "","","", Origin("Earth"),"https://rickandmortyapi.com/api/character/avatar/21.jpeg")
        val person1 = Character(2,"Pepe", "Alive", "","","", Origin("Earth"), "https://rickandmortyapi.com/api/character/avatar/538.jpeg")
        val person2 = Character(3,"Mario", "Dead", "","","", Origin("Earth"), "https://rickandmortyapi.com/api/character/avatar/823.jpeg")

        val characters = listOf<Character>(person, person1, person2, person, person1, person2)

        recycler = view.findViewById<RecyclerView>(R.id.list_character)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            recycler.layoutManager = GridLayoutManager(context, 2)
            adapter = CharacterRepositoryDataSource().getCharacters()?.results?.let { CharacterAdapter(it) }
            recycler.adapter = adapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}