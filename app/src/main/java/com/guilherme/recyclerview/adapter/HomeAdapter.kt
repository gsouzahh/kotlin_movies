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

    private var listCurrent: List<Results> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeHolder {
        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false)
        return homeHolder(layoutItem)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: homeHolder, position: Int) {
        holder.bind(listCurrent[position].title)
        holder.image(listCurrent[position].poster_path)

        holder.itemView.setOnClickListener {
            val currentItem = listCurrent[position]
            val myBundle = Bundle()
            myBundle.putSerializable("movieItem", currentItem)

            Navigation.findNavController(holder.itemView).navigate(R.id.action_AllFragment2_to_DetailsFragment2, myBundle)
        }
    }

    override fun getItemCount(): Int {
        return listCurrent.count()
    }

    fun updateGuest(list: List<Results>) {
        listCurrent = list
        notifyDataSetChanged()
    }
}