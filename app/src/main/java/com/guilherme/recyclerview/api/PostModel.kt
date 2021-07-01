package com.guilherme.recyclerview

import com.google.gson.annotations.SerializedName
import com.guilherme.recyclerview.repository.MovieEntity
import java.io.Serializable

data class PostModel(
    @SerializedName("page")
    var id: Int = 0,
    @SerializedName("results")
    var resultads: List<Results> = emptyList()
)

data class Results(
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
    var release_date: String = "",
    var favorited: Int = 0
) : Serializable {
    fun convertToDBMovie(): MovieEntity {
        return MovieEntity(
            title = this.title,
            overview = this.overview,
            release_date = this.release_date,
            poster_path = this.poster_path,
            vote_average = this.vote_average,
            favorited = this.favorited
        )
    }
}