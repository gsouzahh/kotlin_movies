package com.guilherme.recyclerview.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.guilherme.recyclerview.Results
import java.io.Serializable

@Entity(tableName = "movie_table", indices = arrayOf(Index(value = ["title"], unique = true)))
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @ColumnInfo(name = "vote_average")
    var vote_average: String,

    @ColumnInfo(name = "release_date")
    var release_date: String? = "",

    @ColumnInfo(name = "favorite")
    var favorited: Int

) : Serializable {
    fun convertToDBMovie(): Results {
        return Results(
            title = this.title,
            overview = this.overview,
            release_date = this.release_date ?: "",
            poster_path = this.poster_path,
            vote_average = this.vote_average,
        )
    }
}