package com.mahshad.welcome.signup

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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahshad.ui.ModifiedButton
import com.mahshad.ui.ModifiedTextFiled
import com.mahshad.welcome.R
import kotlinx.serialization.Serializable

@Serializable
data object SignUpScreenRoute

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    //viewModel: LoginScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    //val usernameState = viewModel.usernameStateFlow.collectAsStateWithLifecycle()
    //val passwordState = viewModel.passwordStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 50.dp, end = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(R.drawable.signup_2),
            contentDescription = "Register photo",
            modifier = Modifier
                .size(225.dp)
                .padding(top = 0.dp),
        )
        Text(
            "Register", fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 0.dp, start = 10.dp)
        )
        ModifiedTextFiled(
            value = "",
            onValueChanged = {},
            placeHolder = { Text("Email") },
            cornerRadius = 0,
            color = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                }
        )
        ModifiedTextFiled(
            value = "",
            onValueChanged = {},
            placeHolder = { Text("Password") },
            cornerRadius = 0,
            color = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                }
        )
        ModifiedTextFiled(
            value = "",
            onValueChanged = {},
            placeHolder = { Text("Confirm password") },
            cornerRadius = 0,
            color = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 5.dp)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                }
        )
        ModifiedButton(
            content = { Text("Register") },
            onClick = {},
            shape = RoundedCornerShape(8.dp),
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue),
                contentColor = Color.White
            ),
            borderStroke = BorderStroke(
                width = 2.dp,
                color = colorResource(R.color.blue)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            "Or register with",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
        )
        ModifiedButton(
            content = {
                Row(horizontalArrangement = Arrangement.Center) {
                    Image(
                        painter = painterResource(R.drawable.google),
                        contentDescription = "google icon",
                        modifier = Modifier.size(25.dp),
                    )
                    Spacer(Modifier.width(15.dp))
                    Text("Google")
                }
            },
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            borderStroke = BorderStroke(
                width = 0.5.dp,
                color = Color.Gray
            ),
            modifier = Modifier
                .width(143.dp)
                .height(50.dp)
        )
        Row(Modifier.padding(top = 5.dp)) {
            Text("Already have an account?", fontSize = 14.sp)
            Spacer(Modifier.width(5.dp))
            Text(
                text = "Login",
                fontSize = 14.sp,
                color = colorResource(R.color.blue),
                modifier = Modifier.clickable(true) { onNavigateToLogin() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen({})
}