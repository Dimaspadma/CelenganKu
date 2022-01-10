package com.udinus.celenganku.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.databinding.FragmentHomeBinding
import com.udinus.celenganku.model.AccountViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var username: String = ""
    private var cash: String = ""

    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accountViewModel.setId(activity?.intent?.extras?.getString("id").toString())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = Firebase.firestore
        database.collection("accounts")
            .document(accountViewModel.id.value.toString())
            .get()
            .addOnSuccessListener { document ->
                Log.d("HOme Fragment", "data ${document.data}")

                username = document["username"].toString()
                cash = document["cash"].toString()
                Log.d("Home", "${username} - ${cash}")

                binding.textUsername.text = username
                binding.cash.text = cash
            }

        Log.d("Home", "==$username - $cash")
        Log.d(
            "Home",
            "${accountViewModel.id.value} = ${accountViewModel.username.value} = ${accountViewModel.cash.value}"
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}