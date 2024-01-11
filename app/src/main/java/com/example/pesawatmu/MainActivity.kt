package com.example.pesawatmu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.contactapp_with_firebase.ui.PesawatApp
import com.example.contactapp_with_firebase.ui.theme.PesawatTheme
import com.example.pesawatmu.navigation.PengelolaHalaman
import com.example.pesawatmu.ui.home.StartScreen
import com.google.firebase.Firebase
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        setContent {
            PesawatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PesawatApp()
                }
            }
        }
    }
}