package edu.ucne.bryanovalles_p2_ap2.presentation.entidad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.bryanovalles_p2_ap2.data.local.entity.DepositoEntity

@Composable
fun DepositoListScreen(
    viewModel: DepositoViewModel = hiltViewModel(),
    createDeposito: () -> Unit,
    goDeposito: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DepositoListBodyScreen(
        uiState,
        createDeposito,
        goDeposito,
        viewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositoListBodyScreen(
    uiState: DepositoUiState,
    createDeposito: () -> Unit,
    goDeposito: (Int) -> Unit,
    viewModel: DepositoViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title= {
                    Text("Listado de Depositoes",
                        color = Color.Cyan)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { createDeposito() }) {
                Icon(Icons.Filled.Add, contentDescription = "Crear Deposito")
            }
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Spacer (modifier = Modifier.height(42.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.getDepositos()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                    Text(text = "Recargar")
                }
            }
            Cabecera()
            Spacer(modifier = Modifier.height(32.dp))
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            uiState.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(uiState.depositos) {
                    DepositoRow(it, goDeposito)
                }
            }
        }
    }
}

@Composable
private fun Cabecera() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier.weight(0.2f),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
            text = "Concepto",
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.weight(0.2f),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraLight),
            text = "Monto",
            textAlign = TextAlign.Start
        )
    }
    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
}

@Composable
private fun DepositoRow(
    it: DepositoEntity,
    goDeposito: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                it.idDeposito?.let { eso -> goDeposito(eso) }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(2.5f),
                text = it.concepto,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.weight(1.5f),
                text = "${it.monto}",
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}