# Android Data Store

#### Init data store with context

    private val dataStore = context.createDataStore(
           name = DATA_STORE_NAME,
       )
#### Save User data with DataStore

      suspend fun storeUserData(name: String, age: Int) {
        dataStore.edit { pref ->
            pref[keyName] = name
            pref[keyAge] = age
        }
    }
    
#### Read User data from DataStore

      val userNameFlow: Flow<String> = dataStore.data.map {
        it[keyName] ?: "No name added"
      }.catch { exception ->
         if (exception is IOException) {
             emit("No Data")
          } else {
             throw exception
          }
     }
     
#### Creating object in ViewModel
        val readNameFromStorage = appStorage.userNameFlow.asLiveData()
        val readAgeFromStorage = appStorage.userAge.asLiveData()
#### Saving data in ViewModel
       fun saveUserDataStore(name: String, age: Int) = viewModelScope.launch(Dispatchers.IO) {
        appStorage.storeUserData(name, age)
       }
#### Init ViewModel in MainActivity

       mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
#### Observing ViewModel to update the View
        mainViewModel.readNameFromStorage.observe(this, {
            tvNameAndAge.text = it
        })
        
        
#### Finally used libraries for Coroutines and LiveData is here

    // Preferences DataStore
    implementation "androidx.datastore:datastore-preferences:1.0.0-alpha01"

    // Proto DataStore
    implementation "androidx.datastore:datastore-core:1.0.0-alpha01"
    // Lifecycle components
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
    implementation "androidx.lifecycle:lifecycle-common-java8:2.2.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"

    // Kotlin coroutines components
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    
    
    Happie Coding:)


