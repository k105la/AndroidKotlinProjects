package com.example.xcolordemo.model

import com.example.xcolordemo.model.remote.*
import retrofit2.Call

object ColorModel {

    private val colorService by lazy { RetrofitInstance().colorService }

    @JvmOverloads
    fun getColors(count: Int = 100) : Call<MutableList<XColorModel>> {
        return colorService.getColorHex(count)
    }
}