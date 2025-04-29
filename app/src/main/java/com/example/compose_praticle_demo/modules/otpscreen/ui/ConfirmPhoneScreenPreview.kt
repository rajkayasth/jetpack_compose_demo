package com.example.compose_praticle_demo.modules.otpscreen.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose_praticle_demo.modules.otpscreen.viewmodel.OtpViewModel

@Composable
fun ConfirmPhoneScreenPreview(navController: NavHostController, viewModel: OtpViewModel) {
    ConfirmPhoneScreen(viewModel = viewModel,navController,)
}

@Composable
fun ConfirmPhoneScreen(viewModel: OtpViewModel = viewModel(), navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Confirm your phone",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We send 6 digits code to +881 1720 84 57 57",
            style = MaterialTheme.typography.bodyMedium
        )

        OtpAndTextUI(viewModel)
        Button(
            onClick = {
                viewModel.onVerifyClicked(navController = navController)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A5BFF)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(56.dp)
        ) {
            Text(text = "Verify Your Number", color = Color.White)
        }

        if (viewModel.errorMessage.value.isNotEmpty()) {
            Text(
                text = viewModel.errorMessage.value,
                color = Color.Red,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun OtpAndTextUI(viewModel: OtpViewModel) {
    val otp = viewModel.otp
    val focusRequesters = remember { List(6) { FocusRequester() } }

    Column {
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            otp.forEachIndexed { index, digit ->
                OtpInputField(
                    value = digit,
                    onValueChange = { input ->
                        val current = otp[index]
                        if (input.length <= 1 && input.all { it.isDigit() }) {
                            viewModel.onOtpChange(index, input)
                            if (input.isNotEmpty() && index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        } else if (input.isEmpty() && current.isNotEmpty()) {
                            viewModel.onOtpChange(index, "")
                            if (index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    },
                    focusRequester = focusRequesters[index],
                    previousRequester = focusRequesters.getOrNull(index - 1),
                    nextRequester = focusRequesters.getOrNull(index + 1)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text("Didn't get a code?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Resend",
                color = Color(0xFF3A5BFF),
                modifier = Modifier.clickable {

                }
            )
        }
    }
}

@Composable
fun OtpInputField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    previousRequester: FocusRequester?,
    nextRequester: FocusRequester?
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .width(48.dp)
            .focusRequester(focusRequester)
            .focusProperties {
                if (previousRequester != null) {
                    previous = previousRequester
                }
                if (nextRequester != null) {
                    next = nextRequester
                }
            },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
