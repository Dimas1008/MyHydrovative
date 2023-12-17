package com.example.myhydrovative.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhydrovative.repository.UserRepository
import com.example.myhydrovative.session.SessionModel
import com.example.myhydrovative.ui.Event
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = repository.loading
    val toastText: LiveData<Event<String>> = repository.toastText

    fun getSesion(): LiveData<SessionModel> {
        return repository.getSession()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}