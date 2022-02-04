package com.example.chatbox.viewModel

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.chatbox.database.User
import com.example.chatbox.model.SignUpModel
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
                    val signUpModel = SignUpModel()
                    signUpModel.addUserToDb(name, email, mAuth.currentUser?.uid!!)

                    val intent = Intent(activity, MainActivity::class.java)
                    activity.finish()
                    activity.startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(activity, "erreur sur la création de compte", Toast.LENGTH_LONG).show()
                }
            }
    }


    /**
     *     Password rules
     * must contain at least :
     *  - 5 characters
     *  - 1 minuscule
     *  - 1 majuscule
     *  - 1 number
     *  - 1 special character (@+.-_?!$€)
     *
     * Input : password(String)
     * Output : result(Boolean)
     **/
     fun checkPassword(password: String) : Boolean {
        val length = password.length
        val min = Regex(".*[a-z]+.*")
        val maj = Regex(".*[A-Z]+.*")
        val num = Regex(".*[0-9]+.*")
        val specChar = Regex(".*[@+\\.\\-_\\?\\!\\$€]+.*")

        if ((length >= 5) && (min.matches(password)) && (maj.matches(password)) &&
                (num.matches(password)) && (specChar.matches(password))) {
            return true
        }

        return false
    }

    fun checkEmail(email: String): Boolean {
        val rules = Regex(".+@.+\\..+")

        if (rules.matches(email)) return true

        return false
    }

}