package com.guilherme.recyclerview.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.guilherme.recyclerview.repository.MovieEntity
import com.guilherme.recyclerview.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = MovieRepository(application)

    private val listFavorite = MutableLiveData<List<MovieEntity>>()
    val mListFavorite: LiveData<List<MovieEntity>> = listFavorite

    fun require(){
        viewModelScope.launch(Dispatchers.IO) {
            listFavorite.postValue(repo.getFavorites())
        }
    }
}