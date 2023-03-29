package com.example.hom3mon5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hom3mon5.databinding.ItemImageBinding

class PixAdapter(private var list: java.util.ArrayList<Hit>): RecyclerView.Adapter<PixAdapter.PixaViewHolder>() {

    class PixaViewHolder(private var binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(hit: Hit){
            binding.imageV.load(hit.largeImageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}