package edu.ucne.bryanovalles_p2_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.ucne.bryanovalles_p2_ap2.presentation.navegation.ParcialNavHost
import edu.ucne.bryanovalles_p2_ap2.ui.theme.BryanOvalles_P2_AP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BryanOvalles_P2_AP2Theme {
                val navHost = rememberNavController()
                ParcialNavHost(navHost)
            }
        }
    }
}