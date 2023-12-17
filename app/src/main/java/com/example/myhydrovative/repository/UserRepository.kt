package com.example.myhydrovative.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.myhydrovative.ui.Event
import com.example.myhydrovative.api.ApiService
import com.example.myhydrovative.data.response.LoginResponse
import com.example.myhydrovative.data.response.RegisterResponse
import com.example.myhydrovative.session.SessionModel
import com.example.myhydrovative.session.SessionPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val preference: SessionPreference,
    private val apiService: ApiService
){
    companion object {
        private const val TAG = "StoryRepository"

        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            preference: SessionPreference,
            apiService: ApiService
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(preference, apiService)
        }.also { instance = it }
    }

    // Untuk memasukkan respomse Login
    private val nregisterResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = nregisterResponse

    // Untuk memasukkan respomse Login
    private val nloginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = nloginResponse

    private val nLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = nLoading

    private val ntoastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = ntoastText

    // Ini digunakan untuk register
    fun postRegister(name: String, email: String, password: String) {
        nLoading.value = true
        val client = apiService.postRegister(name, email, password)

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                nLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    nregisterResponse.value = response.body()
                    ntoastText.value = Event(response.body()?.message.toString())
                } else {
                    ntoastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                ntoastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    // Ini digunakan untuk login
    fun postLogin(email: String, password: String) {
        nLoading.value = true
        val client = apiService.postLogin(email, password)

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                nLoading.value = true
                if (response.isSuccessful && response.body() != null) {
                    nloginResponse.value = response.body()
                    ntoastText.value = Event(response.body()?.message.toString())
                } else {
                    ntoastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                ntoastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getSession(): LiveData<SessionModel> {
        return preference.getSession().asLiveData()
    }

    suspend fun saveSession(session: SessionModel) {
        preference.saveSession(session)
    }

    suspend fun login() {
        preference.login()
    }

    suspend fun logout() {
        preference.logout()
    }
}