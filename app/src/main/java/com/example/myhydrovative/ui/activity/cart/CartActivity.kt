package com.example.myhydrovative.ui.activity.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityCartBinding
import com.example.myhydrovative.ui.activity.hydrivative.HydrovativeActivity

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBackShope.setOnClickListener{
            val moveIntent = Intent(this, HydrovativeActivity::class.java)
            startActivity(moveIntent)
        }
    }
}