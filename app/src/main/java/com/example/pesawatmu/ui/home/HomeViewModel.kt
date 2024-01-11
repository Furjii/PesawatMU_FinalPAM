package com.example.contactapp_with_firebase.ui.home

import HomeUIState2
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.contactapp_with_firebase.ui.HomeUIState
import com.example.firebasepraktikum.data.PesawatRepositori
import com.example.firebasepraktikum.model.Pesawat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class PesawatUiState {
    data class Success(val pesawat: Flow<List<Pesawat>>) : PesawatUiState()
    object Error : PesawatUiState()
    object Loading : PesawatUiState()
}

class HomeViewModel(
    private val pesawatRepository: PesawatRepositori,
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIStatePS: StateFlow<HomeUIState> = pesawatRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState(listPesawat = it.toList(), it.size)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()
        )

    init {
        Log.d("DD","$homeUIStatePS")
    }

}

