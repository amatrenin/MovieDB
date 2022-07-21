package com.example.mypetproject.model.repository


import com.example.mypetproject.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {

    private var database: DatabaseReference = Firebase.database.reference

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser)
    }
}