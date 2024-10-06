package com.example.technicaltest.signup.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRegistryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
): ISignUpRegistry {
    override suspend fun registry(
        name: String,
        lastName: String,
        email: String,
        password: String,
        photoUri: Uri
    ): Result<Unit> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await()
            val userId = authResult.user?.uid ?: return Result.failure(Exception())

            val storageRef = firebaseStorage.reference.child("user_photos/$userId/credential.jpg")
            storageRef.putFile(photoUri).await()
            val photoUrl = storageRef.downloadUrl.await().toString()

            val userData = hashMapOf(
                "name" to name,
                "lastName" to lastName,
                "photoUrl" to photoUrl
            )
            firebaseFirestore.collection("users").document(userId)
                .set(userData).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}