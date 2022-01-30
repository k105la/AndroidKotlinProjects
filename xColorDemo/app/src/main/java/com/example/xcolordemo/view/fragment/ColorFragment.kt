package com.example.xcolordemo.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xcolordemo.adapter.ColorAdapter
import com.example.xcolordemo.databinding.FragmentColorBinding
import com.example.xcolordemo.model.ColorModel
import com.example.xcolordemo.viewmodel.ColorViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import retrofit2.await


class ColorFragment : Fragment() {
    private lateinit var colorBinding: FragmentColorBinding

    //private val adapter by lazy { ColorAdapter() }
    private val colorViewModel by viewModels<ColorViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        colorBinding = FragmentColorBinding.inflate(inflater, container, false)
        handleSuccess()
        initObservers()
        return colorBinding.root
    }


    private fun initObservers() = with(colorViewModel) {
        lifecycleScope.launchWhenStarted {
            colorState.collectLatest {
                Snackbar.make(
                    colorBinding.root,
                    it.toString(),
                    Snackbar.LENGTH_LONG
                ).show()

            }
        }
    }

    private fun handleSuccess() {

        lifecycleScope.launchWhenStarted {
            colorViewModel
                .colorState.collectLatest {
                    val color = ColorModel.getColors().await()
                    val colorList = mutableListOf<String>()
                    for (i in color.indices) {
                        colorList.add(color[i].hex)
                    }

                    colorBinding.recyclerView.layoutManager = GridLayoutManager(activity, 4)
                    colorBinding.recyclerView.adapter = ColorAdapter(colorList)
                }
        }
    }

    private fun handleError(ex: String) {
        Toast.makeText(context, ex, Toast.LENGTH_LONG).show()
    }

}