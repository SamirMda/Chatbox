package com.example.chatbox.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chatbox.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        emailEdt = findViewById(R.id.emailEdt)
        passwordEdt = findViewById(R.id.passwordEdt)
        loginBtn = findViewById(R.id.loginButton)
        signUpBtn = findViewById(R.id.signupButton)

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        // lofic for logging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "L'utilisateur n'existe pas", Toast.LENGTH_LONG).show()
                }
            }
    }
}