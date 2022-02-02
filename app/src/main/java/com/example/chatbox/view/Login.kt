package com.example.chatbox.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chatbox.R
import com.example.chatbox.viewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        // get view element
        emailEdt = findViewById(R.id.emailEdt)
        passwordEdt = findViewById(R.id.passwordEdt)
        loginBtn = findViewById(R.id.loginButton)
        signUpBtn = findViewById(R.id.signupButton)

        // redirect to the sign up page on click
        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // connect on click
        loginBtn.setOnClickListener {
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()
            val loginViewModel = LoginViewModel()

            loginViewModel.getConnection(email, password, this)
        }
    }

}