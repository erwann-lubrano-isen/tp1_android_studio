package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Network.Plate
import com.example.myapplication.databinding.ActivityOrderBinding
import com.example.myapplication.ui.OrderAdapter

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showData(Order.getListPlate())
    }

    private fun showData(items : List<Pair<Plate,Int>>){
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvOrders.layoutManager = llm
        binding.rvOrders.adapter = OrderAdapter(items){
            showData(Order.getListPlate())
        }
        binding.totalPriceTextView.text = "Total : " + Order.getTotalAmount().toString() + " €"
    }

    override fun onDestroy() {
        Order.store(this)
        super.onDestroy()
    }
}