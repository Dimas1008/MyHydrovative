package com.example.myhydrovative.ui.fragment.shope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R
import com.example.myhydrovative.ui.adapter.ShopeGridRecyclerViewAdapter

class ShopeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShopeGridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shope, container, false)

        recyclerView = view.findViewById(R.id.recylerViewShope)
        adapter = ShopeGridRecyclerViewAdapter()

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        return view
    }
}