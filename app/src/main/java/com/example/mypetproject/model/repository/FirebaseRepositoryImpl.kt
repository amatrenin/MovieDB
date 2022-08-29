package com.example.mypetproject.model.repository


import com.example.mypetproject.data.User
import com.google.firebase.database.DatabaseReference

class FirebaseRepositoryImpl(private val mDatabase: DatabaseReference) : FirebaseRepository {

    override fun updateUserData(firebaseUser: User, uid: String) {
        mDatabase.child("users").child(uid).setValue(firebaseUser)
    }
}