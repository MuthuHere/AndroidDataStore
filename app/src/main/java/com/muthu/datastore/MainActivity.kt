package com.muthu.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.ViewModelProvider
import com.muthu.datastore.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init view model
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //observe view model
        mainViewModel.readNameFromStorage.observe(this, {
            tvNameAndAge.text = it
        })
        mainViewModel.readAgeFromStorage.observe(this, {
            val name = tvNameAndAge.text
            d("mmm","$it")
            tvNameAndAge.text = "$name $it"
        })

        btnSave.setOnClickListener {
            mainViewModel.saveUserDataStore("Muthu",28)
        }

    }
}