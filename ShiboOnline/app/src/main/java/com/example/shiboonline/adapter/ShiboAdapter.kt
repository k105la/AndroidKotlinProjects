package com.example.shiboonline.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shiboonline.databinding.ListItemsBinding
import com.squareup.picasso.Picasso

class ShiboAdapter (private val urls: List<String>) : RecyclerView.Adapter<ShiboAdapter.ShiboViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShiboViewHolder.newInstance(parent)
    override fun onBindViewHolder(holder: ShiboViewHolder, position: Int) {
        holder.bindUrl(urls[position])

    }

    override fun getItemCount() = urls.size

    class ShiboViewHolder(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindUrl(url: String) {
            // binding.textView.text = url
            Picasso.get().load(url).into(binding.imageView)
        }
        companion object {
            fun newInstance(parent: ViewGroup) = ListItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let {
                ShiboViewHolder(it)
            }
        }
    }
}