package com.guilherme.recyclerview

import retrofit2.Call
import retrofit2.http.GET

interface GetService {

    @GET("popular?api_key=8526f57af2b4509957af28e34968146c")
    fun list(): Call<PostModel>
}