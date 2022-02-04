package com.example.shiboonline.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shiboonline.adapter.ShiboAdapter
import com.example.shiboonline.databinding.FragmentShiboBinding
import com.example.shiboonline.databinding.ListItemsBinding
import com.example.shiboonline.service.ViewState
import com.example.shiboonline.viewmodel.ShiboViewModel
import com.squareup.picasso.Picasso
import java.lang.Exception

class ShiboFragment : Fragment () {
    private var _binding: FragmentShiboBinding? = null
    private lateinit var adapter: ShiboAdapter
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by lazy {  ShiboViewModel() }
    private lateinit var listItemsBinding: ListItemsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listItemsBinding = ListItemsBinding.inflate(layoutInflater)
        _binding = FragmentShiboBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    fun initObservers() = with(viewModel) {

        dataState.observe(viewLifecycleOwner) { state ->
            binding.rvList.layoutManager = GridLayoutManager(context,2)

            binding.loader.isVisible = state is ViewState.Loading
            if (state is ViewState.Success) {
                handleSuccess(state.urls)
            }
            if (state is ViewState.Error) handleError(state.message)
        }
    }

    private fun handleSuccess(urls: List<String>) {
        binding.rvList.adapter = ShiboAdapter(urls)
        Log.i("tAG",Picasso.get().load(urls.toString()).into(listItemsBinding.imageView).toString())
    }

    private fun handleError(exception: String) {
        Toast.makeText(context, exception, Toast.LENGTH_LONG).show()
        Log.i("ERROR", "Something went wrong")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}