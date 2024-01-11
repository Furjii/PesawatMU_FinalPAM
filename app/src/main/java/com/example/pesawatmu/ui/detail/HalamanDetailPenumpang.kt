package com.example.Penumpangmu.ui.detail


import DetailUIState2
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactapp_with_firebase.ui.AppTopAppBar
import com.example.contactapp_with_firebase.ui.PenyediaViewModel
import com.example.contactapp_with_firebase.ui.detail.DetailViewModel2
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R
import com.example.pesawatmu.model.Penumpang
import kotlinx.coroutines.launch
import toPenumpang

object DetailDestinasiPenumpang : DestinasiNavigasi {
    override val route = "penumpangdetail"
    override val titleRes = "Detail penumpang"
    const val penumpangId = "penumpangId"
    val routeWithArgs = "$route/{$penumpangId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPenumpangScreen(
    navigateToEditItem: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel2 = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState2 = viewModel.uiState2.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = DetailDestinasiPenumpang.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack

            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = { navigateToEditItem(uiState2.value.addBoarding.nik) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
                ) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "")

            }
        }, modifier = modifier
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.datapn),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
        ItemDetailsBody2(
            detailUIState2 = uiState2.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deletePenumpang()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        )
    }
}

@Composable
private fun ItemDetailsBody2(
    detailUIState2: DetailUIState2,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails2(
            penumpang = detailUIState2.addBoarding.toPenumpang(), // Changed here
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog2(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}


@Composable
fun ItemDetails2(
    penumpang: Penumpang, modifier: Modifier = Modifier,

    ) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemDetailsRow2(
                labelResID = "nama",
                itemDetail = penumpang.nama_penumpang,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow2(
                labelResID = "jenis kelamin",
                itemDetail = penumpang.jenis_kelamin,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow2(
                labelResID = "umur",
                itemDetail = penumpang.umur_penumpang,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
        }

    }
}

@Composable
private fun ItemDetailsRow2(
    labelResID: String, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = labelResID, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog2(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Are you sure") },
        text = { Text("Delete") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
