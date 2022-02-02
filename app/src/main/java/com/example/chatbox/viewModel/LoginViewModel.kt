package com.example.chatbox.viewModel

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.chatbox.view.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel {
    fun getConnection(email: String, password: String, activity: Activity) {
        val mAuth = FirebaseAuth.getInstance()

        // logic for logging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.finish()
                    activity.startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(activity, "L'utilisateur n'existe pas", Toast.LENGTH_LONG).show()
                }
            }
    }

}