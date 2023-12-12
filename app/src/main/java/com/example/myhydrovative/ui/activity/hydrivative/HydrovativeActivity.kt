package com.example.myhydrovative.ui.activity.hydrivative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityHydrovativeBinding
import com.example.myhydrovative.ui.fragment.camera.CameraFragment
import com.example.myhydrovative.ui.fragment.home.HomeFragment
import com.example.myhydrovative.ui.fragment.profile.ProfileFragment
import com.example.myhydrovative.ui.fragment.tanaman.TanamanFragment

class HydrovativeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHydrovativeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hydrovative)

        binding = ActivityHydrovativeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.navViewBottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> replaceFragment(HomeFragment())
                R.id.menu_camera -> replaceFragment(CameraFragment())
                R.id.menu_tanaman -> replaceFragment(TanamanFragment())
                R.id.menu_profile -> replaceFragment(ProfileFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}