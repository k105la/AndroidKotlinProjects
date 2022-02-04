package com.example.shiboonline.service

import android.graphics.Movie
import retrofit2.http.GET
import retrofit2.http.Query


interface ShiboService {

    @GET("/api/shibes")
    suspend fun getShiboUrls(
        @Query ("count") count: Int
    ) : List<String>

    companion object {
        const val  BASE_URL = "https://shibe.online/"
    }

}