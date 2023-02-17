package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Network.MenuModel

import com.example.myapplication.Network.Plate
import com.example.myapplication.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getSerializableExtra("category") as? MenuCategory
        supportActionBar?.title = category?.string

        if (MenuModel.isLoaded()){
            showData(MenuModel.getItems(category?:MenuCategory.MAINS))
        }else {
            MenuModel(
                this,
                category ?: MenuCategory.MAINS
            ) { items ->
                this.showData(items)

            }
        }

    }


    private fun showData(items : List<Plate>){
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvItems.layoutManager = llm
        binding.rvItems.adapter = CustomAdapter(items, this)
    }

}