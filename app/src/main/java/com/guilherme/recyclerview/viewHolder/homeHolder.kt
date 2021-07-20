package com.guilherme.recyclerview.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.R
import com.squareup.picasso.Picasso

class homeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(index: String){
    val txtTitle = itemView.findViewById<TextView>(R.id.idTitle)
        txtTitle.text = index
    }

    fun image(index: String){
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original$index")
            .into(imgPoster)
    }
}