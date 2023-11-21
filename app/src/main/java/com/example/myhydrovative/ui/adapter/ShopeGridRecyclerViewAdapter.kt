package com.example.myhydrovative.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R

class ShopeGridRecyclerViewAdapter : RecyclerView.Adapter<ShopeGridRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shope, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Contoh data statis, Anda perlu mengganti ini dengan logika yang sesuai
        val itemText = "Tanaman ${position + 1}"
        // Set data ke tampilan di dalam ViewHolder
        holder.itemView.findViewById<TextView>(R.id.tvNamaTanaman).text = itemText
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    }

}