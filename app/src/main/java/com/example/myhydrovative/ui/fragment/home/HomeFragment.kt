package com.example.myhydrovative.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhydrovative.R
import com.example.myhydrovative.data.firebase.NandurAdapter
import com.example.myhydrovative.data.firebase.NandurData
import com.example.myhydrovative.databinding.FragmentHomeBinding
import com.example.myhydrovative.ui.adapter.HomeRecyclerViewAdapter
import com.example.myhydrovative.ui.adapter.TanamRecyclerViewAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var FirebaseDataBase: DatabaseReference
    private lateinit var nandurList: ArrayList<NandurData>
    private lateinit var mAdapter: NandurAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Recycler view untuk list Timeline
        val adapterRecyclerViewAdapter = TanamRecyclerViewAdapter()
        binding.recyclerViewTimeline.setHasFixedSize(true)
        binding.recyclerViewTimeline.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.recyclerViewTimeline.adapter = adapterRecyclerViewAdapter

        // Recycler tanaman dari firebase
        nandurList = ArrayList()
        mAdapter = NandurAdapter(requireContext(), nandurList)
        binding.recylerViewJenisTanaman.setHasFixedSize(true)
        binding.recylerViewJenisTanaman.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.recylerViewJenisTanaman.adapter = mAdapter

        getNandurData()

        return binding.root
    }

    private fun getNandurData() {
        FirebaseDataBase = FirebaseDatabase.getInstance().getReference("Nandur")
        FirebaseDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (nandurSnapshot in snapshot.children) {
                        val nandur = nandurSnapshot.getValue(NandurData::class.java)
                        nandurList.add(nandur!!)
                    }
                    binding.recylerViewJenisTanaman.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
