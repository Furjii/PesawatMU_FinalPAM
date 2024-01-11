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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
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
import com.example.firebasepraktikum.navigation.DestinasiNavigasi
import com.example.p.ui.home.HomeViewModel2
import com.example.pesawatmu.R
import com.example.pesawatmu.model.Penumpang


object DestinasiHomePenumpang : DestinasiNavigasi {
    override val route = "homedua"
    override val titleRes = "penumpang"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen2(
    navigateToItemAdd2: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel2 = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopAppBar(
                title = "Data Diri Penumpang",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemAdd2,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStatePenumpang by viewModel.homeUIStatePN.collectAsState()
        Image(
            painter = painterResource(id = R.drawable.datapn),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight,
        )
        BodyHome2(
            itemPenumpang = uiStatePenumpang.listPenumpang,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onItemClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome2(
    itemPenumpang: List<Penumpang>,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemPenumpang.isEmpty()) {
            Text(
                text = "Tidak ada data Penumpang",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPenumpang(
                itemPenumpang = itemPenumpang,
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                onItemClick = { onItemClick(it.nik) }
            )
        }
    }
}

@Composable
fun ListPenumpang(
    itemPenumpang: List<Penumpang>,
    modifier: Modifier = Modifier,
    onItemClick: (Penumpang) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemPenumpang, key = {it.nik}) {penumpang ->
            DataPenumpang(
                penumpang = penumpang,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {onItemClick(penumpang)}
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}


@Composable
fun DataPenumpang(
    penumpang: Penumpang,
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

            Spacer(Modifier.weight(1f))
            Text(
                text = penumpang.nama_penumpang,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = penumpang.umur_penumpang,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = penumpang.jenis_kelamin,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}