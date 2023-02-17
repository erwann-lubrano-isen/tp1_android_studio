package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Network.Plate
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.ui.PictureAdapter

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var plate = intent.getSerializableExtra("plat") as? Plate


        plate?.let {

            val imgs = it.images.filter { it -> !it.isNullOrEmpty() }
            /*supportActionBar?.title = it.name*/
            binding.toolBarTitle.text = it.name
            binding.textPlateName.text = it.name
            binding.imagePager.adapter = PictureAdapter(imgs, this)

            if(!imgs.isNullOrEmpty()){
                binding.imagePager.visibility = View.VISIBLE
            }

            count = Order.getPlatesCount(plate)
            update(plate)

            binding.increaseQuantityButton.setOnClickListener {
                //Order.addPlate(plate)
                count += 1
                update(plate)
            }

            binding.decreaseQuantityButton.setOnClickListener {
                //Order.deletePlate(plate)
                if(count <= 1)count = 0
                else count -= 1
                update(plate)
            }

            binding.priceButton.setOnClickListener {
                Order.setPlateCount(plate, count)
                Order.store(this)
                finish()
            }

            binding.ingredients.text = it.ingredients?.map { it.name }?.joinToString(", ")?:""
        }

        binding.ordersButton.setOnClickListener {
            var intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

    }

    private fun update(plate: Plate){
        val price = plate.prices.first().price.toDouble()
        binding.quantityTextView.text = count.toString()
        binding.priceButton.text = (count.toDouble() * price).toString() + " €"
    }
}