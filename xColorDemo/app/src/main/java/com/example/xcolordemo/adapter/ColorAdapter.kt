package com.example.xcolordemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xcolordemo.databinding.ColorItemBinding

class ColorAdapter(private var colors: List<String> = mutableListOf()) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    // Define click listener for the ViewHolder's View.

    class ColorViewHolder(binding: ColorItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {


            fun newInstance(parent: ViewGroup) = ColorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ).let { ColorViewHolder(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ColorViewHolder.newInstance(parent)


    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position]))
    }

    override fun getItemCount() = colors.size


}