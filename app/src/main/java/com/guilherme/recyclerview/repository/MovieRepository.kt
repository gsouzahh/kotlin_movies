package com.guilherme.recyclerview.repository

import android.content.Context

class MovieRepository (context: Context){
    //acesso ao banco de dados
    private val mDataBase = MovieDataBase.getDataBase(context).movieDAO()

    fun save(movie: List<MovieEntity>){
        return mDataBase.save(movie)
    }

    fun getFavorites():List<MovieEntity>{
        return mDataBase.getFavorites()
    }

    fun update(movie: MovieEntity){
        return mDataBase.updateMovie(movie)
    }

    fun getMovie(title: String): MovieEntity{
        return mDataBase.getMovie(title)
    }

    fun getItemFavorite(title: String): Int{
        return mDataBase.getItemFavorite(title)
    }
}