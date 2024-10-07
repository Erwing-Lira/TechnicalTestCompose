package com.example.technicaltest.signup.repository

import android.net.Uri
import com.example.technicaltest.signup.repository.model.UserData
import com.example.technicaltest.signup.util.getFakeCard
import com.example.technicaltest.signup.util.getMovementsList
import com.example.technicaltest.utils.FirebaseConstants.CARDS_COLLECTION
import com.example.technicaltest.utils.FirebaseConstants.MOVEMENTS_COLLECTION
import com.example.technicaltest.utils.FirebaseConstants.USER_COLLECTION
import com.example.technicaltest.utils.FirebaseStorageConstants.PHOTO_NAME
import com.example.technicaltest.utils.FirebaseStorageConstants.USER_DIRECTORY
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

            val storageRef = firebaseStorage.reference
                .child("$USER_DIRECTORY$userId$PHOTO_NAME")
            storageRef.putFile(photoUri).await()
            val photoUrl = storageRef.downloadUrl.await().toString()

            val userData = UserData(
                name = name,
                lastName = lastName,
                photoUrl = photoUrl
            )
            firebaseFirestore
                .collection(USER_COLLECTION)
                .document(userId)
                .set(userData)
                .await()

            registryFakeData(firebaseFirestore, userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private fun registryFakeData(
    firebaseFirestore: FirebaseFirestore,
    userId: String
) {
    firebaseFirestore
        .collection(USER_COLLECTION)
        .document(userId)
        .collection(CARDS_COLLECTION)
        .add(getFakeCard)
        .addOnSuccessListener { task ->
            val cardId = task.id
            getMovementsList.forEach { movement ->
                val movementsMap = hashMapOf(
                    "reference" to movement.reference,
                    "destination" to movement.destination,
                    "operationDate" to movement.operationDate,
                    "movementType" to movement.movementType,
                    "concept" to movement.concept,
                    "money" to movement.money
                )
                firebaseFirestore
                    .collection(USER_COLLECTION)
                    .document(userId)
                    .collection(CARDS_COLLECTION)
                    .document(cardId)
                    .collection(MOVEMENTS_COLLECTION)
                    .add(movementsMap)
            }
        }


}



