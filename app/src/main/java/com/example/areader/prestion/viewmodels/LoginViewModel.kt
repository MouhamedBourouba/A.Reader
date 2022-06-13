package com.example.areader.prestion.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.model.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

     val isLoading = mutableStateOf(false)

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance()

    val isUserAlreadyHaveAccount = {
        auth.currentUser != null
    }

    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (e: String?) -> Unit
    ) = viewModelScope.launch {


        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailed(it.message)
            }

    }



    fun createUser(
        userInfo: UserInfo,
        password: String,
        onSuccess: () -> Unit,
        onFailed: (e: String?) -> Unit
    ) = viewModelScope.launch {

        auth.createUserWithEmailAndPassword(userInfo.email, password)
            .addOnSuccessListener {
                uploadUserName(
                    userInfo,
                    it.user ?: return@addOnSuccessListener,
                    onSuccess = {
                        onSuccess.invoke()
                    },
                    onFailed = { message ->
                        onFailed(message)
                    }
                )
            }
            .addOnFailureListener {
                onFailed(it.message)
            }

    }

    private fun uploadUserName(
        user: UserInfo,
        uid: FirebaseUser,
        onSuccess: () -> Unit,
        onFailed: (message: String?) -> Unit
    ) {
        val ref = db.reference.child("users").child(uid.uid)
        ref.setValue(user)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailed(it.message)
            }
    }
}