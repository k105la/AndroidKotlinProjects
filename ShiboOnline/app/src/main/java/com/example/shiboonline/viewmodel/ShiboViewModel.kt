package com.example.shiboonline.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiboonline.R
import com.example.shiboonline.databinding.ListItemsBinding
import com.example.shiboonline.service.ShiboRepo
import com.example.shiboonline.service.ViewState
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.Exception

class ShiboViewModel : ViewModel() {
    private val _dataState = MutableLiveData<ViewState>(ViewState.Loading)
    val dataState: LiveData<ViewState> get() = _dataState

    init {
        viewModelScope.launch {

            val state = try {
                val urls = ShiboRepo.getShibe(30)
                ViewState.Success(urls)
            } catch (ex: Exception) {
                ViewState.Error(ex.message ?: "Something went wrong")
            }
            _dataState.value = state
            Log.i("initObs", state.toString())
        }
    }


}