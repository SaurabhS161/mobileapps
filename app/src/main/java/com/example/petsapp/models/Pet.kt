package com.example.petsapp.models

data class PetsList(
    val pet: ArrayList<PetModelClass>
)

data class PetModelClass(
    var image_url : String,
    var title :String,
    var content_url: String,
    var date_added :String
)
