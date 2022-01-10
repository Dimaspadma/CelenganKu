package com.udinus.celenganku.model

import androidx.lifecycle.*
import com.udinus.celenganku.data.Item
import com.udinus.celenganku.data.ItemDao
import kotlinx.coroutines.launch

class MainViewModel(private val itemDao: ItemDao) : ViewModel() {

    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewItemEntry(
        itemTag: String,
        itemTitle: String,
        itemNominal: String,
        itemDescription: String
    ): Item {
        return Item(
            tag = itemTag,
            title = itemTitle,
            nominal = itemNominal.toDouble(),
            description = itemDescription,
        )
    }

    fun addNewItemPemasukan(itemTitle: String, itemNominal: String, itemDescription: String) {
        val newItem = getNewItemEntry("PEMASUKAN", itemTitle, itemNominal, itemDescription)
        insertItem(newItem)

    }

    fun addNewItemPengeluaran(itemTitle: String, itemNominal: String, itemDescription: String) {
        val newItem = getNewItemEntry("PENGELUARAN", itemTitle, itemNominal, itemDescription)
        insertItem(newItem)
    }

    fun isEntryValid(itemTitle: String, itemNominal: String, itemDescription: String): Boolean {
        if (itemTitle.isBlank() || itemNominal.isBlank() || itemDescription.isBlank()) {
            return false
        }
        return true
    }

}

class MainViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}