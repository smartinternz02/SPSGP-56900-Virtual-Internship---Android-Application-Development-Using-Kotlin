package com.example.groceryitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryitems.data.GroceryItem

/*
* This class is used to display the data fetched from the database into the UI interface
* */

class GroceryAdapter(var list: List<GroceryItem>, val groceryClicked : GroceryOnClick)
    : RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>(){

    class GroceryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val groceryName = itemView.findViewById<TextView>(R.id.grcoery_item_name)
        val groceryQty = itemView.findViewById<TextView>(R.id.grcoery_item_quantity)
        val groceryPrice = itemView.findViewById<TextView>(R.id.grcoery_item_price)
        val totalAmount = itemView.findViewById<TextView>(R.id.total_amount)
        val delete = itemView.findViewById<ImageView>(R.id.delete_icon)
    }

    interface GroceryOnClick {
        fun onItemClick(groceryItem: GroceryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.groceryName.text = list.get(position).itemName
        holder.groceryQty.text = list.get(position).quantity.toString()
        holder.groceryPrice.text = "Rs." + list.get(position).price.toString()
        val itemTotal : Float = list.get(position).price * list.get(position).quantity
        holder.totalAmount.text = "Rs." + itemTotal.toString()
        holder.delete.setOnClickListener {
            groceryClicked.onItemClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

