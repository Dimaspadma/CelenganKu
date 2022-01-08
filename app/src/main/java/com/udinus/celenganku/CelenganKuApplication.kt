package com.udinus.celenganku

import android.app.Application
import com.udinus.celenganku.data.ItemRoomDatabase

class CelenganKuApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}