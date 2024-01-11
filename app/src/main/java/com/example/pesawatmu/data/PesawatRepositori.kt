package com.example.firebasepraktikum.data

import android.content.ContentValues
import android.util.Log
import com.example.firebasepraktikum.model.Pesawat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface PesawatRepositori {
    fun getAll(): Flow<List<Pesawat>>
    suspend fun save(pesawat: Pesawat): String
    suspend fun update(pesawat: Pesawat)
    suspend fun delete(pesawatId: String)
    fun getPesawatById(pesawatId: String): Flow<Pesawat>
}

class PesawatRepositoriImpl(private val firestore: FirebaseFirestore) : PesawatRepositori {
    override fun getAll(): Flow<List<Pesawat>> = flow {
        val snapshot = firestore.collection("Pesawat")
            .orderBy("nama_maskapai", Query.Direction.ASCENDING)
            .get()
            .await()
        val pesawat = snapshot.toObjects(Pesawat::class.java)
        emit(pesawat)
    }.flowOn((Dispatchers.IO))

    override suspend fun save(pesawat: Pesawat): String {
        return try {
            val documentReference = firestore.collection("Pesawat").add(pesawat).await()

            firestore.collection("Pesawat").document(documentReference.id)
                .set(pesawat.copy(id_penerbangan = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(pesawat: Pesawat) {
        firestore.collection("Pesawat").document(pesawat.id_penerbangan).set(pesawat).await()

    }

    override suspend fun delete(pesawatId: String) {
        firestore.collection("Pesawat").document(pesawatId).delete().await()
    }

    override fun getPesawatById(pesawatId: String): Flow<Pesawat> {
        return flow {
            val snapshot = firestore.collection("Pesawat").document(pesawatId).get().await()
            val pesawat = snapshot.toObject(Pesawat::class.java)
            emit(pesawat!!)
        }.flowOn(Dispatchers.IO)

    }

}