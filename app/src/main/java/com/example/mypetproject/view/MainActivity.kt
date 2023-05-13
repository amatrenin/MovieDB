package com.example.mypetproject.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mypetproject.R
import com.example.mypetproject.data.User
import com.example.mypetproject.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel: MainActivityViewModel by viewModels()

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
    }

    /**
     * We make a call to firebase auth api to show dialog for registration
     */
    private fun openRegistrationScreen() {
        /**
         * in order to make it easier to test new features, remove the comment from the intent below
         */
//        val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
//        startActivity(intentToAnotherScreen)
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val authUser = FirebaseAuth.getInstance().currentUser
                authUser?.let {
                    val email = it.email.toString()
                    val uid = it.uid
                    val firebaseUser = User(email, uid)
                    mMainActivityViewModel.updateUserData(firebaseUser, uid)
                    val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
                    startActivity(intentToAnotherScreen)
                }
            }
            RESULT_CANCELED -> {
                Toast.makeText(
                    this@MainActivity, R.string.introduce_local_variable,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                //do not to anything
            }
        }
    }
}



