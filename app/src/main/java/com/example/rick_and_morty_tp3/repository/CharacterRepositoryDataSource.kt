package com.example.rick_and_morty_tp3.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty_tp3.model.CharacterList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepositoryDataSource  {
    val api = RetrofitHelper.getInstance().create(CharacterApi::class.java)

    suspend fun getCharacters() :CharacterList {
        return api.getCharacters()
    }

    suspend fun getCharactersByName(name:String) :CharacterList {
        return api.getCharactersByName(name)
    }
}