package com.muthu.datastore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.muthu.datastore.storage.AppStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val appStorage = AppStorage(application)

    val readNameFromStorage = appStorage.userNameFlow.asLiveData()
    val readAgeFromStorage = appStorage.userAge.asLiveData()

    fun saveUserDataStore(name: String, age: Int) = viewModelScope.launch(Dispatchers.IO) {
        appStorage.storeUserData(name, age)
    }
}