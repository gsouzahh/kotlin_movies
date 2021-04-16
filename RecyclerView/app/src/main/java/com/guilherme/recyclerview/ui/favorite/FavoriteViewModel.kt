package com.guilherme.recyclerview.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "Nenhum filme favoritado"
    }
    val text: LiveData<String> = _text
}