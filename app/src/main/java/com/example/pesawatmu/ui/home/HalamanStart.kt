package com.example.pesawatmu.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R

object DestinasiStart : DestinasiNavigasi {
    override val route = "start"
    override val titleRes = "start"
}

@Composable
fun StartScreen(
    onNextButtonpesawatClicked: () -> Unit,
    onNextButtonpenumpangClicked: () -> Unit,

    ) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Column( // Added a nested Column for closer text placement
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pesawat MU",
                        color = Color.White,
                        fontSize = 1.sp,
                        fontFamily = FontFamily.Serif, // Menggunakan font family yang berbeda
                        fontWeight = FontWeight.Bold, // Teks tebal untuk menonjolkan judul
                        fontStyle = FontStyle.Italic, // Gaya teks miring untuk variasi
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Terbanglah Bersama kami",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Cursive, // Menggunakan font family cursive untuk variasi yang menarik
                        fontWeight = FontWeight.Normal, // Menggunakan tebal normal untuk tubuh teks
                    )
                }
                Spacer(modifier = Modifier.height(280.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(60.dp)
                            .background(Color.Blue.copy(alpha = 0.4f))
                            .clickable(onClick = onNextButtonpesawatClicked)
                    ) {
                        Text(
                            text = stringResource(R.string.data_pesawat),
                            color = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(60.dp)
                            .background(Color.Blue.copy(alpha = 0.4f))
                            .clickable(onClick = onNextButtonpenumpangClicked)
                    ) {
                        Text(
                            text = stringResource(R.string.data_penumpang),
                            color = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
        }
    }
}