package com.example.mypetproject.viewmodel


import com.example.mypetproject.data.User
import com.example.mypetproject.model.repository.FirebaseRepository
import com.example.mypetproject.model.repository.FirebaseRepositoryImpl


class MainActivityViewModel {

    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()
    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}