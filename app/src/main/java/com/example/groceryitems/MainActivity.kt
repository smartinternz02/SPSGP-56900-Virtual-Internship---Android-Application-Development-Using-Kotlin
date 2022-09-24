package com.example.groceryitems

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryitems.data.GroceryDao
import com.example.groceryitems.data.GroceryDatabase
import com.example.groceryitems.data.GroceryItem
import com.example.groceryitems.databinding.GroceryItemBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.observeOn

/*
* MainActivity contains all the initialization of classes and creates the behaviour of the
* application
* */

class MainActivity : AppCompatActivity(), GroceryAdapter.GroceryOnClick {

    lateinit var groceryItemsRv : RecyclerView
    lateinit var addActionButton : FloatingActionButton
    lateinit var list : List<GroceryItem>
    lateinit var adapter: GroceryAdapter
    lateinit var viewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        groceryItemsRv = findViewById(R.id.recyclerview)
        addActionButton = findViewById(R.id.add_grocery)
        list = ArrayList<GroceryItem>()
        adapter = GroceryAdapter(list, this)
        groceryItemsRv.layoutManager = LinearLayoutManager(this)
        groceryItemsRv.adapter = adapter
        val repository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory).get(GroceryViewModel::class.java)
        viewModel.getAllGroceryItems().observe(this, Observer{
            adapter.list = it
            adapter.notifyDataSetChanged()
        })

        addActionButton.setOnClickListener{
            openDialog()
        }
    }

    fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancel)
        val saveBtn = dialog.findViewById<Button>(R.id.save)
        val nameInput = dialog.findViewById<EditText>(R.id.name_input)
        val qtyInput = dialog.findViewById<EditText>(R.id.qty_input)
        val priceInput = dialog.findViewById<EditText>(R.id.price_input)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        saveBtn.setOnClickListener {
            val groceryName : String = nameInput.text.toString()
            val groceryQty : String = qtyInput.text.toString()
            val groceryPrice : String = priceInput.text.toString()
            val qty = groceryQty.toInt()
            val price = groceryPrice.toFloat()
            if(groceryName.isNotEmpty() && groceryPrice.isNotEmpty() && groceryQty.isNotEmpty()){
                val items = GroceryItem(groceryName,qty,price)
                viewModel.insert(items)
                Toast.makeText(applicationContext,"Grocery Saved...",Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Enter all details!",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun onItemClick(groceryItem: GroceryItem) {
        viewModel.delete(groceryItem)
        adapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Grocery Removed..", Toast.LENGTH_SHORT).show()
    }
}

