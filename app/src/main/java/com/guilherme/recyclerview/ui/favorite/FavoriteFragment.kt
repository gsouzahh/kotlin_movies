package com.guilherme.recyclerview.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilherme.recyclerview.adapter.FavoriteAdapter
import com.guilherme.recyclerview.databinding.FragmentFavoritesBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        favoriteViewModel.require()

        favoriteAdapter = FavoriteAdapter(binding.recyclerFavorite.context)

        binding.recyclerFavorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerFavorite.adapter = favoriteAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.mListFavorite.observe(viewLifecycleOwner, {
            favoriteAdapter.getList(it)
        })
    }
}