package com.example.flickagram.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {


    //https://pixabay.com/api/?key=24928517-d3b688f4a71fabffb54836684&q=yellow+flowers


    @GET("/api/?key=24928517-d3b688f4a71fabffb54836684&q=yellow+flowers")
    fun getImagesList(@Query("q") searchKeyword: String): Call<DataModel>

}
