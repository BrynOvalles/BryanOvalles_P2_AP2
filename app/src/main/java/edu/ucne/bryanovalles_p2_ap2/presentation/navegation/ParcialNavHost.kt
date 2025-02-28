package edu.ucne.bryanovalles_p2_ap2.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.bryanovalles_p2_ap2.presentation.entidad.DepositoListScreen
import edu.ucne.bryanovalles_p2_ap2.presentation.entidad.DepositoScreen

@Composable
fun ParcialNavHost(
    navHostController: NavHostController
){
    NavHost (
        navController = navHostController,
        startDestination = Screen.PantallaInicio
    ) {
        composable<Screen.PantallaInicio> {
            DepositoListScreen(
                createDeposito = {
                    navHostController.navigate(Screen.Deposito(0))
                },
                goDeposito = {depositoId ->
                    navHostController.navigate(Screen.Deposito(depositoId= depositoId))
                }
            )
        }
        composable<Screen.Deposito> {arg->
            val depositoId = arg.toRoute<Screen.Deposito>().depositoId
            DepositoScreen(
                depositoId = depositoId,
                goDepositoList = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}