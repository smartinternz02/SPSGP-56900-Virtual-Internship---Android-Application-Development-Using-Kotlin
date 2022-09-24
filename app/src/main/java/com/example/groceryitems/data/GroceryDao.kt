package com.example.groceryitems.data


import androidx.lifecycle.LiveData
import androidx.room.*

/*
* Dao class defines the queries and correspond them with method calls
* */

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItem)

    @Delete
    suspend fun delete(item: GroceryItem)

    @Query("SELECT * FROM grocery_items")
    fun showAllItems() : LiveData<List<GroceryItem>>

}