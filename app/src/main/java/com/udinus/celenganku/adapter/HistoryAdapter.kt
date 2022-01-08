package com.udinus.celenganku.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udinus.celenganku.databinding.ListItemBinding
import com.udinus.celenganku.model.Item

class HistoryAdapter(
    private val view: View,
    private val items: List<Item>
): RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>() {

    private lateinit var _binding: ListItemBinding

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding: ListItemBinding = ListItemBinding.bind(view)
//        val textView: TextView = view.findViewById(R.id.title_list)

        fun bind(item: Item) {
            binding.itemButton.text = item.title
            binding.itemButton.setOnClickListener {
//                Snackbar.make(view, "${item.title} is clicked", Snackbar.LENGTH_SHORT).show()
                Log.d("CLICK", "${item.title} have been clicked")

//                val intent = Intent(view.context, DetailHistoryActivity::class.java)
//                intent.putExtra(DetailHistoryActivity.TITLE, item.title)
//                view.context.startActivity(intent)
            }

//            textView.text = item.title
//            textView.setOnClickListener {
//                Toast.makeText(context, "Test Tet tet", Toast.LENGTH_SHORT).show()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//         val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return ItemViewHolder(layout)
        _binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(_binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}