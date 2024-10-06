package com.example.technicaltest.home.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogOutRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): ILogOutRepository {
    override fun logOut() {
        firebaseAuth.signOut()
    }
}