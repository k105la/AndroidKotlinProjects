package com.example.xcolordemo.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ColorService {
    companion object {
        const val BASE_URL = "https://x-colors.herokuapp.com/"
    }
    @GET("/api/random/blue")
    fun getColorHex(@Query ("number") number : Int) : Call<MutableList<XColorModel>>

}

data class XColorModel(
    val hex: String,
    val rgb: String,
    val hsl: String
)