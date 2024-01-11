package com.example.Penumpangmu.ui.add

import AddBoarding
import AddUIState2
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactapp_with_firebase.ui.AppTopAppBar
import com.example.contactapp_with_firebase.ui.PenyediaViewModel
import com.example.contactapp_with_firebase.ui.add.AddViewModel2
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R
import com.example.pesawatmu.model.SumberData
import kotlinx.coroutines.launch


object DestinasiAddPenumpang : DestinasiNavigasi {
    override val route = "entrypenumpang"
    override val titleRes = "Data Penumpang"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenPenumpang(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addViewModel2: AddViewModel2 = viewModel(factory = PenyediaViewModel.Factory)
    ) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopAppBar(
                title = DestinasiAddPenumpang.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.datapn),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
        AddBodyPenumpang(
            addUIState2 = addViewModel2.addUIState2,
            onValueChange2 = addViewModel2::updateAddUIState2,
            onSaveClick = {
                coroutineScope.launch {
                    addViewModel2.addPenumpang()
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

@Composable
fun AddBodyPenumpang(
    addUIState2: AddUIState2,
    onValueChange2: (AddBoarding) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        AddFormPenumpang(
            addBoarding = addUIState2.addBoarding,
            onValueChange = onValueChange2,
            modifier = Modifier.fillMaxWidth(),
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
fun AddFormPenumpang(
    addBoarding: AddBoarding,
    modifier: Modifier = Modifier,
    onValueChange: (AddBoarding) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = addBoarding.nama,
            onValueChange = { onValueChange(addBoarding.copy(nama = it)) },
            label = { Text("Nama Penumpang") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addBoarding.jenis_kelamin,
            onValueChange = { onValueChange(addBoarding.copy(jenis_kelamin = it)) },
            label = { Text("Jenis Kelamin") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addBoarding.umur,
            onValueChange = { onValueChange(addBoarding.copy(umur = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = "Umur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}