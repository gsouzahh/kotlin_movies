package com.guilherme.recyclerview.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilherme.recyclerview.PostModel
import com.guilherme.recyclerview.Results

class HomeViewModel : ViewModel() {

    val post = PostModel()
    val list: List<Results>? = post.resultads

    private val mList = MutableLiveData<List<Results>>()
    val mLista: LiveData<List<Results>> = mList

    fun load(){
        mList.value = list
    }
}