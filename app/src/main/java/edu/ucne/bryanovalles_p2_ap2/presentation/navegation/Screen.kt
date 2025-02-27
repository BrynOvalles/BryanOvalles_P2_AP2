package edu.ucne.bryanovalles_p2_ap2.presentation.navegation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object PantallaInicio : Screen()
    @Serializable
    data class Deposito(val sistemaId: Int) : Screen()
}