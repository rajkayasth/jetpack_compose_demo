package com.example.compose_praticle_demo.navigations

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    data object SplashScreen : NoArgumentsDestination(NAV_SPLASH)
    data object LoginScreen : NoArgumentsDestination(NAV_LOGIN)
    data object OnBoardingScreen : NoArgumentsDestination(NAV_ONBOARDING)
    data object OtpScreen : NoArgumentsDestination(NAV_CONFIRM_PHONE_SCREEN_PREVIEW)
}

data class ImageGalleryScreen(val images: List<String>)

data class NewChatConnectDetail(val groupName: String = "", val userName: String = "", )

internal fun String.appendNavParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}