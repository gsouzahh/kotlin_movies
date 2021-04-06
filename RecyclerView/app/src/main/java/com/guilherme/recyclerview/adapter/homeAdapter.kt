package com.guilherme.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.viewHolder.homeHolder

class homeAdapter : RecyclerView.Adapter<homeHolder>() {

    private var lista: List<Results> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_teste, parent, false)
        return homeHolder(item)
    }

    override fun onBindViewHolder(holder: homeHolder, position: Int) {
        holder.bind(lista[position].title)
    }

    override fun getItemCount(): Int {
        return lista.count()
    }

    fun updateGuest(list: List<Results>){
        lista = list
        notifyDataSetChanged()
    }
}