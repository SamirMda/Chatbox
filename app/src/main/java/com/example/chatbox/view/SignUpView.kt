package com.example.chatbox.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

            signUpViewModel.signUp(name, email, password, this)
        }
    }

}