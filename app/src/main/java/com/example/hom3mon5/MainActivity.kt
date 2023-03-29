package com.example.hom3mon5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hom3mon5.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = PixAdapter(arrayListOf())
    var page = 1
    val layoutManager = LinearLayoutManager(this)
    var images = arrayListOf<Hit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()

    }



    private fun initClicker() {
        with(binding){
            rvImage.layoutManager = layoutManager
            rvImage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = adapter.itemCount
                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        ++page
                        moreRequest()

                    }
                }
            })


            btnSearch.setOnClickListener {
                page =1
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        PixaService().api.getImages(picture = edSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        adapter = PixAdapter(response.body()?.hits!!)
                        rvImage.adapter = adapter
                        images = response.body()?.hits!!

                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("dota", t.message.toString())
                }

            })
    }
    fun ActivityMainBinding.moreRequest(){
        PixaService().api.getImages(picture = edSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        val moreImage = response.body()?.hits!!
                        images.addAll(moreImage)
                        adapter = PixAdapter(images)
                        rvImage.adapter = adapter

                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {

                }

            })
    }



}