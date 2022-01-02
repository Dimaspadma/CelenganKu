package com.udinus.celenganku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.udinus.celenganku.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    companion object {
        const val TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailHistoryBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        val titleText = intent?.extras?.getString(TITLE).toString()

        binding.title.text = titleText
    }
}