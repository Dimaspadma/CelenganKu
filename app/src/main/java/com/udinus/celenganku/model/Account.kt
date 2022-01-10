package com.udinus.celenganku.model

data class Account(
    val username: String,
    val password: String,
    val cash: Double = 0.0
)