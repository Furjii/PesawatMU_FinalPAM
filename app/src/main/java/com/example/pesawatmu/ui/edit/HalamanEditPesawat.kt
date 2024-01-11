package com.example.contactapp_with_firebase.ui.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactapp_with_firebase.ui.AppTopAppBar
import com.example.contactapp_with_firebase.ui.PenyediaViewModel
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R
import com.example.pesawatmu.ui.add.AddBodyPesawat
import kotlinx.coroutines.launch

object EditDestinasiPesawat : DestinasiNavigasi {
    override val route = "itemedit"
    override val titleRes ="Edit Pesawat"
    const val pesawatId = "pesawatId"
    val routeWithArgs = "$route/{$pesawatId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenPesawat(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory),
    pilihanM: List<String>
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = EditDestinasiPesawat.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.dataps),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
        AddBodyPesawat(
            addUIState = viewModel.pesawatUiState,
            onValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatepesawat()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            pilihanPesawat = pilihanM
        )
    }
}