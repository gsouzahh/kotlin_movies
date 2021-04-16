package com.guilherme.recyclerview.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.adapter.FavoriteAdapter

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)


        val recycler = root.findViewById<RecyclerView>(R.id.recyclerFavorite)

        favoriteAdapter = FavoriteAdapter(recycler.context)

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = favoriteAdapter

        return root
    }
}