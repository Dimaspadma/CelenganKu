package com.udinus.celenganku.data

import com.udinus.celenganku.model.Item

class DataSource {

    fun loadHistory(): List<Item> {
        return listOf(
            Item("Sembako"),
            Item("Bantuan Sosial"),
            Item("Kopi kapal api")
        ).sortedBy { item ->
            item.title
        }
    }


}

