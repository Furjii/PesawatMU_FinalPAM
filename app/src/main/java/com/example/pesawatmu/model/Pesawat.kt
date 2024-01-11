package com.example.firebasepraktikum.model

data class Pesawat(
    val id_penerbangan: String,
    val nama_maskapai: String,
    val rute_penerbangan: String,
    val jadwal_penerbangan: String
) {
    constructor() : this("","","","")
}
