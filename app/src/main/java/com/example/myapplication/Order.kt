package com.example.myapplication

import android.content.Context
import android.util.Log
import com.example.myapplication.Network.MenuModel
import com.example.myapplication.Network.Plate
import com.google.gson.Gson
import java.io.File
import kotlin.math.roundToInt

class Order {
    companion object{
        var orders : MutableMap<String,Double> = mutableMapOf()


        public fun deletePlate(plate: Plate){
            orders.remove(plate.id)
        }
        public fun getPlatesCount(plate: Plate) : Int {
            return (orders[plate.id]?:0.0).roundToInt()
        }

        public fun setPlateCount(plate: Plate, count : Int){
            if(count > 0){
                orders[plate.id] = count.toDouble()
            }else if (getPlatesCount(plate) > 0){
                orders.remove(plate.id)
            }
        }

        public fun getListPlate() : List<Pair<Plate, Int>> {
            val list : MutableList<Pair<Plate, Int>> = mutableListOf()
            if(MenuModel.isLoaded()){
                orders.forEach { (plateId: String, count: Double) ->
                    val plate = MenuModel.getPlate(plateId)
                    if (plate != null) {
                        list.add(Pair(plate,count.roundToInt()))
                    }
                }
            }
            return list
        }

        public fun getTotalAmount() : Double{
            val list = getListPlate()
            var amount : Double = 0.0
            list.forEach {
                (plate, count) ->
                amount += plate.prices.first().price.toDouble()*count.toDouble()
            }
            return amount
        }

        public fun store(context : Context){
            Gson().toJson(orders.toList())
            val path = context.getExternalFilesDir(null)
            val jsonDir = File(path,"json_dir")
            jsonDir.mkdirs()
            val file = File(jsonDir,"orders.json")
            file.writeText(Gson().toJson(orders).toString())
            Log.d("cout",orders.toString())

        }

        public fun load(context : Context){
            Gson().toJson(orders)
            val path = context.getExternalFilesDir(null)
            val jsonDir = File(path,"json_dir")
            if(jsonDir.exists()) {
                val file = File(jsonDir, "orders.json")
                if(file.exists()) {
                    val json = file.readText()
                    Log.d("cout", json)
                    try {
                        val gson = Gson()
                        orders = (gson.fromJson<Map<*, *>>(
                            json,
                            MutableMap::class.java
                        )?: mutableMapOf<String,Double>()) as MutableMap<String, Double>
                    }catch (e : Exception){

                    }


                }
            }
        }

    }
}