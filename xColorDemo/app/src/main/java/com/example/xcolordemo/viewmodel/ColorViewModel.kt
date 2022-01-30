package com.example.xcolordemo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xcolordemo.model.ColorModel
import com.example.xcolordemo.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.await
import kotlin.Exception

class ColorViewModel : ViewModel() {
    private val _colorState = MutableStateFlow<ViewState>(ViewState.Loading)
    val colorState by lazy { _colorState.asStateFlow() }

    init {
        viewModelScope.launch {
           val state = try {
               val color = ColorModel.getColors().await()

               for(i in 0 until color.size) {
                   Log.i("Colors", color[i].hex)
               }
                ViewState.Success(color.last().hex)
            } catch (ex: Exception) {
               Log.i("Error", ex.message.toString())

               ViewState.Error(ex.message ?: "Something wrong")
            }
            Log.i("state",state.toString())
            _colorState.value = state
        }
    }
}