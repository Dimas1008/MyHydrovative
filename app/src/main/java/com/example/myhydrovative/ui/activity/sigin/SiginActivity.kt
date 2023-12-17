package com.example.myhydrovative.ui.activity.sigin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivitySiginBinding
import com.example.myhydrovative.ui.ViewModelFactory
import com.example.myhydrovative.ui.activity.login.LoginActivity

class SiginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiginBinding
    private lateinit var factory: ViewModelFactory
    private val siginViewModel: SiginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin)

        binding = ActivitySiginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewMOdel()
        setupAction()

        binding.tvLoginSig.setOnClickListener {
            val moveIntent = Intent(this, LoginActivity::class.java)
            startActivity(moveIntent)
            finish()
        }
    }

    private fun setupViewMOdel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupView() {
        binding = ActivitySiginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = getString(R.string.halaman_registrasi)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupAction() {
        binding.apply {
            btnSigin.setOnClickListener {
                val username = edtUsernameSig.text.toString()
                val email = edtEmailSig.text.toString()
                val password = edtPasswordSig.text.toString()
                val confirmPassword = edtConfirmPasswordSig.text.toString()

                if (username.isBlank()) {
                    // Tangani kolom nama yang kosong
                    edtUsernameSig.error = getString(R.string.kolom_nama)
                } else {
                    edtUsernameSig.error = null
                }

                if (email.isBlank()) {
                    // Tangani kolom email yang kosong
                    edtEmailSig.error = getString(R.string.kolom_email)
                } else {
                    edtEmailSig.error = null
                }

                if (password.length < 8) {
                    // Tangani panjang kata sandi kurang dari 8 karakter
                    edtPasswordSig.setError(getString(R.string.password_length_error), null)
                } else {
                    edtPasswordSig.error = null
                }

                if (password != confirmPassword) {
                    // Tangani kata sandi yang tidak sesuai
                    edtConfirmPasswordSig.setError(getString(R.string.password_kurangtepat), null)
                } else {
                    edtConfirmPasswordSig.error = null
                }

                if (username.isNotBlank() && email.isNotBlank() && password.length >= 8 && password == confirmPassword) {
                    // Lanjutkan dengan registrasi hanya jika semua kondisi terpenuhi
                    showLoading()
                    postText()
                    showToast()
                    moveActivity()
                }
            }
        }
    }


    private fun showLoading() {
        siginViewModel.isLoading.observe(this@SiginActivity) {
            binding.pbSigin.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun postText() {
        binding.apply {
            siginViewModel.postRegister(
                edtUsernameSig.text.toString(),
                edtEmailSig.text.toString(),
                edtPasswordSig.text.toString(),
            )
        }
    }

    private fun showToast() {
        siginViewModel.toastText.observe(this@SiginActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@SiginActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity() {
        siginViewModel.registerResponse.observe(this@SiginActivity) { response ->
            if (!response.error) {
                startActivity(Intent(this@SiginActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}