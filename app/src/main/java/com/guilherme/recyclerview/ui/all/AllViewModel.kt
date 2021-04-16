package com.guilherme.recyclerview.ui.all

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.repository.MovieDataBase

class AllViewModel(application: Application) : AndroidViewModel(application) {

    var list: List<Results>? = null

    private val mList = MutableLiveData<List<Results>>()
    val mLista: LiveData<List<Results>> = mList

    fun load() = mList.postValue(list)


}