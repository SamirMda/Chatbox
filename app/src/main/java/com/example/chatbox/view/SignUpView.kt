package com.example.chatbox.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.chatbox.R
import com.example.chatbox.database.User
import com.example.chatbox.viewModel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpView : AppCompatActivity() {
    private lateinit var nameEdt: EditText
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // get activity element
        nameEdt = findViewById(R.id.nameEdt)
        emailEdt = findViewById(R.id.emailEdt)
        passwordEdt = findViewById(R.id.passwordEdt)
        signUpBtn = findViewById(R.id.signupButton)

        signUpBtn.setOnClickListener {
            val name = nameEdt.text.toString()
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()
            val signUpViewModel = SignUpViewModel()

            // Pwd and email verification before registration
            if (!signUpViewModel.checkPassword(password)) {
                // Remove password error message if it's visible
                val errorEmailTV = findViewById<TextView>(R.id.errorEmailTV)

                if (errorEmailTV.visibility == View.VISIBLE) {
                    errorEmailTV.visibility = View.GONE
                }

                // Display error message
                val errorPasswordTV = findViewById<TextView>(R.id.errorPasswordTV)
                errorPasswordTV.visibility = View.VISIBLE

                Toast.makeText(this, "Erreur sur la création du mot de passe", Toast.LENGTH_LONG).show()
            }  else if(!signUpViewModel.checkEmail(email)) {
                // Remove password error message if it's visible
                val errorPasswordTV = findViewById<TextView>(R.id.errorPasswordTV)

                if (errorPasswordTV.visibility == View.VISIBLE) {
                    errorPasswordTV.visibility = View.GONE
                }

                // Display error message
                val errorEmailTV = findViewById<TextView>(R.id.errorEmailTV)
                errorEmailTV.visibility = View.VISIBLE

                Toast.makeText(this, "Erreur sur la saisie de l'adresse mail", Toast.LENGTH_LONG)
                    .show()
            }else {
                signUpViewModel.signUp(name, email, password, this)
            }
        }
    }

}