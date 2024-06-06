package com.example.musicapp1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

//    @Headers("X-RapidAPI-Key: 075a6492d4msh91d1a7416c10f43p197140jsn8437478b79d0" +
//        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
@Headers(
    "X-RapidAPI-Key: YOUR_RAPIDAPI_KEY"
)
    @GET("search")
    fun getData(@Query("q") query: String): Call<Mydata>

}