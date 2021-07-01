package com.guilherme.recyclerview.repository

import androidx.room.*

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(movieModel: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_table WHERE favorite = 1")
    suspend fun getFavorites(): List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE title = :title")
    suspend fun getMovie(title: String): MovieEntity

    @Query("SELECT favorite FROM movie_table WHERE title = :title")
    suspend fun getItemFavorite(title: String): Int
}