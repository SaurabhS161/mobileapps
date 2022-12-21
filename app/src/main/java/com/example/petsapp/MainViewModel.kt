package com.example.petsapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petsapp.models.PetModelClass
import com.example.petsapp.utils.Utility
import org.json.JSONException
import org.json.JSONObject

class MainViewModel(/*private val utils: Utility*/): ViewModel() {
    private val mutablePets = MutableLiveData<List<PetModelClass>>()
    val pets: LiveData<List<PetModelClass>> get() = mutablePets

    fun fetchPets(context: Context) {
        var utils: Utility = Utility()
        val jsonString = utils.getJson(context, "pets_list.json")
        if (jsonString != null) {
            mutablePets.value = utils.parseJsonData(jsonString)
        }
    }

  /*  private fun parseJsonData(json :String) {
        try {
            val obj = JSONObject(json)
            val petsArray = obj.getJSONArray("pets")
            petList = ArrayList<PetModelClass>()

            for (i in 0 until petsArray.length()) {
                val pet = petsArray.getJSONObject(i)

                val imageUrl = pet.getString("image_url")
                val title = pet.getString("title")
                val contentUrl = pet.getString("content_url")
                val dateAdded = pet.getString("date_added")

                val petDetails =  PetModelClass(imageUrl, title, contentUrl, dateAdded)

                (petList as ArrayList<PetModelClass>).add(petDetails)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }*/
}