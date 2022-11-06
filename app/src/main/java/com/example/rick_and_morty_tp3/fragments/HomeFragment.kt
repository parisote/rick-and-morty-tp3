package com.example.rick_and_morty_tp3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.rick_and_morty_tp3.R
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morty_tp3.adapter.CharacterAdapter
import com.example.rick_and_morty_tp3.model.UserSession
import com.example.rick_and_morty_tp3.repository.CharacterRepositoryDataSource
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
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
        this.checkSearchViewEnable(view.findViewById(R.id.searchView2))
        recycler = view.findViewById<RecyclerView>(R.id.list_character)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            recycler.layoutManager = GridLayoutManager(context, 2)
            adapter = CharacterRepositoryDataSource().getCharacters()?.results?.let { CharacterAdapter(it) }
            recycler.adapter = adapter
            adapter?.notifyDataSetChanged()
        }
    }

    private fun checkSearchViewEnable(searchView: SearchView) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val isSearchViewEnable = preferences.getBoolean("enable_searchbar", false)
        searchView.isVisible = isSearchViewEnable
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