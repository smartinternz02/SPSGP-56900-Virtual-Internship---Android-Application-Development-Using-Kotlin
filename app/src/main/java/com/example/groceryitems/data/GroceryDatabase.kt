package com.example.groceryitems.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryItem::class], version = 1, exportSchema = false)
abstract class GroceryDatabase : RoomDatabase(){

    abstract fun groceryDao() : GroceryDao

    companion object{
        @Volatile
        private var INSTANCE : GroceryDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also{
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GroceryDatabase::class.java,
                "grocery_database"
            ).build()


    }

}