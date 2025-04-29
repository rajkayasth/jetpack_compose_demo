package com.example.compose_praticle_demo.modules.otpscreen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.compose_praticle_demo.navigations.NAV_CONFIRM_PHONE_SCREEN_PREVIEW
import com.example.compose_praticle_demo.navigations.NAV_LOGIN
import com.example.compose_praticle_demo.navigations.NAV_ONBOARDING
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(): ViewModel() {
    var otp by mutableStateOf(List(6) { "" })

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun onOtpChange(index: Int, value: String) {
        if (value.length <= 1 && (value.isEmpty() || value.all { it.isDigit() })) {
            otp = otp.toMutableList().also { it[index] = value }

            // ✅ Clear error when the user types
            if (_errorMessage.value.isNotEmpty()) {
                _errorMessage.value = ""
            }
        }
    }

    fun clearOtp() {
        otp = List(6) { "" }


    }

    fun getOtp(): String = otp.joinToString("")

    fun onVerifyClicked(navController: NavController) {
        val isOtpComplete = otp.all { it.length == 1 && it.all { char -> char.isDigit() } }



        if (isOtpComplete) {
            val fullOtp = otp.joinToString("")
            // You can add API call or verification logic here
            navController.navigate(NAV_ONBOARDING)
        } else {
            // Show error: Incomplete or invalid OTP
            _errorMessage.value = "Please enter all 6 digits"
        }
    }
}
