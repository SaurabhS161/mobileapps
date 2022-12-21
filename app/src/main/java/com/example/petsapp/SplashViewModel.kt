package com.example.petsapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petsapp.models.PetModelClass
import com.example.petsapp.utils.Utility

class SplashViewModel(/*private val utils: Utility*/): ViewModel() {

    private val validTime = MutableLiveData<Boolean>()
    val _validTime: LiveData<Boolean> get() = validTime
    private lateinit var checkTime : String

    fun fetchAllowedTime(context: Context) : String {
        var utils: Utility = Utility()
        val jsonString = utils.getJson(context, "config.json")
        if (jsonString != null) {
            checkTime = utils.parseConfigJsonData(jsonString)
        }
        return checkTime
    }
}