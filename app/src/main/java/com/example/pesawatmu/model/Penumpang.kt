package com.example.pesawatmu.model

import androidx.room.PrimaryKey


data class Penumpang(
    var nik: String,
    var nama_penumpang: String,
    var jenis_kelamin: String,
    var umur_penumpang: String
) {
    constructor() : this("", "", "", "")
}
