package com.udinus.celenganku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.udinus.celenganku.R
import com.udinus.celenganku.databinding.FragmentDetailHistoryBinding

class DetailHistoryFragment : Fragment() {

    private var _binding: FragmentDetailHistoryBinding? = null
    private val binding get() = _binding!!

    private var title: String? = null
    private var nominal: String? = null
    private var description: String? = null
    private var tag_item: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title")
            nominal = it.getString("nominal")
            description = it.getString("description")
            tag_item = it.getString("tag_item")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailHistoryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            if (tag_item == "PEMASUKAN") {
                image.setImageResource(R.drawable.ic_pemasukan)
            } else {
                image.setImageResource(R.drawable.ic_cart)
            }

            textTitle.text = title
            textNominal.text = nominal
            textDescription.text = description
        }
    }

}