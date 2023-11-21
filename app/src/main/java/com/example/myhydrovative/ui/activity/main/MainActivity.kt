package com.example.myhydrovative.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityMainBinding
import com.example.myhydrovative.ui.activity.login.LoginActivity
import com.example.myhydrovative.ui.activity.sigin.SiginActivity

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSigin.setOnClickListener {
            val moveIntent = Intent(this, SiginActivity::class.java)
            startActivity(moveIntent)
        }

        binding.btnLogin.setOnClickListener {
            val moveIntent = Intent(this, LoginActivity::class.java)
            startActivity(moveIntent)
        }
    }
}