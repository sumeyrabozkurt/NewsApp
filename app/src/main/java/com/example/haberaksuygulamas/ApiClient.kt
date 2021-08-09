package com.example.haberaksuygulamas

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {

    //https://newsapi.org/v2/everything?q=besiktas&amp;page=1&amp;apiKey=90f78b5e459f4557a6d285161db89387
    //.baseUrl("https://picsum.photos/")
    fun getApiService(): ApiService {
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        return retrofitBuilder.create(ApiService::class.java)
    }
}