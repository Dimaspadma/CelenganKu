package com.udinus.celenganku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udinus.celenganku.adapter.HistoryAdapter
import com.udinus.celenganku.data.DataSource
import com.udinus.celenganku.databinding.ActivityHistoryBinding

// History Activity uses Recycler View
class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        val items = DataSource().loadHistory()

        // Change recycler layout and adapter
        recyclerView = binding.historyRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(binding.root, items)
    }
}