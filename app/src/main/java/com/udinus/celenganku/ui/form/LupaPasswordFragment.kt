package com.udinus.celenganku.ui.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.databinding.FragmentLupaPasswordBinding

class LupaPasswordFragment : Fragment() {

    private var _binding: FragmentLupaPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLupaPasswordBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setOnClickListener {
            val database = Firebase.firestore

            database.collection("accounts")
                .whereEqualTo("username", binding.inputUsername.text.toString())
                .get()
                .addOnSuccessListener { documents ->

                    var username: String? = null

                    for (document in documents) {
                        username = document["username"].toString()

                        val id = document.id

                        Log.d("Lupa password", "$document")

                        val action =
                            LupaPasswordFragmentDirections.actionLupaPasswordFragmentToNewPasswordFragment(
                                id = id,
                                username = username
                            )
                        findNavController().navigate(action)

                    }
                    if (username == null) Snackbar.make(
                        this.requireView(),
                        "User not found",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}