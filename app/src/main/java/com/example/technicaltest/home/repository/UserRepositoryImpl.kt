package com.example.technicaltest.home.repository

import com.example.technicaltest.utils.FirebaseConstants.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): IUserRepository {
    override suspend fun getUserInfo(): Result<String> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Result.failure(NullPointerException())
            val userDocument = firebaseFirestore
                .collection(USER_COLLECTION)
                .document(userId)
                .get()
                .await()
            if (userDocument.exists()) {

                Result.success("${userDocument.getString("name")} ${userDocument.getString("lastName")}")
            } else {
                Result.failure(NullPointerException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}