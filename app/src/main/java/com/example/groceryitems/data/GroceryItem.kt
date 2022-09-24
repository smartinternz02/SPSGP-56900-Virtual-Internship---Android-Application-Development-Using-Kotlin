package com.example.groceryitems.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* This class defines the properties of the database table which we are going to create
* Every property defined would be representing a column
* */
@Entity(tableName = "grocery_items")
data class GroceryItem(

    @PrimaryKey
    val itemName: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "price")
    val price: Float
)
