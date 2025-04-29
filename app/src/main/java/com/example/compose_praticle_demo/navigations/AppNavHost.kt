package com.example.compose_praticle_demo.navigations

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_praticle_demo.modules.splash.ui.SplashScreenView
import com.example.compose_praticle_demo.modules.splash.viewmodel.SplashViewModel


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Destination = Destination.SplashScreen,
    @SuppressLint("ContextCastToActivity") activity: ComponentActivity = LocalContext.current as ComponentActivity,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.fullRoute,
    ) {
        composable(Destination.SplashScreen.fullRoute) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreenView(
                navController,
                viewModel = viewModel,
            )
        }

    }
}