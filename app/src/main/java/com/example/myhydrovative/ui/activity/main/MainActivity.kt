package com.example.myhydrovative.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityMainBinding
import com.example.myhydrovative.ui.ViewModelFactory
import com.example.myhydrovative.ui.activity.welcome.WelcomeActivity
import com.example.myhydrovative.ui.fragment.camera.CameraFragment
import com.example.myhydrovative.ui.fragment.home.HomeFragment
import com.example.myhydrovative.ui.fragment.profile.ProfileFragment
import com.example.myhydrovative.ui.fragment.tanaman.TanamanFragment

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    // private lateinit var storyAdapter: ListStoryAdapter
    private var token = ""
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        setupView()
        setupViewModel()
        //setupAdapter()
        setupUser()

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

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun setupUser() {
        mainViewModel.getSesion().observe(this@MainActivity) {
            token = it.token
            if (!it.isLogin) {
                moveActivity()
            } else {
                setupData()
            }
        }
        showToast()
    }

    private fun moveActivity() {
        startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
        finish()
    }

    private fun setupData() {
        // mainViewModel.getListStory.observe(this@MainActivity) { pagingData ->
        //    storyAdapter.submitData(lifecycle, pagingData)
        // }
    }

    private fun showToast() {
        mainViewModel.toastText.observe(this@MainActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@MainActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}