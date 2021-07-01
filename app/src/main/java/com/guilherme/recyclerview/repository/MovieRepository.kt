package com.guilherme.recyclerview.repository

import android.content.Context
import com.guilherme.recyclerview.PostModel
import com.guilherme.recyclerview.api.GetService
import com.guilherme.recyclerview.api.RetrofitClient

class MovieRepository (context: Context){
    //acesso ao banco de dados
    private val mDataBase = MovieDataBase.getDataBase(context).movieDAO()
    private val mRetrofit = RetrofitClient.createService(GetService::class.java)

    suspend fun save(movie: List<MovieEntity>) = mDataBase.save(movie)

    suspend fun getFavorites():List<MovieEntity> = mDataBase.getFavorites()

    suspend fun update(movie: MovieEntity) = mDataBase.updateMovie(movie)

    suspend fun getMovie(title: String): MovieEntity = mDataBase.getMovie(title)

    suspend fun getItemFavorite(title: String): Int = mDataBase.getItemFavorite(title)

    suspend fun getListaFilmes() : PostModel = mRetrofit.list()
}