package com.udinus.celenganku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udinus.celenganku.data.DaoAccount
import com.udinus.celenganku.model.Account

class RegisterActivity : AppCompatActivity() {

    companion object {
        const val REGISTER_TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        DaoAccount(this).register(Account("test", "test123"))
    }
}