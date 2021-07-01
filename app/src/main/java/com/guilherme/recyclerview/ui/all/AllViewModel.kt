package com.guilherme.recyclerview.ui.all

import android.app.Application
import androidx.lifecycle.*
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.repository.MovieRepository
import kotlinx.coroutines.launch

class AllViewModel(application: Application) : AndroidViewModel(application) {

    var repo: MovieRepository = MovieRepository(application)

    private val mList = MutableLiveData<List<Results>>()
    val mLista: LiveData<List<Results>> = mList

    fun retrofit() {
        viewModelScope.launch {
            val filmes = repo.getListaFilmes()
            mList.postValue(filmes.resultads)
            repo.save(filmes.resultads.map {
                it.convertToDBMovie()
            })
        }
    }
}