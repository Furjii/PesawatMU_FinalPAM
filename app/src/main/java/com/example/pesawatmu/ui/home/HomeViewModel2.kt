package com.example.p.ui.home

import HomeUIState2
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Penumpangmu.data.PenumpangRepositori
import com.example.pesawatmu.model.Penumpang
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class PenumpangUIState {
    data class Success(val penumpang: Flow<List<Penumpang>>) : PenumpangUIState()
    object Error : PenumpangUIState()
    object Loading : PenumpangUIState()
}

class HomeViewModel2(
    private val penumpangRepositori: PenumpangRepositori
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


    val homeUIStatePN: StateFlow<HomeUIState2> = penumpangRepositori.getAll()
        .filterNotNull()
        .map {
            HomeUIState2(listPenumpang = it.toList(), it.size)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState2()
        )
}

