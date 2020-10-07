package com.muthu.datastore.storage

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import com.muthu.datastore.storage.AppStorage.PrefKeys.keyAge
import com.muthu.datastore.storage.AppStorage.PrefKeys.keyName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val DATA_STORE_NAME = "myDataStore"


class AppStorage(context: Context) {


    private object PrefKeys {
        val keyName = preferencesKey<String>("name")
        val keyAge = preferencesKey<Int>("age")
    }

    private val dataStore = context.createDataStore(
        name = DATA_STORE_NAME,
    )


    //save data
    suspend fun storeUserData(name: String, age: Int) {
        dataStore.edit { pref ->
            pref[keyName] = name
            pref[keyAge] = age
        }
    }

    //read data
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[keyName] ?: "No name added"
    }.catch { exception ->
        if (exception is IOException) {
            emit("No Data")
        } else {
            throw exception
        }
    }

    val userAge: Flow<Int> = dataStore.data.map {
        it[keyAge] ?: 0
    }
}