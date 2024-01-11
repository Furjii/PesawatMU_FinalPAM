package com.example.contactapp_with_firebase.ui.detail

import DetailUIState2
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.contactapp_with_firebase.ui.DetailUIState
import com.example.contactapp_with_firebase.ui.toAddPlane
import com.example.firebasepraktikum.data.PesawatRepositori
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import toDetailPenumpang

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val pesawatRepositori: PesawatRepositori,

) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val penerbanganId: String = checkNotNull(savedStateHandle[DetailDestinasiPesawat.pesawatId])

    val uiState: StateFlow<DetailUIState> =
        pesawatRepositori.getPesawatById(penerbanganId)
            .filterNotNull()
            .map {
                DetailUIState(addPlane = it.toAddPlane())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deletePesawat() {
        pesawatRepositori.delete(penerbanganId)
    }


}

