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
import com.udinus.celenganku.databinding.FragmentLoginBinding
import com.udinus.celenganku.model.Account
import com.udinus.celenganku.utils.Hash.sha256

class LoginFragment : Fragment() {


    private val TAG = "LoginFragment"

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var account: Account = Account("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonLogin.setOnClickListener {
            setAccount()
            login(account)
        }
        
        binding.textRegistrasi.setOnClickListener { 
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.textLupaPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToLupaPasswordFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "OnDestroy is called")
        _binding = null
    }

    private fun setAccount() {

        val username = binding.inputUsername.text.toString()
        val password = binding.inputPassword.text.toString()

        account = Account(username, sha256(password))
    }

    private fun login(account: Account)
    {
        val database = Firebase.firestore

        database.collection("accounts")
            .whereEqualTo("username", account.username)
            .whereEqualTo("password", sha256(account.password))
            .get()
            .addOnSuccessListener { documents ->

                var username: String? = null

                for (document in documents) {
                    username = document["username"].toString()

                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(username=username)
                    findNavController().navigate(action)
                }
                if (username == null) Snackbar.make(this.requireView(), "User not found", Snackbar.LENGTH_SHORT).show()

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}