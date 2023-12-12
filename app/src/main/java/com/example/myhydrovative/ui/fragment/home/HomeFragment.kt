package com.example.myhydrovative.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R
import com.example.myhydrovative.ui.adapter.HomeRecyclerViewAdapter
import com.example.myhydrovative.ui.adapter.TanamRecyclerViewAdapter

class HomeFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView // Ini untuk recyclerview item_jenisTanaman
    private lateinit var adapter: HomeRecyclerViewAdapter // Ini untuk mengambild data dari adapter HomeRecyclerViewAdapter
    private lateinit var adapterRecyclerViewAdapter: TanamRecyclerViewAdapter // Ini untuk mengambild data dari adapter TanamRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        // Recycler view untuk list Timeline
        recyclerView = view.findViewById(R.id.recyclerViewTimeline)
        adapterRecyclerViewAdapter = TanamRecyclerViewAdapter()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = adapterRecyclerViewAdapter

        // Recycler view untuk list Jenis Tanaman
        recyclerView = view.findViewById(R.id.recylerViewJenisTanaman)
        adapter = HomeRecyclerViewAdapter()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}