package com.example.compose_praticle_demo.modules.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.compose_praticle_demo.modules.splash.viewmodel.SplashViewModel
import com.example.compose_praticle_demo.navigations.Destination
import com.example.compose_praticle_demo.utils.rRaw
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(
    navController: NavController?,
    viewModel: SplashViewModel,
) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        handleNavigation(
            navController, viewModel,

        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
//         Lottie Animation
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rRaw.splash_lottie_json))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            contentScale = ContentScale.Inside,
            modifier = Modifier.fillMaxSize()
        )
    }
}

// handle navigation after splash screen
fun handleNavigation(
    navController: NavController?,
    viewModel: SplashViewModel,
) {
//    if (viewModel.isLogined && payloadData != null && payloadData.type != null && payloadData.id != null) {
//        when (payloadData.type) {
//            PayloadType.Product -> {
//                navController?.navigate(
//                    NAV_PRODUCT_DETAILS.appendNavParams(
//                        (Constants.PRODUCT_TYPE to Constants.ProductTypes.SIMPLE_PRODUCT)
//                    )){
//                    popUpTo(Destination.SplashScreen.fullRoute){
//                        inclusive = true
//                    }
//                }
//            }
//
//            PayloadType.Category -> {
//                val categoryName =
//                    if (language == PrefKey.AR_CODE) Category.MedicationAr else Category.MedicationEn
//                navController?.navigate("$NAV_SUB_CATEGORIES_LIST/${categoryName}"){
//                    popUpTo(Destination.SplashScreen.fullRoute){
//                        inclusive = true
//                    }
//                }
//            }
//
//            PayloadType.General -> {
//                navController?.navigate(Destination.HomeScreen.fullRoute) {
//                    popUpTo(Destination.SplashScreen.fullRoute) {
//                        inclusive = true
//                    }
//                }
//            }
//
//            PayloadType.Order -> {
//                navController?.navigate(Destination.MyOrdersScreen.fullRoute){
//                    popUpTo(Destination.SplashScreen.fullRoute){
//                        inclusive = true
//                    }
//                }
//            }
//        }
//    }
//    else {
        navController?.navigate(
            route = when {


                !viewModel.onboardingVisitedEn -> {
                    Destination.OnBoardingScreen.fullRoute
                }

                !viewModel.isLogined -> Destination.LoginScreen.fullRoute
                else -> Destination.OnBoardingScreen.fullRoute
            }
        ) {
            popUpTo(Destination.SplashScreen.fullRoute) {
                inclusive = true
            }
//        }
    }
}
