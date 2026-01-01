import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.layout.ContentScale
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
import com.mahshad.ui.components.OverlappingViews
import com.mahshad.ui.icons.TneIcons.AuthenticationBackground
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
    val isEnabledState = viewModel.isEnabled.collectAsStateWithLifecycle()
    val loginStatusState = viewModel.loginStatusFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val content: @Composable () -> Unit = {
        LoginFormContent(
            onNavigateToHome = onNavigateToHome,
            onNavigateToSignUp = onNavigateToSignUp,
            updateFlow = { update: String, type: Boolean -> viewModel.updateFlow(update, type) },
            login = { username: String, password: String -> viewModel.login(username, password) },
            usernameState = usernameState.value,
            passwordState = passwordState.value,
            isEnabledState = isEnabledState.value,
            loginStatusState = loginStatusState.value,
            context = context
        )
    }
    val background: @Composable () -> Unit = {
        Image(
            painter = painterResource(id = AuthenticationBackground),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    OverlappingViews(
        backgroundModifier = Modifier.fillMaxWidth(),
        contentModifier = Modifier
            .fillMaxWidth()
            .offset(y = -100.dp),
        backgroundShape = RoundedCornerShape(0.dp),
        contentShape = RoundedCornerShape(24.dp),
        background = background,
        content = content
    )
}

@Composable
private fun LoginFormContent(
    onNavigateToHome: (String) -> Unit,
    onNavigateToSignUp: () -> Unit,
    updateFlow: (String, Boolean) -> Unit,
    login: (String, String) -> Unit,
    usernameState: String,
    passwordState: String,
    isEnabledState: Boolean,
    loginStatusState: Boolean,
    context: Context,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        ModifiedTextFiled(
            value = usernameState,
            onValueChanged = { updateFlow(it, true) },
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
            value = passwordState,
            onValueChanged = { updateFlow(it, false) },
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
                Text("Login", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            },
            onClick = {
                login(usernameState, passwordState)
                if (loginStatusState) {
                    onNavigateToHome(usernameState)
                } else {
                    Toast.makeText(
                        context,
                        "Wrong username or password",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            },
            shape = RoundedCornerShape(10.dp),
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue),
                contentColor = Color.White
            ),
            borderStroke = BorderStroke(0.5.dp, Color.LightGray),
            enabled = isEnabledState,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )

        Text(
            "Or login with",
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
                .width(180.dp)
                .height(50.dp)
        )

        Spacer(Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text("New here?", fontSize = 15.sp, color = Color.Gray)
            Spacer(Modifier.width(5.dp))
            Text(
                text = "Register",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.blue),
                modifier = Modifier.clickable { onNavigateToSignUp() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({}, {})
}