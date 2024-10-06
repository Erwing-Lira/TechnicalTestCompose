package com.example.technicaltest.home.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegistryCardRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): IRegistryCardRepository {
    override suspend fun registryCard(): Result<Unit> {
        val card = Card(
            number = "1234 1234 1234",
            expiresDate = "10/10",
            cvv = "1234"
        )
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Result.failure(NullPointerException())
            firebaseFirestore.collection("users").document(userId)
                .collection("cards")
                .add(card)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

data class Card(
    val number: String,
    val expiresDate: String,
    val cvv: String
)