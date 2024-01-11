package com.example.contactapp_with_firebase.ui.detail

import DetailUIState2
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.Penumpangmu.ui.detail.DetailDestinasiPenumpang
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import toDetailPenumpang

class DetailViewModel2(
    savedStateHandle: SavedStateHandle,
    private val penumpangRepositori: PenumpangRepositori
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val penumpangId: String = checkNotNull(savedStateHandle[DetailDestinasiPenumpang.penumpangId])


    val uiState2: StateFlow<DetailUIState2> =
        penumpangRepositori.getPenumpangById(penumpangId)
            .filterNotNull()
            .map {
                DetailUIState2(addBoarding = it.toDetailPenumpang())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState2()
            )
    suspend fun deletePenumpang() {
        penumpangRepositori.delete(penumpangId)
    }
}

