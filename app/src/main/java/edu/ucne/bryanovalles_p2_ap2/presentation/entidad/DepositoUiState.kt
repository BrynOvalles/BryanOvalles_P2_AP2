package edu.ucne.bryanovalles_p2_ap2.presentation.entidad

import edu.ucne.bryanovalles_p2_ap2.data.local.entity.DepositoEntity

data class DepositoUiState(
    val depositoId: Int? = null,
    val fecha: String = "",
    val idCuenta: String = "",
    val concepto: String = "",
    val monto: String = "",
    val errorMessage: String? = null,
    val depositos: List<DepositoEntity> = emptyList(),
    val isLoading: Boolean = false,
)