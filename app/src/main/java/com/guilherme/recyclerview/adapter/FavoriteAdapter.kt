package com.guilherme.recyclerview.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.repository.MovieEntity
import com.guilherme.recyclerview.repository.MovieRepository
import com.guilherme.recyclerview.viewHolder.favoriteHolder

class FavoriteAdapter(context: Context) : RecyclerView.Adapter<favoriteHolder>() {

    private var lista: List<MovieEntity> = arrayListOf()
    var repository = MovieRepository(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriteHolder {
        val layoutItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_list_item_favorite, parent, false)

        return favoriteHolder(layoutItem)
    }

    override fun onBindViewHolder(holder: favoriteHolder, position: Int) {
        holder.bind(lista[position].title)
        holder.image(lista[position].poster_path)

        holder.itemView.setOnClickListener {
            val currentItem = lista[position]
            val myBundle = Bundle()

            myBundle.putSerializable("movieItem", currentItem.convertToDBMovie())

            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_favoritesFragment_to_detailsFragment2, myBundle)
        }
    }

    override fun getItemCount(): Int {
        return lista.count()
    }

    fun getList(list: List<MovieEntity>){
        lista = list
        notifyDataSetChanged()
    }
}