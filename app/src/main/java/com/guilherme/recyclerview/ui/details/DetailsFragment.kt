package com.guilherme.recyclerview.ui.details

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.databinding.FragmentDetailsBinding
import com.guilherme.recyclerview.repository.MovieRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private var favorited: Boolean = false
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val arguments = arguments?.getSerializable("movieItem") as Results

        detailsViewModel.setItemFavorite(arguments.title)

        Picasso.get()
            .load("https://image.tmdb.org/t/p/original${arguments.poster_path}")
            .into(binding.imgDetails)

        binding.txtTitle.text = arguments.title
        binding.txtOverview.text = arguments.overview
        binding.txtClassification.text = arguments.vote_average
        binding.btnFavorite.setOnClickListener {
            favorited = !favorited
            detailsViewModel.checkFavorite(favorited, binding.btnFavorite, binding.iconTopFavorite, arguments)
        }

        convertDataTime(binding.txtData, arguments)

        observer()

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDataTime(str: TextView, res: Results) {
        val valueData = res.release_date
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(valueData)!!
        str.text = SimpleDateFormat("dd MMM yyyy").format(date)
    }

    private fun observer(){
        detailsViewModel.mItemFavorite.observe(viewLifecycleOwner, Observer{
            favorited = it != 0
            if (favorited) {
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_star_24)
                binding.iconTopFavorite.visibility = View.VISIBLE
            } else {
                binding.btnFavorite.setImageResource(R.drawable.ic_star_vazia)
                binding.iconTopFavorite.visibility = View.INVISIBLE
            }
        })
    }
}