package com.example.mypetproject.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mypetproject.data.User
import com.example.mypetproject.model.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mFirebaseRepository: FirebaseRepository) :
    ViewModel() {

    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}