package com.example.hom3mon5

import retrofit2.http.GET
import retrofit2.http.Query

interface PixApi {
    @GET("/api/")
    fun getImages(
        @Query("key")key: String = "34848261-199b88616e245a76182fcecdf",
        @Query ("q")picture:String,
        @Query("per_page") perPage: Int = 15,
        @Query("page") page: Int,)
            :retrofit2.Call<PixaModel>

}