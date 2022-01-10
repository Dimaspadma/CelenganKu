package com.udinus.celenganku.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.CelenganKuApplication
import com.udinus.celenganku.data.Item
import com.udinus.celenganku.databinding.FragmentPemasukanBinding
import com.udinus.celenganku.model.MainViewModel
import com.udinus.celenganku.model.MainViewModelFactory

class PemasukanFragment : Fragment() {

    private var _binding: FragmentPemasukanBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(
            (activity?.application as CelenganKuApplication).database
                .itemDao()
        )
    }

    lateinit var item: Item
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = activity?.intent?.extras?.getString("id").toString()
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
        Log.d("PemasukanFragment", "id: $id")
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

            val database = Firebase.firestore
            database.collection("accounts")
                .document(id)
                .get()
                .addOnSuccessListener { document ->

                    val cash = document["cash"].toString()
                        .toDouble() + binding.inputNominal.text.toString().toDouble()

                    database.collection("accounts")
                        .document(id)
                        .update("cash", cash)
                }

            val action = PemasukanFragmentDirections.actionPemasukanFragmentToHistoryFragment()
            findNavController().navigate(action)
        }
    }

}