package com.example.encoderdecoder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encoderdecoder.databinding.ItemRowBinding

class RVAdapter(private val entries: ArrayList<Phrase>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entry = entries[position]

        holder.binding.apply {
            tvItem.text = entry.text
            if(!entry.original){tvItem.setTextColor(Color.BLUE)}
        }
    }

    override fun getItemCount() = entries.size

    fun update(){
        notifyDataSetChanged()
    }
}