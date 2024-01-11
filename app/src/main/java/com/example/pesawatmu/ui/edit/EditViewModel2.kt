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
import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.pesawatmu.ui.edit.EditDestinasiPenumpang

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import toPenumpang
import toUIStatePenumpang

class EditViewModel2(
    savedStateHandle: SavedStateHandle,
    private val penumpangRepositori: PenumpangRepositori,
) : ViewModel() {

    var penumpangUiState by mutableStateOf(AddUIState2())
        private set

    private val penumpangId: String =
        checkNotNull(savedStateHandle[EditDestinasiPenumpang.penumpangId])

    init {
        viewModelScope.launch {
            penumpangUiState =
                penumpangRepositori.getPenumpangById(penumpangId)
                    .filterNotNull()
                    .first()
                    .toUIStatePenumpang()
        }
    }

    fun updateUIState2(addBoarding: AddBoarding) {
        penumpangUiState = penumpangUiState.copy(addBoarding = addBoarding)
    }

    suspend fun updatepenumpang() {
        penumpangRepositori.update(penumpangUiState.addBoarding.toPenumpang())

    }
}