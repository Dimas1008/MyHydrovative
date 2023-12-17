package com.example.myhydrovative.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myhydrovative.repository.UserRepository
import com.example.myhydrovative.ui.activity.login.LoginViewModel
import com.example.myhydrovative.ui.activity.main.MainViewModel
import com.example.myhydrovative.ui.activity.sigin.SiginViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("CHECK_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(SiginViewModel::class.java) -> {
                SiginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknow ViewModel " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}