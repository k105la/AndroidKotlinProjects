package com.example.xcolordemo.util

import com.example.xcolordemo.model.remote.ColorService
import com.example.xcolordemo.model.remote.XColorModel
import retrofit2.Call

sealed class ViewState {
    object Loading : ViewState()
    data class Error(val error: String) : ViewState()
    data class Success(val colors: String): ViewState()
}