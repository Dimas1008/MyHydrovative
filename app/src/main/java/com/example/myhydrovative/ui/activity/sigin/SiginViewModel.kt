package com.example.myhydrovative.ui.activity.sigin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhydrovative.data.response.RegisterResponse
import com.example.myhydrovative.repository.UserRepository
import com.example.myhydrovative.ui.Event
import kotlinx.coroutines.launch

class SiginViewModel(private val repository: UserRepository) : ViewModel() {
    val registerResponse: LiveData<RegisterResponse> = repository.registerResponse
    val isLoading: LiveData<Boolean> = repository.loading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun postRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            repository.postRegister(name, email, password)
        }
    }
}