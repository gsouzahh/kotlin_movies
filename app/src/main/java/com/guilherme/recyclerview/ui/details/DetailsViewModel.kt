package com.guilherme.recyclerview.ui.details

import android.app.Application
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.*
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.repository.MovieEntity
import com.guilherme.recyclerview.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var repo = MovieRepository(application)

    private val itemFavorite = MutableLiveData<Int>()
    val mItemFavorite: LiveData<Int> = itemFavorite

    fun setItemFavorite(string: String){
        viewModelScope.launch {
            itemFavorite.postValue(repo.getItemFavorite(string))
        }
    }

    fun checkFavorite(favorited: Boolean, btn: ImageButton, favIcon: ImageView, res: Results) {
        viewModelScope.launch {
            val favorite = repo.getMovie(res.title)
            if (favorited) {
                btn.setImageResource(R.drawable.ic_baseline_star_24)
                favIcon.visibility = View.VISIBLE
                favorite.favorited = 1
            } else {
                favorite.favorited = 0
                btn.setImageResource(R.drawable.ic_star_vazia)
                favIcon.visibility = View.INVISIBLE
            }
            repo.update(favorite)
        }
    }
}