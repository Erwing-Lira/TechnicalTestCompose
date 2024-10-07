package com.example.technicaltest.home.repository

import com.example.technicaltest.home.repository.model.CardResponse
import com.example.technicaltest.utils.FirebaseConstants.CARDS_COLLECTION
import com.example.technicaltest.utils.FirebaseConstants.USER_COLLECTION
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): ICardsRepository {
    override suspend fun getFirstCard(): Result<CardResponse> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Result.failure(NullPointerException())
            val cardSnapshot = firebaseFirestore
                .collection(USER_COLLECTION)
                .document(userId)
                .collection(CARDS_COLLECTION)
                .get()
                .await()

            if (cardSnapshot.documents.isNotEmpty()) {
                val firstDocument = cardSnapshot.documents[0]
                val card = CardResponse(
                    id = firstDocument.id,
                    cvv = firstDocument["cvv"] as String,
                    number = firstDocument["number"] as String,
                    expiresDate = (firstDocument["expiresDate"] as Timestamp).toDate()
                )
                Result.success(card)
            } else {
                Result.failure(NullPointerException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}