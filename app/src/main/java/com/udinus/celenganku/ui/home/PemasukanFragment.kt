package com.udinus.celenganku.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udinus.celenganku.CelenganKuApplication
import com.udinus.celenganku.data.Item
import com.udinus.celenganku.databinding.FragmentPemasukanBinding
import com.udinus.celenganku.model.HistoryViewModel
import com.udinus.celenganku.model.HistoryViewModelFactory

class PemasukanFragment : Fragment() {

    private var _binding: FragmentPemasukanBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by activityViewModels {
        HistoryViewModelFactory(
            (activity?.application as CelenganKuApplication).database
                .itemDao()
        )
    }

    lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPemasukanBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submit.setOnClickListener {
            addNewItem()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.inputTitle.text.toString(),
            binding.inputNominal.text.toString(),
            binding.inputDescription.text.toString()
        )
    }

    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItemPemasukan(
                binding.inputTitle.text.toString(),
                binding.inputNominal.text.toString(),
                binding.inputDescription.text.toString()
            )

            val action = PemasukanFragmentDirections.actionPemasukanFragmentToHistoryFragment()
            findNavController().navigate(action)
        }
    }

}