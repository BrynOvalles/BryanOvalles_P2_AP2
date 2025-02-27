package edu.ucne.bryanovalles_p2_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Deposito")
data class DepositoEntity (
    @PrimaryKey
    val idDeposito: Int? = null,
    val fecha: String = "",
    val idCuenta: Int? = null,
    val concepto: String = "",
    val monto: Int? = null,
)