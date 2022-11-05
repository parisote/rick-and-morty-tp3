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

class CharacterRepositoryDataSource : ViewModel() {
    val api = RetrofitHelper.getInstance().create(CharacterApi::class.java)

    private val _characters = MutableLiveData<CharacterList>()

    val characters: LiveData<CharacterList> = _characters

    init{
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch{
            try{
                _characters.value = api.getCharacters()
            } catch (e : Exception){
                System.out.print(e)
                e.printStackTrace()
            }
        }
    }
}