package com.example.Penumpangmu.data

import android.content.ContentValues
import android.util.Log
import com.example.pesawatmu.model.Penumpang
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


interface PenumpangRepositori {
    fun getAll(): Flow<List<Penumpang>>
    suspend fun save(penumpang: Penumpang): String
    suspend fun update(penumpang: Penumpang)
    suspend fun delete(PenumpangId: String)
    fun getPenumpangById(PenumpangId: String): Flow<Penumpang>
}

class PenumpangRepositoriImpl(private val firestore: FirebaseFirestore) : PenumpangRepositori {
    override fun getAll(): Flow<List<Penumpang>> = flow {
        val snapshot = firestore.collection("Penumpang")
            .orderBy("nama_penumpang", Query.Direction.ASCENDING)
            .get()
            .await()
        val penumpang = snapshot.toObjects(Penumpang::class.java)
        emit(penumpang)
    }.flowOn((Dispatchers.IO))

    override suspend fun save(penumpang: Penumpang): String {
        return try {
            val documentReference = firestore.collection("Penumpang").add(penumpang).await()
            firestore.collection("Penumpang").document(documentReference.id)
                .set(penumpang.copy(nik = documentReference.id))
            documentReference.id
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(penumpang: Penumpang) {
        firestore.collection("Penumpang").document(penumpang.nik).set(penumpang).await()

    }

    override suspend fun delete(penumpangId: String) {
        firestore.collection("Penumpang").document(penumpangId).delete().await()
    }

    override fun getPenumpangById(penumpangId: String): Flow<Penumpang> {
        return flow {
            val snapshot = firestore.collection("Penumpang").document(penumpangId).get().await()
            val Penumpang = snapshot.toObject(Penumpang::class.java)
            emit(Penumpang!!)
        }.flowOn(Dispatchers.IO)

    }

}