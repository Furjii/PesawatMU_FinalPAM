package com.example.contactapp_with_firebase.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.contactapp_with_firebase.ui.add.AddViewModel
import com.example.contactapp_with_firebase.ui.add.AddViewModel2
import com.example.contactapp_with_firebase.ui.detail.DetailViewModel
import com.example.contactapp_with_firebase.ui.detail.DetailViewModel2
import com.example.contactapp_with_firebase.ui.edit.EditViewModel
import com.example.contactapp_with_firebase.ui.edit.EditViewModel2
import com.example.contactapp_with_firebase.ui.home.HomeViewModel
import com.example.p.ui.home.HomeViewModel2
import com.example.pesawatmu.PesawatMU

fun CreationExtras.aplikasiPesawat(): PesawatMU =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PesawatMU)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(
                aplikasiPesawat().container.pesawatRepositori,
            )
        }

        initializer {
            HomeViewModel(
                aplikasiPesawat().container.pesawatRepositori
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiPesawat().container.pesawatRepositori,
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiPesawat().container.pesawatRepositori,
            )
        }

        initializer {
            AddViewModel2(

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            HomeViewModel2(

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            DetailViewModel2(
                createSavedStateHandle(),

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            EditViewModel2(
                createSavedStateHandle(),
                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            AddViewModel2(

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            HomeViewModel2(

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            DetailViewModel2(
                createSavedStateHandle(),

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            EditViewModel2(
                createSavedStateHandle(),
                aplikasiPesawat().container.penumpangRepositori
            )
        }

    }
}

object PenyediaViewModel2 {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel2(

                aplikasiPesawat().container.penumpangRepositori
            )
        }

        initializer {
            HomeViewModel2(

                aplikasiPesawat().container.penumpangRepositori
            )
        }
        initializer {
            DetailViewModel2(
                createSavedStateHandle(),

                aplikasiPesawat().container.penumpangRepositori
            )
        }
        initializer {
            EditViewModel2(
                createSavedStateHandle(),
                aplikasiPesawat().container.penumpangRepositori
            )
        }


    }
}