package com.example.mypetproject

import com.example.mypetproject.data.Details.MoviesDetails
import com.example.mypetproject.data.User
import com.example.mypetproject.model.repository.FirebaseRepository
import com.example.mypetproject.room.RoomRepositoryImpl
import com.example.mypetproject.room.ToDoDao
import com.example.mypetproject.viewmodel.MainActivityViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainActivityViewModelTest {

    private lateinit var subject: MainActivityViewModel
    private val mFirebaseRepositoryMock: FirebaseRepository = mock()

    private val firebaseUserMock: User = User("emailTest", "uidTest")
    private val uidMock: String  = "0"

        @Before
        fun setup() {
            subject = MainActivityViewModel(mFirebaseRepositoryMock)
        }

        @Test
        fun updateUserData_success() {
            subject.updateUserData(firebaseUserMock, uidMock)
            Mockito.verify(mFirebaseRepositoryMock).updateUserData(firebaseUserMock, uidMock)
        }
}