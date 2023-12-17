package com.example.myhydrovative.ui.activity.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityWelcomeBinding
import com.example.myhydrovative.ui.activity.login.LoginActivity
import com.example.myhydrovative.ui.activity.sigin.SiginActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
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