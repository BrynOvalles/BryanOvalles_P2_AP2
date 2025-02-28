package edu.ucne.bryanovalles_p2_ap2.presentation.entidad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.bryanovalles_p2_ap2.presentation.componentes.CustomDatePicker
import edu.ucne.bryanovalles_p2_ap2.presentation.componentes.TopBar

@Composable
fun DepositoScreen(
    viewModel: DepositoViewModel = hiltViewModel(),
    depositoId: Int,
    goDepositoList: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DepositoBodyScreen(
        depositoId,
        viewModel,
        uiState,
        goDepositoList
    )
}

@Composable
fun DepositoBodyScreen(
    depositoId: Int,
    viewModel: DepositoViewModel,
    uiState: DepositoUiState,
    goDepositoList: () -> Unit
) {
    LaunchedEffect(depositoId) {
        if (depositoId > 0) viewModel.find(depositoId)
    }
    Scaffold(
        topBar = {
            TopBar(if (depositoId > 0) "Editar Deposito" else "Registrar Deposito")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Concepto") },
                        value = uiState.concepto,
                        onValueChange = viewModel::onConceptoChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                    CustomDatePicker(
                        label = "Fecha",
                        selectedDate = uiState.fecha,
                        onDateSelected = { date -> viewModel.onFechaChange(date) }
                    )
                    OutlinedTextField(
                        label = { Text(text = "Monto") },
                        value = uiState.monto,
                        onValueChange = viewModel::onMontoChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        label = { Text(text = "Cuenta") },
                        value = uiState.idCuenta.toString(),
                        onValueChange = viewModel::onCuentaChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    uiState.errorMessage?.let {
                        Text(text = it, color = MaterialTheme.colorScheme.error)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(onClick = {
                            if (depositoId > 0) {
                                viewModel.delete(id = depositoId)
                                goDepositoList()
                            } else {
                                viewModel.new()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = if (depositoId > 0) "Borrar" else "Limpiar"
                            )
                            Text(text = if (depositoId > 0) "Borrar" else "Limpiar")
                        }
                        OutlinedButton(
                            onClick = {
                                if (viewModel.isValid()) {
                                    if (depositoId > 0) viewModel.update() else viewModel.save()
                                    goDepositoList()
                                }
                            }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Save button"
                            )
                            Text(text = "Guardar")
                        }
                    }
                }
            }
        }
    }
}