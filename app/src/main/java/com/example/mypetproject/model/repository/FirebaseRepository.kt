package com.example.mypetproject.model.repository

import com.example.mypetproject.data.User


interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}