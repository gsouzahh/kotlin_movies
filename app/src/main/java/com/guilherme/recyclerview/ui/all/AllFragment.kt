package com.guilherme.recyclerview.ui.all

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.*
import com.guilherme.recyclerview.adapter.HomeAdapter
import com.guilherme.recyclerview.api.GetService
import com.guilherme.recyclerview.api.RetrofitClient
import com.guilherme.recyclerview.repository.MovieDataBase
import com.guilherme.recyclerview.repository.MovieEntity
import com.guilherme.recyclerview.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllFragment : Fragment() {

    private lateinit var allViewModel: AllViewModel
    var homeadapter: HomeAdapter = HomeAdapter()
    lateinit var repo: MovieRepository

    val post = PostModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        repo = MovieRepository(requireActivity().applicationContext)
        var listEntity = mutableListOf<MovieEntity>()


        allViewModel =
            ViewModelProvider(this).get(AllViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        root.findViewById<RecyclerView>(R.id.recyclerAll).setOnClickListener {}

        //regras para a recyclerView
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerAll)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = homeadapter
        observer()

        //regras do retrofit
        val remote = RetrofitClient.createService(GetService::class.java)
        val call: Call<PostModel> = remote.list()
        call.enqueue(object : Callback<PostModel> {
            override fun onResponse(
                call: Call<PostModel>, res: Response<PostModel>
            ) {
                val s = res.body()
                post.resultads = s?.resultads
                allViewModel.list = post.resultads
                allViewModel.load()

                for (data in allViewModel.list!!) {
                    listEntity.add(
                        MovieEntity(
                            title = data.title,
                            overview = data.overview,
                            poster_path = data.poster_path,
                            vote_average = data.vote_average,
                            release_date = data.release_date,
                            favorited = 0
                        )
                    )
                }
                repo.save(listEntity)
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                val s = t.message
            }
        })
        return root
    }

    private fun observer() {
        allViewModel.mLista.observe(viewLifecycleOwner, Observer {
            homeadapter.updateGuest(it)
        })
    }
}