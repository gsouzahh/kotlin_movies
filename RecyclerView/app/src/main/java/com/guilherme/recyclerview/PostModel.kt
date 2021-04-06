package com.guilherme.recyclerview

import com.google.gson.annotations.SerializedName

class PostModel {
    @SerializedName("page")
    var id: Int = 0

    @SerializedName("results")
    var resultads: List<Results>? = null
}

class Results{
    @SerializedName("title")
    var title: String = ""

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("poster_path")
    var poster_path: String = ""
}