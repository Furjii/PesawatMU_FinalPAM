package com.example.contactapp_with_firebase.ui

import com.example.firebasepraktikum.model.Pesawat


data class AddUIState(
    val addPlane: AddPlane = AddPlane(),
)

data class AddPlane(
    val id_penerbangan: String = "",
    val nama: String = "",
    val rute: String = "",
    val jadwal: String = "",
)

fun AddPlane.toPesawat() = Pesawat(
    id_penerbangan = id_penerbangan,
    nama_maskapai = nama,
    rute_penerbangan = rute,
    jadwal_penerbangan = jadwal
)

data class DetailUIState(
    val addPlane: AddPlane = AddPlane()
)

fun Pesawat.toAddPlane(): AddPlane = AddPlane(
    id_penerbangan = id_penerbangan,
    nama = nama_maskapai,
    rute = rute_penerbangan,
    jadwal = jadwal_penerbangan
)

fun Pesawat.toUIStatePesawat(): AddUIState = AddUIState(
    addPlane = this.toAddPlane(),
    )

data class HomeUIState(
    val listPesawat: List<Pesawat> = listOf(),
    val dataLength: Int = 0
)