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
        setContentView(R.layout.activity_detail_home)

        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**get Data*/
        val animalIntent = intent
        val animalName = animalIntent.getStringExtra("name")
        val animaldescription = animalIntent.getStringExtra("description")
        val animalImage = animalIntent.getStringExtra("image")

        binding.tvTanaman.text = animalName
        binding.tvDescription.text = animaldescription
        binding.imageTanaman.loadImage(animalImage, getProgessDrawable(this))

        binding.iconBackTanaman.setOnClickListener {
            onBackPressed()
        }
    }
}