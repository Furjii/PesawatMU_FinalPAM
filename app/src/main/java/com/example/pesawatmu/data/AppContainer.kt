package com.example.firebasepraktikum.data

import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.Penumpangmu.data.PenumpangRepositoriImpl
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val pesawatRepositori: PesawatRepositori
    val penumpangRepositori: PenumpangRepositori
}

class AppContainers : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val pesawatRepositori: PesawatRepositori by lazy {
        PesawatRepositoriImpl(firestore)
    }
    override val penumpangRepositori: PenumpangRepositori by lazy {
        PenumpangRepositoriImpl(firestore)
    }
}

