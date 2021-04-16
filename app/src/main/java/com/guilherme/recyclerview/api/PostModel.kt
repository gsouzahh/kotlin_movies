package com.guilherme.recyclerview

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostModel(
    @SerializedName("page")
    var id: Int = 0,
    @SerializedName("results")
    var resultads: List<Results>? = null
)

data class Results  (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("vote_average")
    var vote_average: String,
    @SerializedName("release_date")
    var release_date: String
): Serializable