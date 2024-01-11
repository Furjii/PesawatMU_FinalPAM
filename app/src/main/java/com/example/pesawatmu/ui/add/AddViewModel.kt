package com.example.contactapp_with_firebase.ui.add

import AddBoarding
import AddUIState2
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.contactapp_with_firebase.ui.AddPlane
import com.example.contactapp_with_firebase.ui.AddUIState
import com.example.contactapp_with_firebase.ui.toPesawat
import com.example.firebasepraktikum.data.PesawatRepositori
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import toPenumpang


class AddViewModel(
    private val pesawatRepositori: PesawatRepositori,

) : ViewModel() {

    var addUIState by mutableStateOf(AddUIState())
        private set


    fun updateAddUIState(addPlane: AddPlane) {
        addUIState = AddUIState(addPlane = addPlane)
    }


    suspend fun addPesawat() {
        pesawatRepositori.save(addUIState.addPlane.toPesawat())
    }

}

class AddViewModel2(
    private val penumpangRepositori: PenumpangRepositori
) : ViewModel() {

    var addUIState2 by mutableStateOf(AddUIState2())
        private set

    fun updateAddUIState2(addBoarding: AddBoarding) {
        addUIState2 = AddUIState2(addBoarding = addBoarding)
    }

    suspend fun addPenumpang() {
        penumpangRepositori.save(addUIState2.addBoarding.toPenumpang())
    }
}
