package com.example.xcolordemo.model.remote

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ColorService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val colorService: ColorService by lazy { retrofit.create() }

}