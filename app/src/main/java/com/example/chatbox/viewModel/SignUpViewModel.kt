package com.example.chatbox.viewModel

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.chatbox.database.User
import com.example.chatbox.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpViewModel {
    fun signUp(name: String, email: String, password: String, activity: Activity){
        val mAuth = FirebaseAuth.getInstance()

        // Creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDb(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.finish()
                    activity.startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(activity, "erreur sur la création de compte", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun addUserToDb(name: String, email: String, uid: String) {
        val mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}