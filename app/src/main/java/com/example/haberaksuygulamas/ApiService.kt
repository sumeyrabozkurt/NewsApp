package com.example.haberaksuygulamas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //@GET("v2/list")
    //fun STRING_CALL(@Query("page") page:Int,@Query("limit") limit:Int) : Call<String>

    //https://newsapi.org/v2/everything?q=besiktas&amp;page=1&amp;apiKey=90f78b5e459f4557a6d285161db89387

    @GET("everything")
    fun STRING_CALL(@Query("q") query:String,@Query("page") page:Int,@Query("limit") limit:Int,@Query("apiKey") apikey:String) : Call<String>

}