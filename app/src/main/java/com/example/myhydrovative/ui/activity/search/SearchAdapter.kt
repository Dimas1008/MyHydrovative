package com.example.myhydrovative.ui.activity.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R
import com.example.myhydrovative.data.firebase.NandurData
import com.example.myhydrovative.databinding.ItemSearchBinding
import com.example.myhydrovative.ui.activity.detail.DetailHomeActivity

class SearchAdapter (
    var c: Context, var nandurList:ArrayList<NandurData>
): RecyclerView.Adapter<SearchAdapter.NandurViewHolder>(){
    inner class NandurViewHolder(var v: ItemSearchBinding): RecyclerView.ViewHolder(v.root){}

    private var originalList: ArrayList<NandurData> = ArrayList(nandurList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NandurViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemSearchBinding>(
            inflter, R.layout.item_search,parent,
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

    fun setData(data: ArrayList<NandurData>) {
        originalList = ArrayList(data)
        notifyDataSetChanged()
    }

    fun filter(query: String?) {
        val filteredList = ArrayList<NandurData>()

        query?.let {
            for (item in originalList) {
                if (item.name?.contains(it, ignoreCase = true) == true) {
                    filteredList.add(item)
                }
            }
        }

        nandurList.clear()
        nandurList.addAll(filteredList)
        notifyDataSetChanged()
    }

}