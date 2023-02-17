package com.example.myapplication


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CustomAdapter.*
import com.example.myapplication.Network.Plate
import com.example.myapplication.databinding.CellCustomBinding
import com.squareup.picasso.Picasso

class CustomAdapter(val items : List<Plate>, val context : Context) : RecyclerView.Adapter<CellViewHolder>() {


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

}