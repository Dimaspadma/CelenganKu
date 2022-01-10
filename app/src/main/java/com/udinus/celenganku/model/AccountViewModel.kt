package com.udinus.celenganku.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

class AccountViewModel : ViewModel() {

    val username = MutableLiveData("")
    val id = MutableLiveData("")
    val cash = MutableLiveData(0.0)

    fun setUsername(name: String) {
        username.value = name
    }

    fun setId(inputId: String) {
        id.value = inputId
    }

    fun setCash(inputCash: Double) {
        cash.value = inputCash
    }

    fun showCash(): String {
        return NumberFormat.getCurrencyInstance()
            .format(cash.value)
            .replace(',', '.')
            .replace("$", " ")
    }

    fun addCash(value: Double) {
        cash.value = cash.value?.plus(value)
    }

    fun subtractCash(value: Double) {
        cash.value = cash.value?.minus(value)
    }

}