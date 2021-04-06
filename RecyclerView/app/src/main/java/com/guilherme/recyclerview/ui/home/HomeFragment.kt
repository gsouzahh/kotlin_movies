package com.guilherme.recyclerview.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilherme.recyclerview.*
import com.guilherme.recyclerview.adapter.homeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var homeadapter: homeAdapter = homeAdapter()

    val post = PostModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        //regras para a recyclerView
        val recycler = root.findViewById<RecyclerView>(R.id.recyclerID)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = homeadapter



        //regras do retrofit
        val remote = RetrofitClient.createService(GetService::class.java)
        val call: Call<PostModel> = remote.list()
        val response = call.enqueue(object : Callback<PostModel> {
            override fun onResponse(
                call: Call<PostModel>, res: Response<PostModel>
            ) {
                val s = res.body() //retorno dos dados

                post.resultads = s?.resultads

                observer()

                homeViewModel.load()
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                val s = t.message
            }
        })
        return root
    }

    private fun observer() {
        homeViewModel.mLista.observe(viewLifecycleOwner, Observer {
            homeadapter.updateGuest(it)
        })
    }
}