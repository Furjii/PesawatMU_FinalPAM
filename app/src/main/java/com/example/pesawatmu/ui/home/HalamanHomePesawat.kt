package com.example.contactapp_with_firebase.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactapp_with_firebase.ui.AppTopAppBar
import com.example.contactapp_with_firebase.ui.PenyediaViewModel
import com.example.firebasepraktikum.model.Pesawat
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.pesawatmu.R


object DestinasiHomePesawat : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Pesawat"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemAdd: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopAppBar(
                title = "Data Penerbangan Pesawat",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemAdd,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->

        val uiStatePesawat by viewModel.homeUIStatePS.collectAsState()

        Image(
            painter = painterResource(id = R.drawable.dataps),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
        BodyHome(
            itempesawat = uiStatePesawat.listPesawat,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onItemClick = onDetailClick
        )
    }

}

@Composable
fun BodyHome(
    itempesawat: List<Pesawat>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
    ) {
        if (itempesawat.isEmpty()) {
            Text(
                text = "Tidak ada data Penerbangan",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPesawat(
                itempesawat = itempesawat,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onItemClick(it.id_penerbangan) }
            )
        }
    }
}

@Composable
fun ListPesawat(
    itempesawat: List<Pesawat>,
    modifier: Modifier = Modifier,
    onItemClick: (Pesawat) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itempesawat, key = { it.id_penerbangan }) { Pesawat ->
                DataPesawat(
                pesawat = Pesawat,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(Pesawat) })
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataPesawat(
    pesawat: Pesawat,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = pesawat.nama_maskapai,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = pesawat.rute_penerbangan,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = pesawat.jadwal_penerbangan, style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}