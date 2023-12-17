package com.example.myhydrovative.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhydrovative.data.response.LoginResponse
import com.example.myhydrovative.repository.UserRepository
import com.example.myhydrovative.session.SessionModel
import com.example.myhydrovative.ui.Event
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel(){
    val loginResponse: LiveData<LoginResponse> = repository.loginResponse
    val isLoading: LiveData<Boolean> = repository.loading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun postLogin(email: String, password: String) {
        viewModelScope.launch {
            repository.postLogin(email, password)
        }
    }

    fun saveSession(session: SessionModel) {
        viewModelScope.launch {
            repository.saveSession(session)
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login()
        }
    }
}