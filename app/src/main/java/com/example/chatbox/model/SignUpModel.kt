package com.example.chatbox.model

import com.example.chatbox.database.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpModel {
    fun addUserToDb(name: String, email: String, uid: String) {
        val mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}