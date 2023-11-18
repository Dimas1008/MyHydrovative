package com.example.myhydrovative.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityLoginBinding
import com.example.myhydrovative.ui.activity.hydrivative.HydrovativeActivity
import com.example.myhydrovative.ui.activity.sigin.SiginActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ini untuk berpindah ke activity Sigin
        binding.tvSignupLog.setOnClickListener {
            val moveIntent = Intent(this, SiginActivity::class.java)
            startActivity(moveIntent)
            finish()
        }

        binding.btnLoginLog.setOnClickListener {
            val moveIntent = Intent(this, HydrovativeActivity::class.java)
            startActivity(moveIntent)
        }
    }
}