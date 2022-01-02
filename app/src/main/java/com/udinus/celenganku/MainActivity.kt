package com.udinus.celenganku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


// Splash screen
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "created")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "started")
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onStop() {
        super.onStop()
        finish()
        Log.d(TAG, "closed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "destroyed")
    }
}