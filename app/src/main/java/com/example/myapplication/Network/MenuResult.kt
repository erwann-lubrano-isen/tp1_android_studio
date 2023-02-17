package com.example.myapplication.Network

import com.google.gson.annotations.SerializedName
import java.io.Serializable



class MenuResult(@SerializedName("data") val data: List<Category>) : Serializable {
}