package com.example.groceryitems

import androidx.lifecycle.*
import com.example.groceryitems.data.GroceryDao
import com.example.groceryitems.data.GroceryItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository) : ViewModel() {

    fun insert(items : GroceryItem) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items : GroceryItem) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllGroceryItems() = repository.allItems()
}

class GroceryViewModelFactory(private val repository: GroceryRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GroceryViewModel(repository) as T
        }

    }