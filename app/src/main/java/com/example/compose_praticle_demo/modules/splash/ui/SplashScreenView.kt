package com.example.compose_praticle_demo.modules.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.compose_praticle_demo.modules.splash.viewmodel.SplashViewModel

@Composable
fun SplashScreenView(
    navController: NavController?,
    viewModel: SplashViewModel,
) {
//    val language = LocalLanguage.current
//    LaunchedEffect(key1 = true) {
//        delay(3500)
//        handleNavigation(navController, viewModel, language.value, payloadData)
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Splash Screen")
        // Lottie Animation
//        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(if (language.value == PrefKey.AR_CODE) rRaw.united_pharmacy_splash_animation_ar else  rRaw.united_pharmacy_splash_animation))
//        val progress by animateLottieCompositionAsState(
//            composition = composition,
//            iterations = LottieConstants.IterateForever
//        )
//        LottieAnimation(
//            composition = composition,
//            progress = { progress },
//            contentScale = ContentScale.Inside,
//            modifier = Modifier.fillMaxSize()
//        )
    }
}

// handle navigation after splash screen
/*
fun handleNavigation(
    navController: NavController?,
    viewModel: SplashViewModel,
    language: String,
    payloadData: PayloadData?
) {
    if (viewModel.isLogined && payloadData != null && payloadData.type != null && payloadData.id != null) {
        when (payloadData.type) {
            PayloadType.Product -> {
                navController?.navigate(
                    NAV_PRODUCT_DETAILS.appendNavParams(
                        (Constants.PRODUCT_TYPE to Constants.ProductTypes.SIMPLE_PRODUCT)
                    )){
                    popUpTo(Destination.SplashScreen.fullRoute){
                        inclusive = true
                    }
                }
            }

            PayloadType.Category -> {
                val categoryName =
                    if (language == PrefKey.AR_CODE) Category.MedicationAr else Category.MedicationEn
                navController?.navigate("$NAV_SUB_CATEGORIES_LIST/${categoryName}"){
                    popUpTo(Destination.SplashScreen.fullRoute){
                        inclusive = true
                    }
                }
            }

            PayloadType.General -> {
                navController?.navigate(Destination.HomeScreen.fullRoute) {
                    popUpTo(Destination.SplashScreen.fullRoute) {
                        inclusive = true
                    }
                }
            }

            PayloadType.Order -> {
                navController?.navigate(Destination.MyOrdersScreen.fullRoute){
                    popUpTo(Destination.SplashScreen.fullRoute){
                        inclusive = true
                    }
                }
            }
        }
    } else {
        navController?.navigate(
            route = when {
                !viewModel.onboardingVisitedAr && language == PrefKey.AR_CODE -> {
                    Destination.OnBoardingScreen.fullRoute
                }

                !viewModel.onboardingVisitedEn && language == PrefKey.EN_CODE -> {
                    Destination.OnBoardingScreen.fullRoute
                }

                !viewModel.isLogined -> Destination.LoginScreen.fullRoute
                else -> Destination.HomeScreen.fullRoute
            }
        ) {
            popUpTo(Destination.SplashScreen.fullRoute) {
                inclusive = true
            }
        }
    }
}*/
