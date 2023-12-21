package com.example.myhydrovative.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.myhydrovative.R
import com.example.myhydrovative.ui.activity.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT = 3000  // Digunakan untuk durasi splash activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getSupportActionBar()?.hide()

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_TIMEOUT.toLong())
    }
}