package com.guilherme.recyclerview.ui.details

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.guilherme.recyclerview.R
import com.guilherme.recyclerview.Results
import com.guilherme.recyclerview.repository.MovieEntity
import com.guilherme.recyclerview.repository.MovieRepository
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private var favorited: Boolean = false
    lateinit var repo: MovieRepository

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        val textTitle = root.findViewById<TextView>(R.id.txtTitle)
        val textOverview = root.findViewById<TextView>(R.id.txtOverview)
        val textVote = root.findViewById<TextView>(R.id.txtClassification)
        val textData = root.findViewById<TextView>(R.id.txtData)
        val image = root.findViewById<ImageView>(R.id.imgDetails)
        val btnFavorite = root.findViewById<ImageButton>(R.id.btnFavorite)
        val topFavorite = root.findViewById<ImageView>(R.id.iconTopFavorite)

        repo = MovieRepository(requireActivity().applicationContext)

        val arguments = arguments?.getSerializable("movieItem") as Results

        Picasso.get()
            .load("https://image.tmdb.org/t/p/original${arguments.poster_path}")
            .into(image)
        textTitle.text = arguments.title
        textOverview.text = arguments.overview
        textVote.text = arguments.vote_average

        convertDataTime(textData, arguments)
        favorited = repo.getItemFavorite(arguments.title) != 0

        setImages(btnFavorite, topFavorite, arguments)

        btnFavorite.setOnClickListener {
            favorited = !favorited
            checkFavorite(btnFavorite, topFavorite, arguments)
        }
        return root
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDataTime(str: TextView, res: Results) {
        val valueData = res.release_date
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(valueData)
        str.text = SimpleDateFormat("dd MMM yyyy").format(date)
    }

    fun checkFavorite(btn: ImageButton, favIcon: ImageView, res: Results) {
        var favorite = repo.getMovie(res.title)

        if (favorited) {
            btn.setImageResource(R.drawable.ic_baseline_star_24)
            favIcon.visibility = View.VISIBLE
            favorite.favorited = 1
        } else {
            favorite.favorited = 0
            btn.setImageResource(R.drawable.ic_star_vazia)
            favIcon.visibility = View.INVISIBLE
        }
        repo.update(favorite)
    }

    fun setImages(btn: ImageButton, favIcon: ImageView, res: Results){
        if (favorited) {
            btn.setImageResource(R.drawable.ic_baseline_star_24)
            favIcon.visibility = View.VISIBLE
        } else {
            btn.setImageResource(R.drawable.ic_star_vazia)
            favIcon.visibility = View.INVISIBLE
        }
    }
}