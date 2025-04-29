package com.example.compose_praticle_demo.modules.onboarding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.compose_praticle_demo.modules.onboarding.viewmodel.OnboardingViewmodel
import com.example.compose_praticle_demo.modules.splash.viewmodel.SplashViewModel
import com.example.compose_praticle_demo.navigations.NAV_CONFIRM_PHONE_SCREEN_PREVIEW

@Composable
fun OnboardingPage(
    navController: NavController?,
    viewModel: OnboardingViewmodel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .clickable(){
                navController?.navigate(NAV_CONFIRM_PHONE_SCREEN_PREVIEW)

            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Go to Confirm Phone Number Screen")
//        Text(text = "ON BOARDING SCREEN")
    }
}