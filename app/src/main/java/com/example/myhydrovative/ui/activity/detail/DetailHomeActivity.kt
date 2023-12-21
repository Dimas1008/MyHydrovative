package com.example.myhydrovative.ui.activity.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhydrovative.R
import com.example.myhydrovative.data.firebase.getProgessDrawable
import com.example.myhydrovative.data.firebase.loadImage
import com.example.myhydrovative.databinding.ActivityDetailHomeBinding

class DetailHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_detail_home)
        getSupportActionBar()?.hide()

        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**get Data*/
        val tanamanIntent = intent
        val tanamanName = tanamanIntent.getStringExtra("name")
        val tanamanDescription = tanamanIntent.getStringExtra("description")
        val tanamanImage = tanamanIntent.getStringExtra("image")

        binding.tvTanaman.text = tanamanName
        binding.tvDescription.text = tanamanDescription
        binding.imageTanaman.loadImage(tanamanImage, getProgessDrawable(this))

        binding.iconBackTanaman.setOnClickListener {
            onBackPressed()
        }
    }
}