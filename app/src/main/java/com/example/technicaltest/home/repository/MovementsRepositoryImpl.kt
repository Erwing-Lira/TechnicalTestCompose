package com.example.technicaltest.home.repository

import com.example.technicaltest.home.repository.model.MovementResponse
import com.example.technicaltest.utils.FirebaseConstants.CARDS_COLLECTION
import com.example.technicaltest.utils.FirebaseConstants.MOVEMENTS_COLLECTION
import com.example.technicaltest.utils.FirebaseConstants.USER_COLLECTION
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MovementsRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : IMovementsRepository {
    override suspend fun getMovementsList(cardId: String): Result<List<MovementResponse>> {
        return try {
            val userId =
                firebaseAuth.currentUser?.uid ?: return Result.failure(NullPointerException())
            val movementSnapshot = firebaseFirestore
                .collection(USER_COLLECTION)
                .document(userId)
                .collection(CARDS_COLLECTION)
                .document(cardId)
                .collection(MOVEMENTS_COLLECTION)
                .get()
                .await()
            if (movementSnapshot.documents.isNotEmpty()) {
                val movements = movementSnapshot.documents.mapNotNull { document ->
                    val data = document.data
                    if (data != null) {
                        MovementResponse(
                            id = document.id,
                            concept = data["concept"] as String,
                            destination = data["destination"] as String,
                            movementType = data["movementType"] as String,
                            operationDate = data["operationDate"] as Timestamp,
                            reference = data["reference"] as String,
                            money = data["money"] as Double
                        )
                    } else {
                        null
                    }
                }
                Result.success(movements)
            } else {
                Result.failure(NullPointerException())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}