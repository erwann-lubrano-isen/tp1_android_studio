package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Network.Plate
import com.example.myapplication.Order
import com.example.myapplication.databinding.CellOrderBinding
import com.squareup.picasso.Picasso

class OrderAdapter(val items : List<Pair<Plate, Int>>, val update : () -> Unit) : RecyclerView.Adapter<OrderAdapter.CellOrderViewHolder>(){
    class CellOrderViewHolder(binding: CellOrderBinding) :RecyclerView.ViewHolder(binding.root){
        val imageView : ImageView = binding.imageView
        val itemName : TextView = binding.itemName
        val itemCount : TextView = binding.itemCount
        val itemPrice : TextView = binding.itemPrice
        val totalPrice : TextView = binding.totalPrice
        val deleteButton : View = binding.deleteOrderButton

    }

    override fun onBindViewHolder(holder: CellOrderViewHolder, position: Int) {
        val item = items[position]
        val plate = item.first
        val count = item.second
        Picasso.get().load(getThumbnail(plate)).into(holder.imageView)
        holder.itemPrice.text = plate.prices.first().price + " €"
        holder.totalPrice.text = (plate.prices.first().price.toDouble()*count).toString() + " €"
        holder.itemName.text = plate.name
        holder.itemCount.text = count.toString()
        holder.deleteButton.setOnClickListener {
            Order.deletePlate(plate)
            update()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellOrderViewHolder {
        val binding = CellOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    private fun getThumbnail(plate: Plate): String? {
        return if (plate.images.isNotEmpty() && plate.images.firstOrNull()?.isNotEmpty() == true) {
            plate.images.firstOrNull()
        } else {
            null
        }
    }
}

/*class CustomAdapter(val items : List<Plate>, val context : Context) : RecyclerView.Adapter<CellViewHolder>() {


    class CellViewHolder(binding: CellCustomBinding) : RecyclerView.ViewHolder(binding.root){
        val textView_name : TextView = binding.itemName
        val textView_price : TextView = binding.itemPrice
        val imageView : ImageView = binding.imageView
        val root = binding.root
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val item = items[position]
        holder.textView_name.text = item.name
        holder.textView_price.text = item.prices.first().price + " €"
        Picasso.get().load(getThumbnail(item)).into(holder.imageView)
        holder.root.setOnClickListener {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("plat", item)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    private fun getThumbnail(plate: Plate): String? {
        return if (plate.images.isNotEmpty() && plate.images.firstOrNull()?.isNotEmpty() == true) {
            plate.images.firstOrNull()
        } else {
            null
        }
    }

}*/