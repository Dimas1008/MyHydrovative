package com.example.myhydrovative.ui.activity.sigin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivitySiginBinding
import com.example.myhydrovative.ui.activity.login.LoginActivity

class SiginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin)

        binding = ActivitySiginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLoginSig.setOnClickListener {
            val moveIntent = Intent(this, LoginActivity::class.java)
            startActivity(moveIntent)
            finish()
        }
    }
}