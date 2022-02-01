package com.example.chatbox.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chatbox.R
import com.example.chatbox.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var nameEdt: EditText
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpBtn: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        nameEdt = findViewById(R.id.nameEdt)
        emailEdt = findViewById(R.id.emailEdt)
        passwordEdt = findViewById(R.id.passwordEdt)
        signUpBtn = findViewById(R.id.signupButton)

        signUpBtn.setOnClickListener {
            val name = nameEdt.text.toString()
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String){
        // logic or creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDb(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "erreur sur la création de compte", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun addUserToDb(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}