package com.guilherme.recyclerview.repository

import androidx.room.*

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(movieModel: List<MovieEntity>)

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_table WHERE favorite = 1")
    fun getFavorites(): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE title = :title")
    fun getMovie(title: String): MovieEntity

    @Query("SELECT favorite FROM movie_table WHERE title = :title")
    fun getItemFavorite(title: String): Int
}