package com.example.banquemisr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banquemisr.ui.theme.BanqueMisrTheme
import com.example.banquemisr.ui.theme.LightPinkishRed
import com.example.banquemisr.ui.theme.LightRed
import com.example.banquemisr.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrTheme {
                BanqueMisr()
            }
        }
    }
}

@Composable
fun BanqueMisr(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        var isButtonEnabled: Boolean by remember {
            mutableStateOf(false)
        }
        var userName: String by remember {
            mutableStateOf("")
        }
        var password: String by remember {
            mutableStateOf("")
        }
        var showPassword: Boolean by remember {
            mutableStateOf(false)
        }
        var passIcon: Int by remember {
            mutableStateOf(R.drawable.ic_visibility_off)
        }
        if (userName.isNotEmpty() && password.isNotEmpty()) {
            isButtonEnabled = true
        } else {
            isButtonEnabled = false
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 64.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderTitle()
        }
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(stringResource(id = R.string.user_name)) },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp)
        )
        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password)) },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
            visualTransformation = if (showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = passIcon),
                    contentDescription = "Hide pass", modifier = modifier.clickable {
                        showPassword = !showPassword
                        passIcon = if (showPassword) R.drawable.ic_visibility
                        else R.drawable.ic_visibility_off
                    }
                )
            })
        Text(
            text = stringResource(id = R.string.forget_sm),
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = modifier.padding(start = 16.dp, top = 24.dp)
        )
        Button(
            onClick = { },
            colors = ButtonColors(LightRed, White, LightPinkishRed, White),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            enabled = isButtonEnabled,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Login", modifier = modifier.padding(all = 8.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.need_help),
                modifier = modifier.padding(start = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.contact),
                modifier = modifier.padding(start = 4.dp),
                style = TextStyle(textDecoration = TextDecoration.Underline, color = LightRed)
            )

        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Services()


    }
}

@Composable
fun HeaderTitle() {
    val context = LocalContext.current
    Image(
        painter = painterResource(R.drawable.bm_icon), contentDescription = "BM Icon"
    )

    TextButton(onClick = { //context.setLocale("ar")
    }) {
        Text(
            text = stringResource(id = R.string.arabic), style = TextStyle(
                fontSize = 20.sp, color = LightRed, fontWeight = FontWeight.W600
            )
        )
    }

}

@Composable
fun Services(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ServiceItem(
            imageRes = R.drawable.our_products,
            textRes = R.string.our_products
        )
        ServiceItem(
            imageRes = R.drawable.exchange_rate,
            textRes = R.string.exchange_rate
        )
        ServiceItem(
            imageRes = R.drawable.security_tips,
            textRes = R.string.security_tips
        )
        ServiceItem(
            imageRes = R.drawable.nearest_branch_or_atm,
            textRes = R.string.nearest_atm
        )
    }
}

@Composable
fun ServiceItem(imageRes: Int, textRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp) // Ensure consistent padding between items
            .width(72.dp) // Set a fixed width for consistent layout
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = stringResource(id = textRes),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
    }
}


@Preview(showSystemUi = true)
@Composable
private fun BaqueMisrPreview() {
    BanqueMisr()
}

