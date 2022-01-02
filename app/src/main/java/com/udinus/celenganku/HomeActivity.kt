package com.udinus.celenganku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.udinus.celenganku.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "username"
    }

    private val TAG = "HomeActivity"

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate first")

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
//
        val username = intent?.extras?.getString(USERNAME).toString()
        binding.title.text = username
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy is called")
    }
}