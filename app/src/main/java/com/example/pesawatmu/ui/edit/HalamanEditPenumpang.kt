package com.example.pesawatmu.ui.edit

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
import com.example.Penumpangmu.ui.add.AddBodyPenumpang
import com.example.contactapp_with_firebase.ui.AppTopAppBar
import com.example.contactapp_with_firebase.ui.PenyediaViewModel
import com.example.contactapp_with_firebase.ui.edit.EditViewModel
import com.example.contactapp_with_firebase.ui.edit.EditViewModel2
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R
import kotlinx.coroutines.launch

object EditDestinasiPenumpang : DestinasiNavigasi {
    override val route = "itemeditdua"
    override val titleRes ="Edit penumpang"
    const val penumpangId = "penumpangId"
    val routeWithArgs = "$route/{$penumpangId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenPenumpang(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel2 = viewModel(factory = PenyediaViewModel.Factory)

) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = EditDestinasiPenumpang.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.datapn),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
        AddBodyPenumpang(
            addUIState2 = viewModel.penumpangUiState,
            onValueChange2 = viewModel::updateUIState2,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatepenumpang()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
        )
    }
}