package com.udinus.celenganku.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity(tableName = "item")
class Item(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "tag")
    val tag: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "nominal")
    val nominal: Double,

    @ColumnInfo(name = "description")
    val description: String
)

fun Item.getFormattedPrice(): String =
    NumberFormat.getCurrencyInstance().format(nominal).replace(',', '.')