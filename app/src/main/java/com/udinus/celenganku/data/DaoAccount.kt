package com.udinus.celenganku.data

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.LoginActivity
import com.udinus.celenganku.RegisterActivity
import com.udinus.celenganku.databinding.ActivityLoginBinding
import com.udinus.celenganku.model.Account
import com.udinus.celenganku.utils.Hash

class DaoAccount(
    private var context: Context
) {

    fun register(account: Account){

        val database = Firebase.firestore

        val user = hashMapOf(
            "username" to account.username,
            "password" to Hash.sha256(account.password)
        )

        // Add a new document with a generated ID
        database.collection("accounts")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(RegisterActivity.REGISTER_TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(RegisterActivity.REGISTER_TAG, "Error adding document", e)
            }
    }

    fun login(account: Account, view: View)
    {
        val database = Firebase.firestore

        database.collection("accounts")
            .whereEqualTo("username", account.username)
            .whereEqualTo("password", Hash.sha256(account.password))
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
//                    Log.d(LoginActivity.LOGIN_TAG, "${document.id} => ${document.data}")
                    val username: String = document["username"].toString()
//                    val intent = Intent(context, HomeActivity::class.java)
//                    intent.putExtra(HomeActivity.USERNAME, username)
//                    context.startActivity(intent)
                }
                Snackbar.make(view, "User tidak ditemukan", Snackbar.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Log.w(LoginActivity.LOGIN_TAG, "Error getting documents: ", exception)
            }
    }
}