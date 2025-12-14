import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahshad.ui.ModifiedTextFiled
import com.mahshad.welcome.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier // ACCEPT MODIFIER FOR REUSABILITY!
) {
    // Defines the layout to fill the screen and center the contents
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.mobile_login_rafiki),
            contentDescription = "login photo",
            modifier = Modifier.size(225.dp),
        )
        Text(
            "Login", fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier
                .width(311.dp)
                .wrapContentHeight()
                .padding(top = 10.dp, start = 10.dp)
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
                .width(311.dp)
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
        Spacer(Modifier.height(36.dp))
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
                .width(311.dp)
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
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}