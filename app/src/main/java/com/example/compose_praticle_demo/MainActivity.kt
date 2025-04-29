package com.example.compose_praticle_demo

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
import com.example.compose_praticle_demo.navigations.AppNavHost
import com.example.compose_praticle_demo.navigations.Destination
import com.example.compose_praticle_demo.ui.theme.Compose_Praticle_demoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_Praticle_demoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OnTimeApp(modifier = Modifier.padding(innerPadding), activity = this)
                }
            }
        }
    }
}

@Composable
fun OnTimeApp(
    activity: MainActivity,
    modifier: Modifier,
) {
    val navController = rememberNavController()
    AppNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.SplashScreen,
        activity = activity
    )
}

