package com.example.pesawatmu.ui.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactapp_with_firebase.ui.AddPlane
import com.example.contactapp_with_firebase.ui.AddUIState
import com.example.contactapp_with_firebase.ui.AppTopAppBar
import com.example.contactapp_with_firebase.ui.PenyediaViewModel
import com.example.contactapp_with_firebase.ui.add.AddViewModel
import com.example.contactapp_with_firebase.ui.add.AddViewModel2
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R
import kotlinx.coroutines.launch


object DestinasiAddpesawat : DestinasiNavigasi {
    override val route = "entrypesawat"
    override val titleRes = "Data Penerbangan"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenPesawat(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory),
    pilihanM: List<String>

) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        AppTopAppBar(
            title = DestinasiAddpesawat.titleRes,
            canNavigateBack = true,
            scrollBehavior = scrollBehavior,
            navigateUp = navigateBack
        )
    }) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.dataps),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )

        AddBodyPesawat(
            addUIState = addViewModel.addUIState,
            onValueChange = addViewModel::updateAddUIState,
            onSaveClick = {
                coroutineScope.launch {
                    addViewModel.addPesawat()
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

@Composable
fun AddBodyPesawat(
    addUIState: AddUIState,
    onValueChange: (AddPlane) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    pilihanPesawat: List<String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        AddFormPesawat(
            addPlane = addUIState.addPlane,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            pilihanPesawat = pilihanPesawat
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormPesawat(
    addPlane: AddPlane,
    modifier: Modifier = Modifier,
    onValueChange: (AddPlane) -> Unit = {},
    pilihanPesawat: List<String>,
    enabled: Boolean = true
) {
    var pilihanMas by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Pilih Maskapai Penerbangan :")
        pilihanPesawat.forEach { item ->
            Row(modifier = Modifier.selectable(
                selected = pilihanMas == item,
                onClick = {
                    pilihanMas = item
                    onValueChange(addPlane.copy(nama = item))
                }
            ),
                verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = pilihanMas == item, onClick = {
                    pilihanMas = item
                    onValueChange(addPlane.copy(nama = item))
                })
                Text(item)
            }
        }

        OutlinedTextField(value = addPlane.rute,
            onValueChange = { onValueChange(addPlane.copy(rute = it)) },
            label = { Text("Rute Penerbangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addPlane.jadwal,
            onValueChange = { onValueChange(addPlane.copy(jadwal = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Jadwal Penerbangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}
