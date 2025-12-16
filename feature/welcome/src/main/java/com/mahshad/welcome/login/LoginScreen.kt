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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.ui.ModifiedButton
import com.mahshad.ui.ModifiedTextFiled
import com.mahshad.welcome.R
import com.mahshad.welcome.login.LoginScreenViewModel
import kotlinx.serialization.Serializable

@Serializable
data object LoginScreenRoute

@Composable
fun LoginScreen(
    onNavigateToHome: (String) -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val usernameState = viewModel.usernameStateFlow.collectAsStateWithLifecycle()
    val passwordState = viewModel.passwordStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 50.dp, end = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(R.drawable.mobile_login_rafiki),
            contentDescription = "login photo",
            modifier = Modifier
                .size(225.dp)
                .padding(top = 0.dp),
        )
        Text(
            "Login", fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 0.dp, start = 10.dp)
        )
        ModifiedTextFiled(
            value = usernameState.value,
            onValueChanged = { viewModel.updateFlow(it, true) },
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
        Spacer(Modifier.height(3.dp))
        ModifiedTextFiled(
            value = passwordState.value,
            onValueChanged = { viewModel.updateFlow(it, false) },
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
        Text(
            "Forgot password?",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 5.dp)
                .align(Alignment.End)
                .clickable(true) {}
        )
        ModifiedButton(
            content = { Text("Login") },
            onClick = { onNavigateToHome("Sara") },
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
            "Or login with",
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
            Text("New here?", fontSize = 14.sp)
            Spacer(Modifier.width(5.dp))
            Text(
                text = "Register",
                fontSize = 14.sp,
                color = colorResource(R.color.blue),
                modifier = Modifier.clickable(true) { onNavigateToSignUp() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({}, {})
}