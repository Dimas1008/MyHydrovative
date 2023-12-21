package com.example.myhydrovative.ui.activity.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhydrovative.R
import com.example.myhydrovative.data.firebase.NandurAdapter
import com.example.myhydrovative.data.firebase.NandurData
import com.example.myhydrovative.databinding.ActivitySearchBinding
import com.example.myhydrovative.ui.adapter.TanamRecyclerViewAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var mAdapter: SearchAdapter
    lateinit var FirebaseDataBase: DatabaseReference
    private lateinit var nandurList: ArrayList<NandurData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSupportActionBar()?.hide()

        nandurList = ArrayList()
        mAdapter = SearchAdapter(this, nandurList)

        binding.searchView.requestFocus()

        binding.recyclerViewSearch.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewSearch.adapter = mAdapter

        fetchDataAndSetDataToAdapter()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mAdapter.filter(newText)
                return true
            }
        })
    }

    private fun fetchDataAndSetDataToAdapter() {
        FirebaseDataBase = FirebaseDatabase.getInstance().getReference("Nandur")
        FirebaseDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nandurList.clear() // Bersihkan list sebelum menambahkan data baru
                if (snapshot.exists()) {
                    for (nandurSnapshot in snapshot.children) {
                        val nandur = nandurSnapshot.getValue(NandurData::class.java)
                        nandurList.add(nandur!!)
                    }
                    mAdapter.setData(nandurList) // Set data ke adapter setelah mendapatkan data baru
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@SearchActivity, // Menggunakan konteks activity
                    error.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}