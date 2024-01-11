package com.example.pesawatmu

import android.app.Application
import com.example.firebasepraktikum.data.AppContainer
import com.example.firebasepraktikum.data.AppContainers

class PesawatMU : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AppContainers()
    }
}