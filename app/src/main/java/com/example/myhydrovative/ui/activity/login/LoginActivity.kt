package com.example.myhydrovative.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myhydrovative.R
import com.example.myhydrovative.databinding.ActivityLoginBinding
import com.example.myhydrovative.session.SessionModel
import com.example.myhydrovative.ui.ViewModelFactory
import com.example.myhydrovative.ui.activity.main.MainActivity
import com.example.myhydrovative.ui.activity.sigin.SiginActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()

        // Ini untuk berpindah ke activity Sigin
        binding.tvSignupLog.setOnClickListener {
            val moveIntent = Intent(this, SiginActivity::class.java)
            startActivity(moveIntent)
            finish()
        }
    }

    private fun setupView() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = getString(R.string.halaman_masuk)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupAction() {
        binding.apply {
            btnLoginLog.setOnClickListener {
                val email = edtEmailLog.text.toString()
                val password = edtPasswordLog.text.toString()

                if (email.isBlank() || password.isBlank()) {
                    // Tangani kolom yang kosong
                    edtEmailLog.error = getString(R.string.kolom_email)
                    edtPasswordLog.setError(getString(R.string.kolom_password), null)
                } else if (password.length < 8) {
                    // Tangani panjang kata sandi kurang dari 8 karakter
                    edtPasswordLog.setError(getString(R.string.password_length_error), null)
                } else {
                    // Lanjutkan dengan proses login
                    showLoading()
                    postText()
                    showToast()
                    loginViewModel.login()
                    moveActivity()
                }
            }
        }
    }

    private fun showLoading() {
        loginViewModel.isLoading.observe(this@LoginActivity, Observer { isLoading ->
            binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }


    private fun postText() {
        binding.apply {
            loginViewModel.postLogin(
                edtEmailLog.text.toString(),
                edtPasswordLog.text.toString()
            )
        }

        loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
            saveSession(
                SessionModel(
                    response.loginResult?.name.toString(),
                    AUTHOR_KEY + (response.loginResult?.token.toString()),
                    true
                )
            )
        }
    }

    private fun saveSession(sessionModel: SessionModel) {
        loginViewModel.saveSession(sessionModel)
    }

    private fun showToast() {
        loginViewModel.toastText.observe(this@LoginActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@LoginActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun moveActivity() {
        loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
            if (!response.error) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val AUTHOR_KEY = "Bearer "
    }
}