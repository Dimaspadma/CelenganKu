package com.udinus.celenganku.ui.form

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.databinding.FragmentRegisterBinding
import com.udinus.celenganku.model.Account
import com.udinus.celenganku.utils.Hash.sha256

class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private var account = Account("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            if (verification()) {
                register(account)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun verification(): Boolean {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        val confirmPassword = binding.inputConfirmPassword.text.toString()

        if (password != confirmPassword) {
            Log.d(TAG, "Password is not match")
            binding.inputConfirmPassword.error = "Password is not match"
            return false
        }

        if (email.isEmpty()) {
            binding.inputEmail.error = "Email cannot empty"
            return false
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.inputEmail.error = "Email not valid"
                return false
            }
        }

        if (password.isEmpty()) {
            Log.d(TAG, "Password cannot empty")
            binding.inputPassword.error = "Password cannot empty"
            return false
        } else {
            if (password.length < 5) {
                binding.inputConfirmPassword.error = "Password minimal 5 character"
                return false
            }
        }

        account = Account(email, sha256(password))
        return true

    }

    private fun register(account: Account){

        val database = Firebase.firestore

        val user = hashMapOf(
            "username" to account.username,
            "password" to account.password,
            "cash" to 0.0
        )

        // Add a new document with a generated ID
        database.collection("accounts")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                findNavController().navigate(action)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}