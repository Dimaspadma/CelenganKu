package com.udinus.celenganku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.udinus.celenganku.data.DaoAccount
import com.udinus.celenganku.databinding.ActivityLoginBinding
import com.udinus.celenganku.model.Account

class LoginActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_TAG = "LoginActivity"
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {

            val account = Account(
                binding.inputUsername.text.toString(),
                binding.inputPassword.text.toString()
            )

            DaoAccount(this).login(account, binding.root)
//            Log.d(LOGIN_TAG, "test ${test?.isEmpty}")
        }

        binding.textRegistrasi.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}