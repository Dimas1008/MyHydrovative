package com.example.myhydrovative.ui

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myhydrovative.api.ApiConfig
import com.example.myhydrovative.repository.UserRepository
import com.example.myhydrovative.session.SessionPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")
object Injection {
    fun provideRepository(context: Context): UserRepository {
        val preferences = SessionPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(preferences, apiService)
    }
}