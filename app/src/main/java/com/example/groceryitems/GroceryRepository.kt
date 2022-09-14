package com.example.groceryitems

import com.example.groceryitems.data.GroceryDatabase
import com.example.groceryitems.data.GroceryItem

class GroceryRepository(private val database: GroceryDatabase) {

    suspend fun insert(items:GroceryItem) = database.groceryDao().insert(items)
    suspend fun delete(items:GroceryItem) = database.groceryDao().delete(items)

    fun allItems() = database.groceryDao().showAllItems()
}