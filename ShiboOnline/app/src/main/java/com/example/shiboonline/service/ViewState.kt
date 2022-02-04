package com.example.shiboonline.service

// Different states in the application.
sealed class ViewState {
    object Loading : ViewState()
    data class Error(var message: String) : ViewState()
    data class Success(val urls: List<String>): ViewState()
}






