package com.guilherme.recyclerview.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.R
import kotlinx.android.synthetic.main.item_teste.view.*

class homeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(index: String){
    val txtNome = itemView.findViewById<TextView>(R.id.idName)
        txtNome.text = index
    }

}