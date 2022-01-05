package com.udinus.celenganku.data

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.udinus.celenganku.RegisterActivity
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


}