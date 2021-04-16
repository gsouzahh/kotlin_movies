package com.guilherme.recyclerview.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.viewHolder.homeHolder

class HomeAdapter() : RecyclerView.Adapter<homeHolder>() {

    private var lista: List<Results> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeHolder {
        val layout_Item = LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)

        return homeHolder(layout_Item)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: homeHolder, position: Int) {
        holder.bind(lista[position].title)
        holder.image(lista[position].poster_path)

        holder.itemView.setOnClickListener {
            val currentItem = lista[position]
            val myBundle = Bundle()
            myBundle.putSerializable("movieItem", currentItem)

            Navigation.findNavController(holder.itemView).navigate(R.id.action_AllFragment2_to_DetailsFragment2, myBundle)
        }
    }

    override fun getItemCount(): Int {
        return lista.count()
    }

    fun updateGuest(list: List<Results>) {
        lista = list
        notifyDataSetChanged()
    }
}