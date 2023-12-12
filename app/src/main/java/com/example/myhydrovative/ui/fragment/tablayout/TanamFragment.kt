package com.example.myhydrovative.ui.fragment.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R

class TanamFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView // Ini untuk recyclerview
    private lateinit var adapter: TanamAdapter // Ini untuk mengambild data dari adapter PanenAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tanam, container, false)

        // Recycler view untuk list Timeline
        recyclerView = view.findViewById(R.id.recyclerViewTanaman)
        adapter = TanamAdapter()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}