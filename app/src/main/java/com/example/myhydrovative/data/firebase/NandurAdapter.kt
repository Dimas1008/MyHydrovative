package com.example.myhydrovative.data.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ItemJenistanamanBinding
import com.example.myhydrovative.ui.activity.detail.DetailHomeActivity

class NandurAdapter (
    var c: Context, var nandurList:ArrayList<NandurData>
): RecyclerView.Adapter<NandurAdapter.NandurViewHolder>(){
    inner class NandurViewHolder(var v:ItemJenistanamanBinding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NandurViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemJenistanamanBinding>(
            inflter, R.layout.item_jenistanaman,parent,
            false
        )
        return NandurViewHolder(v)
    }

    override fun onBindViewHolder(holder: NandurViewHolder, position: Int) {
        val newList = nandurList[position]
        holder.v.isJenisTanaman = nandurList[position]
        holder.v.root.setOnClickListener {
            val image = newList.image
            val name = newList.name
            val description = newList.description

            // Set Data
            val mIntent = Intent(c, DetailHomeActivity::class.java)
            mIntent.putExtra("image", image)
            mIntent.putExtra("name", name)
            mIntent.putExtra("description", description)
            c.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return  nandurList.size
    }
}