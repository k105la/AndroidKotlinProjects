package com.example.shiboonline.service

import android.graphics.Movie
import okhttp3.internal.cache.DiskLruCache
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

class RetrofitInstance {

    interface apiCall {
        @GET("/api/shibes?count=5")
        suspend fun getShibo() : Call<List<String>>
    }
    private val rf = Retrofit.Builder()
        .baseUrl(ShiboService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val shiboService: ShiboService by lazy { rf.create() }
}