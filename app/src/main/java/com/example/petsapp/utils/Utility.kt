package com.example.petsapp.utils

import android.content.Context
import com.example.petsapp.models.PetModelClass
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class Utility {
    fun getJson(context: Context, file: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(file).bufferedReader().use{ it.readText() }
        } catch (exception: IOException) {
            exception.printStackTrace()
            return  null
        }
        return  jsonString
    }

    fun parseJsonData(json :String) : ArrayList<PetModelClass> {
        var petList = ArrayList<PetModelClass>()
        try {
            val obj = JSONObject(json)
            val petsArray = obj.getJSONArray("pets")

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
        return petList
    }

    fun parseConfigJsonData(json :String) : String {
        var allowedTime : String = ""
        try {
            val obj = JSONObject(json)
            val settingsObj = obj.getJSONObject("settings")
            allowedTime = settingsObj.getString("workHours")

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return allowedTime
    }
}