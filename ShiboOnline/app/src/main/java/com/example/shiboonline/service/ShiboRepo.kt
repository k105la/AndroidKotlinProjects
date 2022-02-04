package com.example.shiboonline.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ShiboRepo {
    private val shiboService by lazy { RetrofitInstance().shiboService }
    suspend fun getShibe (count: Int = 10) = withContext(Dispatchers.IO) {
        shiboService.getShiboUrls(count)
    }
}