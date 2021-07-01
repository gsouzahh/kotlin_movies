package com.guilherme.recyclerview.api

import com.guilherme.recyclerview.PostModel
import com.guilherme.recyclerview.repository.MovieEntity
import retrofit2.Call
import retrofit2.http.GET

interface GetService {
    @GET("popular?api_key=8526f57af2b4509957af28e34968146c")
    suspend fun list(): PostModel
}