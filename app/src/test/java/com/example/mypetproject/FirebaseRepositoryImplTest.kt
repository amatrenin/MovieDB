//package com.example.mypetproject
//
//import com.example.mypetproject.data.User
//import com.example.mypetproject.model.repository.FirebaseRepository
//import com.example.mypetproject.model.repository.FirebaseRepositoryImpl
//import com.example.mypetproject.viewmodel.MainActivityViewModel
//import com.google.firebase.database.DatabaseReference
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mockito
//import org.mockito.kotlin.mock
//
//class FirebaseRepositoryImplTest {
//
//    private lateinit var subject: FirebaseRepositoryImpl
//    private val mDatabaseReferenceMock: DatabaseReference = mock()
//
//    private val firebaseUserMock: User = User("emailTest", "uidTest")
//    private val uidMock: String = "0"
//    private val users: String = "users"
//
//    @Before
//    fun setup() {
//        subject = FirebaseRepositoryImpl(mDatabaseReferenceMock)
//    }
//
//    @Test
//    fun updateUserData() {
//        subject.updateUserData(firebaseUserMock, uidMock)
//        Mockito.verify(mDatabaseReferenceMock)
//            .child("users")
//            .child("0")
//            .setValue(firebaseUserMock)
//
//    }
//}


