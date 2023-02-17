package com.example.myapplication.Network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.MenuCategory
import com.google.gson.GsonBuilder
import org.json.JSONObject

class MenuModel{
    val context: Context
    val category: MenuCategory
    var lmbd: (List<Plate>) -> Unit

    constructor(
        context: Context,
        category: MenuCategory,
        lmbd: (List<Plate>) -> Unit
    ){
        this.context= context
        this.category = category
        this.lmbd = lmbd
        makeRequest()
    }


    private fun makeRequest() {
        val queue = Volley.newRequestQueue(context)
        val params = JSONObject()
        params.put(NetworkConstants.idShopKey, 1)
        val request = JsonObjectRequest(
            Request.Method.POST,
            NetworkConstants.url,
            params,
            Response.Listener {
                    result ->
                parseData(result.toString())
            },
            Response.ErrorListener{
                    error ->
                Log.d("stderr", error.toString())
            }
        )
        queue.add(request)

    }

    private fun parseData(s_data: String) {
        data = GsonBuilder().create().fromJson(s_data, MenuResult::class.java).data
        loaded = true

        val result = data.first{
            it.name == category.string
        }
        lmbd(result.items)
    }

    companion object{
        private var loaded = false
        private lateinit var data : List<Category>

        public fun isLoaded() : Boolean{
            return loaded
        }

        public fun getData() : List<Category> {
            return data
        }

        public fun getItems(menuCategory: MenuCategory) : List<Plate>{
            return data.first{
                it.name == menuCategory.string
            }.items
        }

        public fun getPlate(plateId : String) : Plate?{
            data.forEach {
                it ->
                val plate = it.items.find {
                    it.id.equals(plateId)
                }
                if (plate != null) {
                    return plate
                }

            }
            return null
        }

    }
}