package com.udinus.celenganku

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.databinding.FragmentLoginBinding
import com.udinus.celenganku.model.Account
import com.udinus.celenganku.utils.Hash
import com.udinus.celenganku.utils.Hash.sha256

class LoginFragment : Fragment() {

    companion object {
        const val TAG = "LoginFragment"
    }

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

        setAccount()

        binding.buttonLogin.setOnClickListener {
            login(account)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
            .whereEqualTo("password", Hash.sha256(account.password))
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val username: String = document["username"].toString()

                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
                Snackbar.make(this.requireView(), "User tidak ditemukan", Snackbar.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}