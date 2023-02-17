package com.example.myapplication.Network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Plate(
    @SerializedName("id") val id : String,
    @SerializedName("name_fr") val name : String,
    @SerializedName("images") val images : List<String>,
    @SerializedName("prices") val prices : List<Price>,
    @SerializedName("ingredients") val ingredients : List<Ingredient>) : Serializable {

}