package com.example.myhydrovative.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.myhydrovative.ui.activity.main.MainActivity
import com.example.myhydrovative.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT: Long = 3000  // Digunakan untuk durasi splash activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent) // Intent Digunakan unutk berpindah ke halaman activity lain
            finish()
        }, SPLASH_TIMEOUT)
    }
}