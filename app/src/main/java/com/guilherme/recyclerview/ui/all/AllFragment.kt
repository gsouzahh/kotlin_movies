package com.guilherme.recyclerview.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilherme.recyclerview.adapter.HomeAdapter
import com.guilherme.recyclerview.databinding.FragmentAllBinding
import com.guilherme.recyclerview.repository.MovieRepository

class AllFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding: FragmentAllBinding get() = _binding!!
    private lateinit var allViewModel: AllViewModel
    lateinit var repo: MovieRepository
    private val mAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)

        repo = MovieRepository(requireActivity().applicationContext)

        allViewModel = ViewModelProvider(this).get(AllViewModel::class.java)

        binding.recyclerAll.layoutManager = LinearLayoutManager(context)
        binding.recyclerAll.adapter = mAdapter
        observer()

        allViewModel.retrofit()
        return binding.root
    }

    private fun observer() {
        allViewModel.mLista.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuest(it)
        })
    }
}