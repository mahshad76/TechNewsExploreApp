package com.mahshad.welcome.signup

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.ui.components.ModifiedButton
import com.mahshad.ui.components.ModifiedTextFiled
import com.mahshad.welcome.R
import kotlinx.serialization.Serializable

@Serializable
data object SignUpScreenRoute

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: SignUpScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val usernameState = viewModel.usernameStateFlow.collectAsStateWithLifecycle()
    val passwordState = viewModel.passwordStateFlow.collectAsStateWithLifecycle()
    val passwordConfirmationState =
        viewModel.passwordConfirmationStateFlow.collectAsStateWithLifecycle()
    val isEnabled = viewModel.isEnabled.collectAsStateWithLifecycle()
    val signUpStatus = viewModel.signUpStatusFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Box(
//            modifier = Modifier
//                .size(225.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = painterResource(R.drawable.signup_2),
//                contentDescription = "signup photo",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 0.dp)
//            )
//        }

        //Spacer(Modifier.height(16.dp))

        Text(
            text = "Register",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp, // Increased size to match visual prominence
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        ModifiedTextFiled(
            value = usernameState.value,
            onValueChanged = { viewModel.updateFlow(it, 1) },
            placeHolder = { Text("Email", color = Color.Gray) },
            cornerRadius = 0,
            color = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
        )

        Spacer(Modifier.height(20.dp))

        ModifiedTextFiled(
            value = passwordState.value,
            onValueChanged = { viewModel.updateFlow(it, 2) },
            placeHolder = { Text("Password", color = Color.Gray) },
            cornerRadius = 0,
            color = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
        )

        Spacer(Modifier.height(20.dp))

        ModifiedTextFiled(
            value = passwordConfirmationState.value,
            onValueChanged = { viewModel.updateFlow(it, 3) },
            placeHolder = { Text("Confirm password", color = Color.Gray) },
            cornerRadius = 0,
            color = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
        )

        Text(
            "Forgot password?",
            fontSize = 14.sp,
            color = colorResource(R.color.blue),
            modifier = Modifier
                .padding(vertical = 15.dp)
                .align(Alignment.End)
                .clickable { /* action */ }
        )

        ModifiedButton(
            content = {
                Text("Register", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            },
            onClick = {
                viewModel.signUp(usernameState.value, passwordState.value)
                if (signUpStatus.value) Toast.makeText(
                    context,
                    "Successful registration",
                    Toast.LENGTH_SHORT
                )
                    .show()
                else Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            },
            shape = RoundedCornerShape(10.dp),
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            borderStroke = BorderStroke(0.5.dp, Color.LightGray),
            enabled = isEnabled.value
        )

        Text(
            "Or register with",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 25.dp)
        )

        ModifiedButton(
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.google),
                        contentDescription = "google icon",
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(Modifier.width(10.dp))
                    Text("Google", fontWeight = FontWeight.Bold)
                }
            },
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            borderStroke = BorderStroke(0.5.dp, Color.LightGray),
            enabled = true,
            modifier = Modifier
                .width(180.dp) // Slightly wider for better text fit
                .height(50.dp)
        )

        Spacer(Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text("Already have an account?", fontSize = 15.sp, color = Color.Gray)
            Spacer(Modifier.width(5.dp))
            Text(
                text = "Login",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue),
                modifier = Modifier.clickable { onNavigateToLogin() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen({})
}