package com.example.contactapp_with_firebase.ui.edit

import AddBoarding
import AddUIState2
import android.media.Image.Plane
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp_with_firebase.ui.AddPlane
import com.example.contactapp_with_firebase.ui.AddUIState
import com.example.contactapp_with_firebase.ui.toPesawat
import com.example.contactapp_with_firebase.ui.toUIStatePesawat
import com.example.firebasepraktikum.data.PesawatRepositori
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val pesawatRepositori: PesawatRepositori,
) : ViewModel() {

    var pesawatUiState by mutableStateOf(AddUIState())
        private set


    private val pesawatId: String =
        checkNotNull(savedStateHandle[EditDestinasiPesawat.pesawatId])


    init {
        viewModelScope.launch {
            pesawatUiState =
                pesawatRepositori.getPesawatById(pesawatId)
                    .filterNotNull()
                    .first()
                    .toUIStatePesawat()
        }
    }


    fun updateUIState(addPlane: AddPlane) {
        pesawatUiState = pesawatUiState.copy(addPlane = addPlane)
    }

    suspend fun updatepesawat() {
        pesawatRepositori.update(pesawatUiState.addPlane.toPesawat())

    }
}