package edu.ucne.bryanovalles_p2_ap2.presentation.componentes

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    titulo: String,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Cyan
            )
        }
    )
}